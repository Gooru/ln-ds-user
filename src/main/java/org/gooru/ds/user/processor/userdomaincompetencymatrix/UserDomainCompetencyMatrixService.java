package org.gooru.ds.user.processor.userdomaincompetencymatrix;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/2/18.
 */
class UserDomainCompetencyMatrixService {

    private final UserDomainCompetencyMatrixDao userCompetencyMatrixDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDomainCompetencyMatrixService.class);

    UserDomainCompetencyMatrixService(DBI dbi) {
        this.userCompetencyMatrixDao = dbi.onDemand(UserDomainCompetencyMatrixDao.class);
    }

    UserDomainCompetencyMatrixModelResponse fetchUserDomainCompetencyMatrix(UserDomainCompetencyMatrixCommand command) {
        final List<UserDomainCompetencyMatrixModel> userCompetencyMatrixModels =
            userCompetencyMatrixDao.fetchUserDomainCompetencyMatrix(command.asBean());
        if (userCompetencyMatrixModels.isEmpty()) {
            return new UserDomainCompetencyMatrixModelResponse();
        } else {
            return UserDomainCompetencyMatrixModelResponseBuilder.build(userCompetencyMatrixModels);
        }
    }
}
