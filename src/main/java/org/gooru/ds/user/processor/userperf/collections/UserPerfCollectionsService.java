package org.gooru.ds.user.processor.userperf.collections;

import java.util.List;

import org.gooru.ds.user.processor.userperf.collections.UserPerfCollectionsCommand;
import org.gooru.ds.user.processor.userperf.collections.UserPerfCollectionsDao;
import org.gooru.ds.user.processor.userperf.collections.UserPerfCollectionsModel;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
public class UserPerfCollectionsService {

    private final UserPerfCollectionsDao userPerfCollectionsDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCollectionsService.class);

    UserPerfCollectionsService(DBI dbi) {
        this.userPerfCollectionsDao = dbi.onDemand(UserPerfCollectionsDao.class);
    }

    public UserPerfCollectionsModelResponse fetchUserCollectionsPerf(UserPerfCollectionsCommand command) {
    	
        List<UserPerfCollectionsModel> models = userPerfCollectionsDao.fetchUserPerfCollections(command.asBean());
        UserPerfCollectionsModelResponse result = new UserPerfCollectionsModelResponse();
        result.setCollections(models);
        return result;

    }

}
