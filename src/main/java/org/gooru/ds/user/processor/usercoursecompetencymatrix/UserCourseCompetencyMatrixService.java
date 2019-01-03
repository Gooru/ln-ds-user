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

  UserCourseCompetencyMatrixModelResponse fetchUserCourseCompetencyMatrix(
      UserCourseCompetencyMatrixCommand command) {
    final List<UserCourseCompetencyMatrixModel> userCompetencyMatrixModels =
        userCompetencyMatrixDao.fetchUserCourseCompetencyMatrixTillMonth(command.asBean());

    if (userCompetencyMatrixModels.isEmpty()) {
      return new UserCourseCompetencyMatrixModelResponse();
    } else {
      Timestamp lastUpdated = userCompetencyMatrixDao.fetchLastUpdatedTime(command.asBean());
      return UserCourseCompetencyMatrixModelResponseBuilder.build(userCompetencyMatrixModels,
          lastUpdated);
    }
  }
}
