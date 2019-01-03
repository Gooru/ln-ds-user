package org.gooru.ds.user.processor.userstats.content;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 12/1/18.
 */
class UserStatsContentsService {

  private final UserStatsContentsDao userStatsContentsDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsContentsService.class);

  UserStatsContentsService(DBI dbi) {
    this.userStatsContentsDao = dbi.onDemand(UserStatsContentsDao.class);
  }

  public UserStatsContentsModel fetchUserStatsContents(UserStatsContentsCommand command) {
    UserStatsContentsModel result = userStatsContentsDao.fetchUserStatsContents(command.asBean());
    return result != null ? result : new UserStatsContentsModel();
  }

}
