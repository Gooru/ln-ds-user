package org.gooru.ds.user.processor.userperf.competency.collections;

import java.util.List;

import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsCommand;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsDao;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsModel;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsModelResponse;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsService;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPerfCompetencyCollectionsService {

    private final UserPerfCompetencyCollectionsDao userPerfCompetencyCollectionsDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCompetencyCollectionsService.class);

    UserPerfCompetencyCollectionsService(DBI dbi) {
        this.userPerfCompetencyCollectionsDao = dbi.onDemand(UserPerfCompetencyCollectionsDao.class);
    }

    public UserPerfCompetencyCollectionsModelResponse fetchUserCollectionsPerf(UserPerfCompetencyCollectionsCommand command) {
        List<UserPerfCompetencyCollectionsModel> models = userPerfCompetencyCollectionsDao.fetchUserPerfCompetencyCollections(command.asBean());
        UserPerfCompetencyCollectionsModelResponse result = new UserPerfCompetencyCollectionsModelResponse();
        result.setCollections(models);
        return result;
    }

}
