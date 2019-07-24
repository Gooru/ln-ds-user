package org.gooru.ds.user.processor.competencysummary;

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
public class UserCompetencySummaryFetcher {

  private final CompetencySummaryDao competencyStatsDao;

  UserCompetencySummaryFetcher(DBI dbi, DBI coreDbi) {
    this.competencyStatsDao = dbi.onDemand(CompetencySummaryDao.class);
  }

  public CompetencySummaryModel fetchUserCompetencySummary(
      UserCompetencySummaryCommand command) {
    //CompetencySummaryModel userCompetencyStatsList = new ArrayList<>();
    CompetencySummaryModel userCompetencyStats = new CompetencySummaryModel();

      if ((command.getMonth() == null || command.getYear() == null)) {
        userCompetencyStats = competencyStatsDao.fetchGradeCompetencyStats(
            command.getUserId(), command.getClassId(), command.getCourseId(), command.getSubjectCode());
      } else if ((command.getMonth() != null && command.getYear() != null)) {
        userCompetencyStats = competencyStatsDao.fetchGradeCompetencyStatsTimeBound(
            command.getUserId(), command.getClassId(), command.getCourseId(), command.getSubjectCode(),
            command.getStatsDate());
      }
    return userCompetencyStats;
  }
}
