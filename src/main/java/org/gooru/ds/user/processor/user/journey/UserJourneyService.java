package org.gooru.ds.user.processor.user.journey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserJourneyService {

    private final UserJourneyDao userJourneyDao;
    private UserJourneyCommand command;
    private List<String> classIds;
    private List<String> courseIds;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserJourneyService.class);

    UserJourneyService(DBI dbi) {
        this.userJourneyDao = dbi.onDemand(UserJourneyDao.class);
    }

    public UserJourneyModelResponse fetchUserJourneys(UserJourneyCommand command) {
        this.command = command;
        classIds = this.command.getClassId();
        courseIds = this.command.getCourseId();
        List<UserJourneyModel> model = new ArrayList<>();
        if (!classIds.isEmpty()) {
            model = fetchClassJourneys(this.command, classIds);
        } else if (!courseIds.isEmpty()) {
            model = fetchILJourneys(this.command, courseIds);
        }
        UserJourneyModelResponse result = new UserJourneyModelResponse();
        result.setJourneys(model);
        return result;
    }

    private List<UserJourneyModel> fetchClassJourneys(UserJourneyCommand command, List<String> classIds) {
        if (!classIds.isEmpty()) {
            return userJourneyDao.fetchUserClassJourney(command.asBean(),
                PGArrayUtils.convertFromListStringToSqlArrayOfString(classIds));

        } else {
            return Collections.emptyList();
        }
    }

    private List<UserJourneyModel> fetchILJourneys(UserJourneyCommand command, List<String> courseIds) {
        if (!courseIds.isEmpty()) {
            return userJourneyDao
                .fetchUserILJourney(command.asBean(), PGArrayUtils.convertFromListStringToSqlArrayOfString(courseIds));

        } else {
            return Collections.emptyList();
        }
    }
}
