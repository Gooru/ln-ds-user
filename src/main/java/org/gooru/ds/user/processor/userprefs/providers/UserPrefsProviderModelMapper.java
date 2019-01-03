package org.gooru.ds.user.processor.userprefs.providers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 13/1/18.
 */
public class UserPrefsProviderModelMapper implements ResultSetMapper<UserPrefsProviderModel> {

  @Override
  public UserPrefsProviderModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPrefsProviderModel model = new UserPrefsProviderModel();
    model.setProviderId(r.getLong(MapperFields.ID));
    model.setProviderName(r.getString(MapperFields.PROVIDER_NAME));
    model.setPref(r.getFloat(MapperFields.PREF));
    return model;
  }

  private static final class MapperFields {

    private MapperFields() {
      throw new AssertionError();
    }

    private static final String ID = "id";
    private static final String PROVIDER_NAME = "provider_name";
    private static final String PREF = "pref";
  }

}
