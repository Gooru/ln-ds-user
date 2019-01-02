package org.gooru.ds.user.processor.userstats.provider;

import java.util.List;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/1/18.
 */
class UserStatsProviderService {

  private final UserStatsProviderDao userStatsProviderDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsProviderService.class);

  UserStatsProviderService(DBI dbi) {
    this.userStatsProviderDao = dbi.onDemand(UserStatsProviderDao.class);
  }

  public UserStatsProviderModelResponse fetchUserStatsProvider(UserStatsProviderCommand command) {
    List<UserStatsProviderModel> models =
        userStatsProviderDao.fetchUserStatsProviders(command.asBean());
    UserStatsProviderModelResponse result = new UserStatsProviderModelResponse();
    result.setProviders(models);
    return result;
  }

}
