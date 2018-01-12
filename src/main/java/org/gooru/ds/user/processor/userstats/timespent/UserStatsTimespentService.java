package org.gooru.ds.user.processor.userstats.timespent;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 12/1/18.
 */
class UserStatsTimespentService {

    private final UserStatsTimespentDao userStatsTimespentDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsTimespentService.class);

    UserStatsTimespentService(DBI dbi) {
        this.userStatsTimespentDao = dbi.onDemand(UserStatsTimespentDao.class);
    }

    public UserStatsTimespentModel fetchUserStatsTimespent(UserStatsTimespentCommand command) {
        UserStatsTimespentModel result = userStatsTimespentDao.fetchUserStatsTimespent(command.asBean());
        return result != null ? result : new UserStatsTimespentModel();
    }

}
