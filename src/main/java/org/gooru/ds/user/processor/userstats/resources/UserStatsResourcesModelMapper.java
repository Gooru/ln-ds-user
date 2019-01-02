package org.gooru.ds.user.processor.userstats.resources;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 */
public class UserStatsResourcesModelMapper implements ResultSetMapper<UserStatsResourcesModel> {

  @Override
  public UserStatsResourcesModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserStatsResourcesModel model = new UserStatsResourcesModel();
    model.setResourceId(r.getString(MapperFields.RESOURCE_ID));
    model.setResourceTitle(r.getString(MapperFields.RESOURCE_TITLE));
    model.setPathId(r.getLong(MapperFields.PATH_ID));
    model.setResourceTimeSpent(r.getLong(MapperFields.RESOURCE_TIMESPENT));

    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String RESOURCE_ID = "resource_id";
    private static final String RESOURCE_TITLE = "resource_title";
    private static final String PATH_ID = "path_id";
    private static final String RESOURCE_TIMESPENT = "resource_time_spent";

  }

}
