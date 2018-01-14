package org.gooru.ds.user.processor.userperf.lesson;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.gooru.ds.user.processor.userperf.lesson.UserPerfLessonModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 */
public class UserPerfLessonModelMapper implements ResultSetMapper<UserPerfLessonModel> {
	
	
    @Override
    public UserPerfLessonModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserPerfLessonModel model = new UserPerfLessonModel();
        model.setLessonId(r.getString(MapperFields.LESSON_ID));
        model.setLessonTitle(r.getString(MapperFields.LESSON_TITLE));
        model.setLessonAsmtScore(r.getDouble(MapperFields.LESSON_ASMT_SCORE));
        model.setLessonAsmtTimeSpent(r.getInt(MapperFields.LESSON_ASMT_TIMESPENT));
        model.setLessonCollTimeSpent(r.getInt(MapperFields.LESSON_COLL_TIMESPENT));
        
        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String LESSON_ID = "lesson_id";        
        private static final String LESSON_TITLE = "lesson_title";
        private static final String LESSON_ASMT_TIMESPENT = "lesson_asmt_time_spent";
        private static final String LESSON_ASMT_SCORE = "lesson_asmt_score";
        private static final String LESSON_COLL_TIMESPENT = "lesson_coll_time_spent";

    }


}
