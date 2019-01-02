package org.gooru.ds.user.processor.userprefs.curators;

import java.util.List;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsCuratorService {

  private final UserPrefsCuratorDao userPrefsCuratorDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserPrefsCuratorService.class);

  UserPrefsCuratorService(DBI dbi) {
    this.userPrefsCuratorDao = dbi.onDemand(UserPrefsCuratorDao.class);
  }

  public UserPrefsCuratorModelResponse fetchUserPrefsCurator(UserPrefsCuratorCommand command) {
    List<UserPrefsCuratorModel> models =
        userPrefsCuratorDao.fetchUserPrefsCurator(command.asBean());
    UserPrefsCuratorModelResponse result = new UserPrefsCuratorModelResponse();
    result.setCurators(models);
    return result;
  }

}
