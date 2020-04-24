package org.gooru.ds.user.processor.competency.subjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import io.vertx.core.json.JsonObject;

public class TenantSubjectPrefModelMapper implements ResultSetMapper<TenantSubjectPrefModel> {

  @Override
  public TenantSubjectPrefModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    TenantSubjectPrefModel model = new TenantSubjectPrefModel();
    String value = r.getString(MapperFields.VALUE);
    if (value != null) {
      model.setSubjectPref(new JsonObject(value));
    }
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String VALUE = "value";

  }



}
