package org.gooru.ds.user.processor.competencymatrixcoordinates;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 17/1/18.
 */
public class CompetencyMatrixCoordinatesCourseModelMapper
    implements ResultSetMapper<CompetencyMatrixCoordinatesCourseModel> {

  @Override
  public CompetencyMatrixCoordinatesCourseModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    CompetencyMatrixCoordinatesCourseModel model = new CompetencyMatrixCoordinatesCourseModel();
    model.setCourseCode(r.getString(MapperFields.COURSE_CODE));
    model.setCourseName(r.getString(MapperFields.COURSE_NAME));
    model.setCourseSeq(r.getInt(MapperFields.COURSE_SEQ));
    return model;
  }

  static class MapperFields {
    public static final String COURSE_CODE = "tx_course_code";
    public static final String COURSE_NAME = "tx_course_name";
    public static final String COURSE_SEQ = "tx_course_seq";

    private MapperFields() {
      throw new AssertionError();
    }
  }
}
