
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 17-Oct-2019
 */
public class StrugglingCompetencyModelMapper implements ResultSetMapper<StrugglingCompetencyModel> {

  @Override
  public StrugglingCompetencyModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    StrugglingCompetencyModel model = new StrugglingCompetencyModel();
    model.setCompCode(r.getString("tx_comp_code"));
    model.setUserId(r.getString("user_id"));
    return model;
  }

}
