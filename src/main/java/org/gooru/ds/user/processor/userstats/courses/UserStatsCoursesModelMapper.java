package org.gooru.ds.user.processor.userstats.courses;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 13/1/18.
 */
public class UserStatsCoursesModelMapper implements ResultSetMapper<UserStatsCoursesModel> {

    @Override
    public UserStatsCoursesModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserStatsCoursesModel model = new UserStatsCoursesModel();
        model.setClassId(r.getString(MapperFields.CLASS_ID));
        model.setCourseId(r.getString(MapperFields.COURSE_ID));
        model.setCompletion(r.getFloat(MapperFields.COMPLETION));
        model.setPerformance(r.getFloat(MapperFields.PERFORMANCE));
        model.setTimespent(r.getLong(MapperFields.TIMESPENT));
        model.setStartedInDuration(r.getBoolean(MapperFields.STARTED_IN_DURATION));
        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String COURSE_ID = "course_id";
        private static final String CLASS_ID = "class_id";
        private static final String COMPLETION = "completion";
        private static final String PERFORMANCE = "performance";
        private static final String TIMESPENT = "timespent";
        private static final String STARTED_IN_DURATION = "started_in_duration";
    }

}
