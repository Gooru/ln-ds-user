package org.gooru.ds.user.processor.userprofile;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 17/1/18.
 */
class UserProfileService {
    private final UserProfileDao userProfileDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileService.class);

    UserProfileService(DBI dbi) {
        userProfileDao = dbi.onDemand(UserProfileDao.class);
    }

    UserProfileModel fetchUserProfile(UserProfileCommand command) {
        UserProfileModel model = userProfileDao.fetchUserProfile(command.asBean());
        if (model != null) {
            return model;
        } else {
            model = new UserProfileModel();
            model.setUserId(command.getUser());
            return model;
        }
    }
}
