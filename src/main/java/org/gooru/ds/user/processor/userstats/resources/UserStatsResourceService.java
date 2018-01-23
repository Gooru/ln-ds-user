package org.gooru.ds.user.processor.userstats.resources;

import java.util.List;

import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesCommand;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesDao;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesModel;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesModelResponse;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourceService;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mukul@gooru
 */
public class UserStatsResourceService {
	

    private final UserStatsResourcesDao userStatsResourceDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsResourceService.class);

    UserStatsResourceService(DBI dbi) {
        this.userStatsResourceDao = dbi.onDemand(UserStatsResourcesDao.class);
    }

    public UserStatsResourcesModelResponse fetchUserResourceStats(UserStatsResourcesCommand command) {
        List<UserStatsResourcesModel> models = userStatsResourceDao.fetchUserStatsResource(command.asBean());
        UserStatsResourcesModelResponse result = new UserStatsResourcesModelResponse();
        result.setLessons(models);
        return result;
    }


}
