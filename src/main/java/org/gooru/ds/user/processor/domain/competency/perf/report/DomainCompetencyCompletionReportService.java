
package org.gooru.ds.user.processor.domain.competency.perf.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.constants.HttpConstants.HttpStatus;
import org.gooru.ds.user.constants.StatusConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.atc.pvc.course.competency.utils.CollectionUtils;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClass;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClassDao;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreUserDao;
import org.gooru.ds.user.processor.domain.report.dbhelpers.UserModel;
import org.gooru.ds.user.processor.domain.report.utils.DomainReportUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class DomainCompetencyCompletionReportService {

  private final static Logger LOGGER =
      LoggerFactory.getLogger(DomainCompetencyCompletionReportService.class);

  private final DomainCompetencyCompletionReportDao competencyCompletionDao;
  private final CoreClassDao coreClassDao;
  private final CoreUserDao coreUserDao;

  public DomainCompetencyCompletionReportService(DBI defaultDbi, DBI coreDbi) {
    this.competencyCompletionDao = defaultDbi.onDemand(DomainCompetencyCompletionReportDao.class);
    this.coreClassDao = coreDbi.onDemand(CoreClassDao.class);
    this.coreUserDao = coreDbi.onDemand(CoreUserDao.class);
  }

  public JsonObject fetchDomainCompetencyPerfReport(
      DomainCompetencyCompletionReportCommand command) {
    DomainCompetencyCompletionReportCommand.DomainCompetencyCompletionReportCommandBean bean =
        command.asBean();

    // Fetch class
    CoreClass cls = this.coreClassDao.fetchClassGrades(bean.getClassId());
    if (cls == null) {
      LOGGER.warn("class '{}' not found", bean.getClassId());
      throw new HttpResponseWrapperException(HttpStatus.NOT_FOUND, "class not found");
    }

    // Fetch subject from the class
    String subjectCode = DomainReportUtils.fetchSubjectFromClass(cls);

    // fetch class members
    List<String> classMembers = this.coreClassDao.fetchClassMembers(bean.getClassId());

    // Fetch all competencies above the requested competency
    List<String> domainCompetencies = this.competencyCompletionDao
        .fetchHigherCompetencies(bean.getTxCode(), subjectCode, bean.getDomain());

    // Fetch the completion of the users for the give competency
    List<UserCompetencyCompletionModel> userCompetencyCompletion =
        this.competencyCompletionDao.fetchCompetencyCompletionByGut(bean,
            CollectionUtils.convertToSqlArrayOfString(classMembers));
    Map<String, UserCompetencyCompletionModel> userCompetencyCompletionMap = new HashMap<>();
    userCompetencyCompletion.forEach(competencyCompletion -> {
      userCompetencyCompletionMap.put(competencyCompletion.getUserId(), competencyCompletion);
    });

    // Fetch the completion of the users for higher competencies of the given competency to check if
    // it is inferred completed
    List<String> userInferredCompletion = this.competencyCompletionDao.fetchInferredCompletion(
        CollectionUtils.convertToSqlArrayOfString(classMembers),
        CollectionUtils.convertToSqlArrayOfString(domainCompetencies), bean.getToDate());

    // Fetch user details of the class members
    List<UserModel> users =
        this.coreUserDao.fetchUserDetails(CollectionUtils.convertToSqlArrayOfUUID(classMembers));

    Map<String, UserCompetencyCompletionModel> userCompetencyCompletionModelMap = new HashMap<>();
    users.forEach(user -> {
      String userId = user.getId();
      if (userCompetencyCompletionMap.containsKey(userId)) {
        UserCompetencyCompletionModel competencyCompletion =
            userCompetencyCompletionMap.get(userId);
        if (competencyCompletion.getStatus() >= StatusConstants.COMPLETED) {
          userCompetencyCompletionModelMap.put(competencyCompletion.getUserId(),
              competencyCompletion);
        } else {
          if (competencyCompletion.getStatus() == StatusConstants.IN_PROGRESS) {
            if (userInferredCompletion.contains(competencyCompletion.getUserId())) {
              UserCompetencyCompletionModel completionModel = new UserCompetencyCompletionModel();
              completionModel.setUserId(competencyCompletion.getUserId());
              completionModel.setStatus(StatusConstants.INFERRED);
              userCompetencyCompletionModelMap.put(competencyCompletion.getUserId(),
                  completionModel);
            } else {
              userCompetencyCompletionModelMap.put(competencyCompletion.getUserId(),
                  competencyCompletion);
            }
          }
        }
      } else {
        if (userInferredCompletion.contains(userId)) {
          UserCompetencyCompletionModel completionModel = new UserCompetencyCompletionModel();
          completionModel.setUserId(userId);
          completionModel.setStatus(StatusConstants.INFERRED);
          userCompetencyCompletionModelMap.put(userId, completionModel);
        } else {
          UserCompetencyCompletionModel completionModel = new UserCompetencyCompletionModel();
          completionModel.setUserId(user.getId());
          completionModel.setStatus(StatusConstants.NOT_STARTED);
          userCompetencyCompletionModelMap.put(user.getId(), completionModel);
        }
      }
    });

    return DomainCompetencyCompletionReportResponseBuilder.build(users,
        userCompetencyCompletionModelMap, bean.getAgent());
  }
}
