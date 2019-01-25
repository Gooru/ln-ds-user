package org.gooru.ds.user.processor.atc.recompute;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class ATCCompetencyStatsFetcher {

  private final ClassMembersDao classMembersDao;
  private final CompetencyStatsDao competencyStatsDao;
  private static final Logger LOGGER =
      LoggerFactory.getLogger(ATCCompetencyStatsFetcher.class);

  ATCCompetencyStatsFetcher(DBI dbi, DBI coreDbi) {
    this.classMembersDao = coreDbi.onDemand(ClassMembersDao.class);
    this.competencyStatsDao = dbi.onDemand(CompetencyStatsDao.class);
  }

  public List<CompetencyStatsModel> fetchGradeBasedUserPerformanceCompletion(
      ATCCompetencyStatsCommand command) {
    List<CompetencyStatsModel> userCompetencyStatsList = new ArrayList<>();

    // Fetch Class Members
    List<String> classMembers =
        classMembersDao.fetchClassMembers(UUID.fromString(command.getClassId()));
    if (classMembers != null && !classMembers.isEmpty()) {
      if ((command.getMonth() == null || command.getYear() == null)) {
        userCompetencyStatsList = competencyStatsDao.fetchGradeCompetencyStats(
            PGArrayUtils.convertFromListStringToSqlArrayOfString(classMembers),
            command.getClassId(), command.getCourseId(), command.getSubjectCode());
      } else if ((command.getMonth() != null && command.getYear() != null)) {
        userCompetencyStatsList = competencyStatsDao.fetchGradeCompetencyStatsTimeBound(
            PGArrayUtils.convertFromListStringToSqlArrayOfString(classMembers),
            command.getClassId(), command.getCourseId(), command.getSubjectCode(), command.getMonth(), command.getYear());
      }
    }
    return userCompetencyStatsList;
  }
}
