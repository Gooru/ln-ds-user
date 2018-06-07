package org.gooru.ds.user.processor.userperf.summary.assessment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author mukul@gooru
 */
public class UserPerfAsmtSummaryModelMapper implements ResultSetMapper<UserPerfAsmtSummaryModel> {

    @Override
    public UserPerfAsmtSummaryModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserPerfAsmtSummaryModel model = new UserPerfAsmtSummaryModel();
        model.setId(r.getString(MapperFields.RESOURCE_ID));
        model.setType(r.getString(MapperFields.RESOURCE_TYPE));
        model.setTimeSpent(r.getLong(MapperFields.RESOURCE_TIMESPENT));
        model.setScore(r.getDouble(MapperFields.RESOURCE_SCORE));
        model.setReaction(r.getInt(MapperFields.RESOURCE_REACTION));
        model.setPathId(r.getLong(MapperFields.RESOURCE_PATH_ID));
        //model.setSequenceId(r.getInt(MapperFields.RESOURCE_SEQUENCE_ID));

        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String RESOURCE_ID = "resource_id";
        private static final String RESOURCE_TYPE = "resource_type";
        private static final String RESOURCE_TIMESPENT = "time_spent";
        private static final String RESOURCE_SCORE = "score";
        private static final String RESOURCE_REACTION = "reaction";
        private static final String RESOURCE_PATH_ID = "path_id";
        private static final String RESOURCE_SEQUENCE_ID = "sequence_id";

    }

}
