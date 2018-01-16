package org.gooru.ds.user.processor.userperf.summary.collection;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author mukul@gooru
 */
public class UserPerfCollSummaryModelMapper implements ResultSetMapper<UserPerfCollSummaryModel> {

    @Override
    public UserPerfCollSummaryModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserPerfCollSummaryModel model = new UserPerfCollSummaryModel();
        model.setId(r.getString(MapperFields.RESOURCE_ID));
        //model.setTitle(r.getString(MapperFields.RESOURCE_TITLE));
        model.setType(r.getString(MapperFields.RESOURCE_TYPE));
        //model.setContentType(r.getString(MapperFields.RESOURCE_CONTENT_TYPE));
        model.setTimeSpent(r.getLong(MapperFields.RESOURCE_TIMESPENT));
        model.setScore(r.getDouble(MapperFields.RESOURCE_SCORE));
        model.setReaction(r.getInt(MapperFields.RESOURCE_REACTION));
        model.setPathId(r.getLong(MapperFields.RESOURCE_PATHID));
        //model.setSequenceId(r.getInt(MapperFields.RESOURCE_SEQUENCE_ID));

        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String RESOURCE_ID = "resource_id";
        private static final String RESOURCE_TITLE = "resource_title";
        private static final String RESOURCE_TYPE = "resource_type";
        private static final String RESOURCE_CONTENT_TYPE = "resource_content_type";
        private static final String RESOURCE_TIMESPENT = "time_spent";
        private static final String RESOURCE_SCORE = "score";
        private static final String RESOURCE_REACTION = "reaction";
        private static final String RESOURCE_PATHID = "path_id";
        private static final String RESOURCE_SEQUENCE_ID = "sequence_id";

    }

}
