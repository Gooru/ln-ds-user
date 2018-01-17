package org.gooru.ds.user.processor.usergrades;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 17/1/18.
 */
class UserGradesService {

    private final UserGradesDao userGradesDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGradesService.class);

    UserGradesService(DBI dbi) {
        userGradesDao = dbi.onDemand(UserGradesDao.class);
    }

    UserGradesModelResponse fetchUserGrades(UserGradesCommand command) {
        List<UserGradesModel> models = userGradesDao.fetchUserGrades(command.asBean());
        UserGradesModelResponse result = new UserGradesModelResponse();
        result.setGrades(models);
        return result;
    }
}
