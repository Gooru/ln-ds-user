package org.gooru.ds.user.processor.userstats.competency;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 12/1/18.
 */
class UserStatsCompetencyService {

    private final UserStatsCompetencyDao userStatsCompetencyDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsCompetencyService.class);

    UserStatsCompetencyService(DBI dbi) {
        this.userStatsCompetencyDao = dbi.onDemand(UserStatsCompetencyDao.class);
    }

    public UserStatsCompetencyModel fetchUserStatsCompetency(UserStatsCompetencyCommand command) {
        UserStatsCompetencyModel result = userStatsCompetencyDao.fetchUserStatsCompetency(command.asBean());
        return result != null ? result : new UserStatsCompetencyModel();
    }

}
