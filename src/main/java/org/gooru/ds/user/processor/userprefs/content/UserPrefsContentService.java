package org.gooru.ds.user.processor.userprefs.content;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsContentService {

  private final UserPrefsContentDao userPrefsContentDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserPrefsContentService.class);

  UserPrefsContentService(DBI dbi) {
    this.userPrefsContentDao = dbi.onDemand(UserPrefsContentDao.class);
  }

  public UserPrefsContentModel fetchUserPrefsContent(UserPrefsContentCommand command) {
    UserPrefsContentModel result = userPrefsContentDao.fetchUserPrefsContent(command.asBean());
    return result != null ? result : new UserPrefsContentModel();
  }

}
