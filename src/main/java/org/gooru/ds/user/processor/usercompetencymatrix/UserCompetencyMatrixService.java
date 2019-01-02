package org.gooru.ds.user.processor.usercompetencymatrix;

import java.util.List;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 17/1/18.
 */
class UserCompetencyMatrixService {

  private final UserCompetencyMatrixDao userCompetencyMatrixDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserCompetencyMatrixService.class);

  UserCompetencyMatrixService(DBI dbi) {
    this.userCompetencyMatrixDao = dbi.onDemand(UserCompetencyMatrixDao.class);
  }

  UserCompetencyMatrixModelResponse fetchUserCompetencyMatrix(UserCompetencyMatrixCommand command) {
    final List<UserCompetencyMatrixModel> userCompetencyMatrixModels =
        userCompetencyMatrixDao.fetchUserCompetencyMatrix(command.asBean());
    if (userCompetencyMatrixModels.isEmpty()) {
      return new UserCompetencyMatrixModelResponse();
    } else {
      return UserCompetencyMatrixModelResponseBuilder.build(userCompetencyMatrixModels);
    }
  }
}
