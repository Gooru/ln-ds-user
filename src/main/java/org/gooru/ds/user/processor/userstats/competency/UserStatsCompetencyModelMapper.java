package org.gooru.ds.user.processor.userstats.competency;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 12/1/18.
 */
public class UserStatsCompetencyModelMapper implements ResultSetMapper<UserStatsCompetencyModel> {

  @Override
  public UserStatsCompetencyModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserStatsCompetencyModel model = new UserStatsCompetencyModel();
    model.setCompleted(r.getInt(MapperFields.COMPLETED));
    model.setInProgress(r.getInt(MapperFields.IN_PROGRESS));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String IN_PROGRESS = "in_progress";
    private static final String COMPLETED = "completed";
  }

}
