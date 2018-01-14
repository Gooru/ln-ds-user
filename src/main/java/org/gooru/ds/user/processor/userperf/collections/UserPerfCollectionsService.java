package org.gooru.ds.user.processor.userperf.collections;

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

    public UserPerfCollectionsModel fetchUserCollectionsPerf(UserPerfCollectionsCommand command) {
    	UserPerfCollectionsModel result = userPerfCollectionsDao.fetchUserPerfCollections(command.asBean());
        return result != null ? result : new UserPerfCollectionsModel();
    }




}
