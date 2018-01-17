package org.gooru.ds.user.processor.usergrades;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 17/1/18.
 */
public class UserGradesModelMapper implements ResultSetMapper<UserGradesModel> {
    @Override
    public UserGradesModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserGradesModel result = new UserGradesModel();
        result.setSubjectCode(r.getString(MapperFields.SUBJECT_CODE));
        result.setSubjectName(r.getString(MapperFields.SUBJECT_NAME));
        result.setGrade(r.getString(MapperFields.GRADE));
        return result;
    }

    private static final class MapperFields {

        private MapperFields() {
            throw new AssertionError();
        }

        private static final String SUBJECT_CODE = "subject_code";
        private static final String SUBJECT_NAME = "subject_name";
        private static final String GRADE = "grade";
    }

}
