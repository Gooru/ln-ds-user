package org.gooru.ds.user.processor.userperf.assessments;

import org.gooru.ds.user.processor.userperf.assessments.UserPerfAssessmentsCommand;
import org.gooru.ds.user.processor.userperf.assessments.UserPerfAssessmentsDao;
import org.gooru.ds.user.processor.userperf.assessments.UserPerfAssessmentsModel;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mukul@gooru
 */
public class UserPerfAssessmentsService {


    private final UserPerfAssessmentsDao userPerfAssessmentsDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfAssessmentsService.class);

    UserPerfAssessmentsService(DBI dbi) {
        this.userPerfAssessmentsDao = dbi.onDemand(UserPerfAssessmentsDao.class);
    }

    public UserPerfAssessmentsModel fetchUserAssessmentsPerf(UserPerfAssessmentsCommand command) {
    	UserPerfAssessmentsModel result = userPerfAssessmentsDao.fetchUserPerfAssessments(command.asBean());
        return result != null ? result : new UserPerfAssessmentsModel();
    }


}