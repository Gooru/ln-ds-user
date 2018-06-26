package org.gooru.ds.user.processor.usercoursecompetencymatrix;

import java.sql.Timestamp;
import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/2/18.
 */
class UserCourseCompetencyMatrixService {

    private final UserCourseCompetencyMatrixDao userCompetencyMatrixDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCourseCompetencyMatrixService.class);

    UserCourseCompetencyMatrixService(DBI dbi) {
        this.userCompetencyMatrixDao = dbi.onDemand(UserCourseCompetencyMatrixDao.class);
    }

    UserCourseCompetencyMatrixModelResponse fetchUserCourseCompetencyMatrix(UserCourseCompetencyMatrixCommand command) {
        List<UserCourseCompetencyMatrixModel> userCompetencyMatrixModels = null;
        
        if (command.getMonth() != null && command.getYear() != null) {
        	userCompetencyMatrixModels = userCompetencyMatrixDao.fetchUserCourseCompetencyMatrixTillMonth(command.asBean());
        } else {
        	userCompetencyMatrixModels = userCompetencyMatrixDao.fetchUserCourseCompetencyMatrix(command.asBean());
        }
        
        if (userCompetencyMatrixModels.isEmpty()) {
            return new UserCourseCompetencyMatrixModelResponse();
        } else {
        	Timestamp lastUpdated = userCompetencyMatrixDao.fetchLastUpdatedTime(command.getUser(), command.getSubject());
            return UserCourseCompetencyMatrixModelResponseBuilder.build(userCompetencyMatrixModels, lastUpdated);
        }
    }
}
