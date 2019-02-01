
package org.gooru.ds.user.processor.domain.competency.perf.report;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class UserCompetencyPerformanceModelMapper
    implements ResultSetMapper<UserCompetencyPerformanceModel> {

  @Override
  public UserCompetencyPerformanceModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserCompetencyPerformanceModel model = new UserCompetencyPerformanceModel();
    model.setScore(r.getDouble("score"));
    model.setUserId(r.getString("user_id"));
    model.setStatus(r.getInt("status"));
    return model;
  }

}
