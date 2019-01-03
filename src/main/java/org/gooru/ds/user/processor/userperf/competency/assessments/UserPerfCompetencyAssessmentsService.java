package org.gooru.ds.user.processor.userperf.competency.assessments;

import java.util.List;
import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsCommand;
import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsDao;
import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsModel;
import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsModelResponse;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
public class UserPerfCompetencyAssessmentsService {

  private final UserPerfCompetencyAssessmentsDao userPerfCompetencyAssessmentsDao;
  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserPerfCompetencyAssessmentsService.class);

  UserPerfCompetencyAssessmentsService(DBI dbi) {
    this.userPerfCompetencyAssessmentsDao = dbi.onDemand(UserPerfCompetencyAssessmentsDao.class);
  }

  public UserPerfCompetencyAssessmentsModelResponse fetchUserAssessmentsPerf(
      UserPerfCompetencyAssessmentsCommand command) {
    List<UserPerfCompetencyAssessmentsModel> models =
        userPerfCompetencyAssessmentsDao.fetchUserPerfCompetencyAssessments(command.asBean());
    UserPerfCompetencyAssessmentsModelResponse result =
        new UserPerfCompetencyAssessmentsModelResponse();
    result.setAssessments(models);
    return result;
  }

}
