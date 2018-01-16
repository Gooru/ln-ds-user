package org.gooru.ds.user.processor.userperf.summary.assessment;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserPerfAsmtSummaryService {

    private final UserPerfAsmtSummaryDao userPerfAsmtSummaryDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfAsmtSummaryService.class);

    UserPerfAsmtSummaryService(DBI dbi) {
        this.userPerfAsmtSummaryDao = dbi.onDemand(UserPerfAsmtSummaryDao.class);
    }

    public UserPerfAsmtSummaryModelResponse fetchUserAsmtSummary(UserPerfAsmtSummaryCommand command) {

        List<UserPerfAsmtSummaryModel> models = userPerfAsmtSummaryDao.fetchUserPerfAsmtSummary(command.asBean());
        UserPerfAsmtSummaryModelResponse result = new UserPerfAsmtSummaryModelResponse();
        result.setResources(models);
        return result;

    }

}
