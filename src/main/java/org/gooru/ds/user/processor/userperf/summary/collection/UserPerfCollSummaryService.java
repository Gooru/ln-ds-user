package org.gooru.ds.user.processor.userperf.summary.collection;

import java.util.List;

import org.gooru.ds.user.processor.userperf.summary.collection.UserPerfCollSummaryCommand;
import org.gooru.ds.user.processor.userperf.summary.collection.UserPerfCollSummaryDao;
import org.gooru.ds.user.processor.userperf.summary.collection.UserPerfCollSummaryModel;
import org.gooru.ds.user.processor.userperf.summary.collection.UserPerfCollSummaryModelResponse;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mukul@gooru
 */
public class UserPerfCollSummaryService {
	
    private final UserPerfCollSummaryDao userPerfCollSummaryDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCollSummaryService.class);

    UserPerfCollSummaryService(DBI dbi) {
        this.userPerfCollSummaryDao = dbi.onDemand(UserPerfCollSummaryDao.class);
    }

    public UserPerfCollSummaryModelResponse fetchUserCollSummary(UserPerfCollSummaryCommand command) {
    	
        List<UserPerfCollSummaryModel> models = userPerfCollSummaryDao.fetchUserPerfCollSummary(command.asBean());
        UserPerfCollSummaryModelResponse result = new UserPerfCollSummaryModelResponse();
        result.setResources(models);
        return result;

    }

}
