package org.gooru.ds.user.processor.userperf.course;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.gooru.ds.user.processor.userperf.course.UserPerfCourseBaseModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 */
public class UserPerfCourseBaseModelMapper implements ResultSetMapper<UserPerfCourseBaseModel> {
	
    @Override
    public UserPerfCourseBaseModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserPerfCourseBaseModel model = new UserPerfCourseBaseModel();
        model.setClassId(r.getString(MapperFields.CLASS_ID));
        model.setClassCode(r.getString(MapperFields.CLASS_CODE));
        model.setClassTitle(r.getString(MapperFields.CLASS_TITLE));
        model.setCourseId(r.getString(MapperFields.COURSE_ID));
        model.setCourseTitle(r.getString(MapperFields.COURSE_TITLE));
        model.setUnitId(r.getString(MapperFields.UNIT_ID));
        model.setUnitTitle(r.getString(MapperFields.UNIT_TITLE));
        model.setCourseAsmtScore(r.getFloat(MapperFields.COURSE_ASMT_SCORE));
        model.setCourseAsmtTimeSpent(r.getLong(MapperFields.COURSE_ASMT_TIMESPENT));
        model.setCourseCollTimeSpent(r.getLong(MapperFields.COURSE_COLL_TIMESPENT));
        model.setUnitAsmtScore(r.getFloat(MapperFields.UNIT_ASMT_SCORE));
        model.setUnitAsmtTimeSpent(r.getLong(MapperFields.UNIT_ASMT_TIMESPENT));
        model.setUnitCollTimeSpent(r.getLong(MapperFields.UNIT_COLL_TIMESPENT));
        model.setCourseAssessmentsComplete(r.getInt(MapperFields.COURSE_ASSESSMENTS_COMPLETE));
        model.setTotalAssessments(r.getInt(MapperFields.TOTAL_ASSESSMENTS));

        
        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String CLASS_ID = "class_id";
        private static final String CLASS_CODE = "class_code";
        private static final String CLASS_TITLE = "class_title";
        private static final String COURSE_ID = "course_id";        
        private static final String COURSE_TITLE = "course_title";
        private static final String UNIT_ID = "unit_id";        
        private static final String UNIT_TITLE = "unit_title";
        private static final String COURSE_ASMT_TIMESPENT = "course_asmt_time_spent";
        private static final String COURSE_ASMT_SCORE = "course_asmt_score";
        private static final String COURSE_COLL_TIMESPENT = "course_coll_time_spent";
        private static final String UNIT_ASMT_TIMESPENT = "unit_asmt_time_spent";
        private static final String UNIT_ASMT_SCORE = "unit_asmt_score";
        private static final String UNIT_COLL_TIMESPENT = "unit_coll_time_spent";
        private static final String COURSE_ASSESSMENTS_COMPLETE = "course_assessments_complete";
        private static final String TOTAL_ASSESSMENTS = "total_assessments";

        
    }

}
