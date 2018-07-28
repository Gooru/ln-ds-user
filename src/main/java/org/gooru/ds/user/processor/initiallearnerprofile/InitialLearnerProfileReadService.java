package org.gooru.ds.user.processor.initiallearnerprofile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.constants.ExecutionStatus;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mukul@gooru
 * 
 */
public class InitialLearnerProfileReadService {

    private final InitialLearnerProfileReadDao baseUserProfileReadDao;    
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialLearnerProfileReadService.class);
    private final LearnerProfileStatusUpdateDao learnerProfileStatusUpdateDao;
    private final UserCompetencyStatusUpdateDao userCompetencyStatusUpdateDao;
    private final GradeMapUpdateDao gradeMapUpdateDao;

    InitialLearnerProfileReadService(DBI dbi, DBI coreDbi) {
        this.baseUserProfileReadDao = dbi.onDemand(InitialLearnerProfileReadDao.class);     
        this.learnerProfileStatusUpdateDao = dbi.onDemand(LearnerProfileStatusUpdateDao.class);
        this.userCompetencyStatusUpdateDao = coreDbi.onDemand(UserCompetencyStatusUpdateDao.class);
        this.gradeMapUpdateDao = dbi.onDemand(GradeMapUpdateDao.class);
    }
    
    public ExecutionStatus fetchBaseProfile(InitialLearnerProfileReadCommand command) {
	    Timestamp createdAt = new Timestamp(System.currentTimeMillis());
	    Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        List<InitialLearnerProfileReadModel> models = baseUserProfileReadDao.fetchBaseUserProfile(command.asBean(), command.getGrade());        
        
        learnerProfileStatusUpdateDao.UpdateLearnerProfileCompetencyStatus(models, command.getUser(), createdAt, updatedAt);
        
        LOGGER.info("Initial Profile for the User" + command.getUser() + "updated in the LP Status table.");
        
        learnerProfileStatusUpdateDao.UpdateLearnerProfileCompetencyStatusTs(models, command.getUser(), createdAt, updatedAt);
        
        LOGGER.info("Initial Profile for the User" + command.getUser() + "updated in the LP TS table.");

        userCompetencyStatusUpdateDao.UpdateUserCompetencyStatus(models, command.getUser(), createdAt, updatedAt);
        
        LOGGER.info("Initial Profile for the User" + command.getUser() + "updated in the User Competency Status Table");
        
        gradeMapUpdateDao.UpdateLearnerProfileCompetencyStatus(command.getUser(), command.getSubjectCode(), command.getGrade());
        
        LOGGER.info("Grade Information for " + command.getUser() + "updated in the User Grade Map Table");

        if (models.isEmpty() || models == null) {
        	LOGGER.info("No Profile records available for " + command.getGrade());
        	return ExecutionStatus.SUCCESSFUL;
        }
        
        LOGGER.info("Initial Profile for the User" + command.getUser() + "Successfully Updated!");
        return ExecutionStatus.SUCCESSFUL;
    }

}
