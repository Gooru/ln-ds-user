package org.gooru.ds.user.processor.usercoursecompetencymatrix;

import java.sql.Timestamp;
import java.util.List;

import org.skife.jdbi.v2.DBI;

/**
 * @author ashish on 13/2/18.
 */
class UserCourseCompetencyMatrixService {

	private final UserCourseCompetencyMatrixDao userCompetencyMatrixDao;

	UserCourseCompetencyMatrixService(DBI dbi) {
		this.userCompetencyMatrixDao = dbi.onDemand(UserCourseCompetencyMatrixDao.class);
	}

	UserCourseCompetencyMatrixModelResponse fetchUserCourseCompetencyMatrix(UserCourseCompetencyMatrixCommand command) {
//		final List<UserCourseCompetencyMatrixModel> userCompetencyMatrixModels = userCompetencyMatrixDao
//				.fetchUserCourseCompetencyMatrixTillMonth(command.asBean());
		
		//TEMPORARY CODE: For details please refer UserDomainCompetencyMatrixService.java & NILE-2786 
		List<UserCourseCompetencyMatrixModel> userCompetencyMatrixModels = null;		
		if (command.getMonth() != null && command.getYear() != null) {
//			userCompetencyMatrixModels = userCompetencyMatrixDao
//					.fetchUserDomainCompetencyMatrixTillMonth(command.asBean());
			userCompetencyMatrixModels = userCompetencyMatrixDao.fetchUserCourseCompetencyMatrix(command.asBean());
		} else {
			userCompetencyMatrixModels = userCompetencyMatrixDao.fetchUserCourseCompetencyMatrix(command.asBean());
		}
		//END TEMPORARY CODE ******************************************************

		if (userCompetencyMatrixModels.isEmpty()) {
			return new UserCourseCompetencyMatrixModelResponse();
		} else {
			Timestamp lastUpdated = userCompetencyMatrixDao.fetchLastUpdatedTime(command.getUser(),
					command.getSubject());
			return UserCourseCompetencyMatrixModelResponseBuilder.build(userCompetencyMatrixModels, lastUpdated);
		}
	}
}
