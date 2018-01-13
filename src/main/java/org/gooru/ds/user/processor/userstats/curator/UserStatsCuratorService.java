package org.gooru.ds.user.processor.userstats.curator;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/1/18.
 */
class UserStatsCuratorService {

    private final UserStatsCuratorDao userStatsCuratorDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsCuratorService.class);

    UserStatsCuratorService(DBI dbi) {
        this.userStatsCuratorDao = dbi.onDemand(UserStatsCuratorDao.class);
    }

    public UserStatsCuratorModelResponse fetchUserStatsCurator(UserStatsCuratorCommand command) {
        List<UserStatsCuratorModel> models = userStatsCuratorDao.fetchUserStatsCurator(command.asBean());
        UserStatsCuratorModelResponse result = new UserStatsCuratorModelResponse();
        result.setCurators(models);
        return result;
    }

}
