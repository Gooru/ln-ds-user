package org.gooru.ds.user.processor.activeuserlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 11/1/18.
 */
class ActiveUserListService {

    private final ActiveUserListDao activeUserListDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveUserListService.class);
    private ActiveUserListCommand command;

    ActiveUserListService(DBI dbi) {
        this.activeUserListDao = dbi.onDemand(ActiveUserListDao.class);
    }

    public ActiveUserListModelResponse fetchActiveUserList(ActiveUserListCommand command) {
        this.command = command;
        List<String> userIds = activeUserListDao.fetchActiveUserListForSubject(command.asBean());
        return fetchUserDetailsInSpecifiedOrder(userIds);
    }

    private ActiveUserListModelResponse fetchUserDetailsInSpecifiedOrder(List<String> userIds) {
        if (!userIds.isEmpty()) {
            List<ActiveUserListModel> models = activeUserListDao
                .fetchUserDetails(command.asBean(), PGArrayUtils.convertFromListStringToSqlArrayOfString(userIds));
            return fixupOrdering(models, userIds);

        } else {
            return new ActiveUserListModelResponse();
        }
    }

    private ActiveUserListModelResponse fixupOrdering(List<ActiveUserListModel> models, List<String> userIds) {
        Map<Integer, ActiveUserListModel> tree = new TreeMap<>();
        for (ActiveUserListModel model : models) {
            tree.put(userIds.indexOf(model.getUserId()), model);
        }
        ActiveUserListModelResponse result = new ActiveUserListModelResponse();
        result.setUsers(new ArrayList<>(tree.values()));
        return result;
    }
}
