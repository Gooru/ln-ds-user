package org.gooru.ds.user.processor.usercoursecompetencymatrix;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 13/2/18.
 */
public class UserCourseCompetencyMatrixModelMapper implements ResultSetMapper<UserCourseCompetencyMatrixModel> {

    @Override
    public UserCourseCompetencyMatrixModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserCourseCompetencyMatrixModel model = new UserCourseCompetencyMatrixModel();
        model.setCourseCode(r.getString(MapperFields.COURSE_CODE));
        model.setCompetencyCode(r.getString(MapperFields.COMPETENCY_CODE));
        model.setCompetencyName(r.getString(MapperFields.COMPETENCY_NAME));
        model.setCompetencyDesc(r.getString(MapperFields.COMPETENCY_DESC));
        model.setCompetencyStudentDesc(r.getString(MapperFields.COMPETENCY_STUDENT_DESC));
        model.setCompetencySeq(r.getInt(MapperFields.COMPETENCY_SEQ));
        model.setStatus(r.getInt(MapperFields.STATUS));
        return model;
    }

    static class MapperFields {
        static final String COURSE_CODE = "tx_course_code";
        static final String COMPETENCY_CODE = "tx_comp_code";
        static final String COMPETENCY_NAME = "tx_comp_name";
        static final String COMPETENCY_DESC = "tx_comp_desc";
        static final String COMPETENCY_STUDENT_DESC = "tx_comp_student_desc";
        static final String COMPETENCY_SEQ = "tx_comp_seq";
        static final String STATUS = "status";

        private MapperFields() {
            throw new AssertionError();
        }
    }
}
