package org.gooru.ds.user.processor.userstats.provider;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 13/1/18.
 */
public class UserStatsProviderModelMapper implements ResultSetMapper<UserStatsProviderModel> {

  @Override
  public UserStatsProviderModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserStatsProviderModel model = new UserStatsProviderModel();
    model.setProviderId(r.getLong(MapperFields.ID));
    model.setProviderName(r.getString(MapperFields.PROVIDER_NAME));
    model.setCount(r.getLong(MapperFields.COUNT));
    return model;
  }

  private static final class MapperFields {

    private MapperFields() {
      throw new AssertionError();
    }

    private static final String ID = "id";
    private static final String PROVIDER_NAME = "provider_name";
    private static final String COUNT = "counter";
  }

}
