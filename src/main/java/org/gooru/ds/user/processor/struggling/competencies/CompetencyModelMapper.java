
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 14-Nov-2019
 */
public class CompetencyModelMapper implements ResultSetMapper<CompetencyModel> {

  @Override
  public CompetencyModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    CompetencyModel model = new CompetencyModel();
    model.setCompCode(r.getString("tx_comp_code"));
    model.setDisplayCode(r.getString("tx_comp_display_code"));
    return model;
  }

}
