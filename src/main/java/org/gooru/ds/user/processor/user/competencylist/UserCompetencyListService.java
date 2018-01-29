package org.gooru.ds.user.processor.user.competencylist;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCompetencyListService {

    private final UserCompetencyListDao userCompetencyListDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCompetencyListService.class);

    UserCompetencyListService(DBI dbi) {
        this.userCompetencyListDao = dbi.onDemand(UserCompetencyListDao.class);
    }

    public UserCompetencyListModelResponse fetchUserCompetencyList(UserCompetencyListCommand command) {
        List<UserCompetencyListModel> models = userCompetencyListDao.fetchUserCompetencyList(command.asBean());
        UserCompetencyListModelResponse result = new UserCompetencyListModelResponse();
        result.setCompetencyList(models);
        return result;
    }

}
