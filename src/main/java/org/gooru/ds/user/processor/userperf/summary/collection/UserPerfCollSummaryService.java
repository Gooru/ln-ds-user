package org.gooru.ds.user.processor.userperf.summary.collection;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserPerfCollSummaryService {

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
