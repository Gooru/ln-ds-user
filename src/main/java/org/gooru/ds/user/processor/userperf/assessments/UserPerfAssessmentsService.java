package org.gooru.ds.user.processor.userperf.assessments;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserPerfAssessmentsService {

    private final UserPerfAssessmentsDao userPerfAssessmentsDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfAssessmentsService.class);

    UserPerfAssessmentsService(DBI dbi) {
        this.userPerfAssessmentsDao = dbi.onDemand(UserPerfAssessmentsDao.class);
    }

    public UserPerfAssessmentsModelResponse fetchUserAssessmentsPerf(UserPerfAssessmentsCommand command) {
        List<UserPerfAssessmentsModel> models = userPerfAssessmentsDao.fetchUserPerfAssessments(command.asBean());
        UserPerfAssessmentsModelResponse result = new UserPerfAssessmentsModelResponse();
        result.setAssessments(models);
        return result;
    }

}
