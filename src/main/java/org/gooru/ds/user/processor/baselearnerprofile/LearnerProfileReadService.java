package org.gooru.ds.user.processor.baselearnerprofile;

import java.sql.Timestamp;
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
public class LearnerProfileReadService {
	
    private final LearnerProfileReadDao learnerProfileReadDao;
    private final LearnerProfileBaselineUpdateDao baseLearnerProfileDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(LearnerProfileReadService.class);
   
    LearnerProfileReadService(DBI dbi) {
        this.learnerProfileReadDao = dbi.onDemand(LearnerProfileReadDao.class);
        this.baseLearnerProfileDao = dbi.onDemand(LearnerProfileBaselineUpdateDao.class);
    }
    
    public ExecutionStatus fetchCurrentLearnerProfileAndSetBaseline(LearnerProfileBaselineUpdateCommand command) {
        List<LearnerProfileReadModel> models = learnerProfileReadDao.fetchCurrentLearnerProfile(command.asBean());         

        if (models.isEmpty() || models == null) {
        	LOGGER.info("No Profile records available for " + command.getUser());
        	return ExecutionStatus.SUCCESSFUL;
        }

        
		Timestamp createdAt = new Timestamp(System.currentTimeMillis());
		LOGGER.info("Timestamp is: " + createdAt);
		
		if (command.getClassId() != null) {
			baseLearnerProfileDao.UpdateLearnerBaselineProfile(models, command.getClassId(), command.getCourseId(), createdAt);			
		} else if (command.getClassId() == null) {
			baseLearnerProfileDao.UpdateLearnerBaselineProfileForIL(models, command.getCourseId(), createdAt);
		}	    
        
        LOGGER.info("Learner Profile successfully baseline for user " + command.getUser());
        return ExecutionStatus.SUCCESSFUL;
    }

}
