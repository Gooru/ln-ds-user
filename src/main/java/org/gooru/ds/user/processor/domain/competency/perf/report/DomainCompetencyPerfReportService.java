
package org.gooru.ds.user.processor.domain.competency.perf.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.processor.atc.pvc.course.competency.utils.CollectionUtils;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClassDao;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreUserDao;
import org.gooru.ds.user.processor.domain.report.dbhelpers.UserModel;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class DomainCompetencyPerfReportService {

  private final static Logger LOGGER =
      LoggerFactory.getLogger(DomainCompetencyPerfReportService.class);

  private final DomainCompetencyPerfReportDao competencyPerfDao;
  private final CoreClassDao coreClassDao;
  private final CoreUserDao coreUserDao;

  public DomainCompetencyPerfReportService(DBI defaultDbi, DBI coreDbi) {
    this.competencyPerfDao = defaultDbi.onDemand(DomainCompetencyPerfReportDao.class);
    this.coreClassDao = coreDbi.onDemand(CoreClassDao.class);
    this.coreUserDao = coreDbi.onDemand(CoreUserDao.class);
  }

  public JsonObject fetchDomainCompetencyPerfReport(DomainCompetencyPerfReportCommand command) {
    DomainCompetencyPerfReportCommand.DomainCompetencyPerfReportCommandBean bean = command.asBean();

    // fetch class members
    List<String> classMembers = this.coreClassDao.fetchClassMembers(bean.getClassId());
    List<UserCompetencyPerformanceModel> userCompetencyPerf =
        this.competencyPerfDao.fetchCompetencyPerfByClassAndGut(bean,
            CollectionUtils.convertToSqlArrayOfString(classMembers));

    List<UserModel> users =
        this.coreUserDao.fetchUserDetails(CollectionUtils.convertToSqlArrayOfUUID(classMembers));

    Map<String, UserCompetencyPerformanceModel> userCompetencyPerfModelMap = new HashMap<>();
    userCompetencyPerf.forEach(userPerf -> {
      LOGGER.debug(userPerf.toString());
      userCompetencyPerfModelMap.put(userPerf.getUserId(), userPerf);
    });

    return DomainCompetencyPerfReportResponseBuilder.build(users, userCompetencyPerfModelMap,
        bean.getAgent());
  }
}
