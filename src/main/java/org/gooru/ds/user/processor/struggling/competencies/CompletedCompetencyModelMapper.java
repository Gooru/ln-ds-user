
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 24-Oct-2019
 */
public class CompletedCompetencyModelMapper implements ResultSetMapper<CompletedCompetencyModel> {

  @Override
  public CompletedCompetencyModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    CompletedCompetencyModel model = new CompletedCompetencyModel();
    model.setGutCode(r.getString("gut_code"));
    model.setUserId(r.getString("user_id"));
    return model;
  }

}
