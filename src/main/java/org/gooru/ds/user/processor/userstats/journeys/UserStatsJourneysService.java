package org.gooru.ds.user.processor.userstats.journeys;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 12/1/18.
 */
class UserStatsJourneysService {

    private final UserStatsJourneysDao userStatsJourneysDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsJourneysService.class);

    UserStatsJourneysService(DBI dbi) {
        this.userStatsJourneysDao = dbi.onDemand(UserStatsJourneysDao.class);
    }

    public UserStatsJourneysModel fetchUserStatsJourneys(UserStatsJourneysCommand command) {
        UserStatsJourneysModel result = userStatsJourneysDao.fetchUserStatsJourneys(command.asBean());
        return result != null ? result : new UserStatsJourneysModel();
    }

}
