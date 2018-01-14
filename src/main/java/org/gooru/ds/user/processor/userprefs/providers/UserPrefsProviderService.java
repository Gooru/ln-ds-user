package org.gooru.ds.user.processor.userprefs.providers;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsProviderService {

    private final UserPrefsProviderDao userPrefsProviderDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPrefsProviderService.class);

    UserPrefsProviderService(DBI dbi) {
        this.userPrefsProviderDao = dbi.onDemand(UserPrefsProviderDao.class);
    }

    public UserPrefsProviderModelResponse fetchUserPrefsProvider(UserPrefsProviderCommand command) {
        List<UserPrefsProviderModel> models = userPrefsProviderDao.fetchUserPrefsProvider(command.asBean());
        UserPrefsProviderModelResponse result = new UserPrefsProviderModelResponse();
        result.setProviders(models);
        return result;
    }

}
