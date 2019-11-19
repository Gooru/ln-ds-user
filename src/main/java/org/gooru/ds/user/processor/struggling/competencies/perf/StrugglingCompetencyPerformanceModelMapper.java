
package org.gooru.ds.user.processor.struggling.competencies.perf;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 12-Nov-2019
 */
public class StrugglingCompetencyPerformanceModelMapper
    implements ResultSetMapper<StrugglingCompetencyPerformanceModel> {

  @Override
  public StrugglingCompetencyPerformanceModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    StrugglingCompetencyPerformanceModel model = new StrugglingCompetencyPerformanceModel();
    model.setCollectionId(r.getString("collection_id"));
    model.setUserId(r.getString("user_id"));
    model.setScore(r.getDouble("score"));
    model.setUpdatedAt(r.getTimestamp("updated_at"));
    return model;
  }

}
