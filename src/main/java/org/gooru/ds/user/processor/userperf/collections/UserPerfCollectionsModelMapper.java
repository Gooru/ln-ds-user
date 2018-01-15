package org.gooru.ds.user.processor.userperf.collections;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author mukul@gooru
 */
public class UserPerfCollectionsModelMapper implements ResultSetMapper<UserPerfCollectionsModel> {

    @Override
    public UserPerfCollectionsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserPerfCollectionsModel model = new UserPerfCollectionsModel();
        model.setId(r.getString(MapperFields.COLLECTION_ID));
        model.setTitle(r.getString(MapperFields.COLLECTION_TITLE));        
        model.setTimeSpent(r.getLong(MapperFields.COLLECTION_TIMESPENT));        
        model.setReaction(r.getInt(MapperFields.COLLECTION_REACTION));

        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        //generically referred to as collection_id in analytics DB
        private static final String COLLECTION_ID = "collection_id";
        private static final String COLLECTION_TITLE = "collection_title";
        private static final String COLLECTION_TIMESPENT = "collection_time_spent";
        private static final String COLLECTION_REACTION = "collection_average_reaction";

    }

}
