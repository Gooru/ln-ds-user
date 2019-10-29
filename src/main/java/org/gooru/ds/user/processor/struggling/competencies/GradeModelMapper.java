
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 18-Oct-2019
 */
public class GradeModelMapper implements ResultSetMapper<GradeModel> {

  @Override
  public GradeModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    GradeModel model = new GradeModel();
    model.setId(r.getLong("id"));
    model.setGrade(r.getString("grade"));
    model.setGradeSeq(r.getInt("grade_seq"));
    model.setDescription(r.getString("description"));
    model.setFwCode(r.getString("fw_code"));
    return model;
  }

}
