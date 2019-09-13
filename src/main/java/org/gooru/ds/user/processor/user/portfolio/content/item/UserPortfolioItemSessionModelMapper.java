package org.gooru.ds.user.processor.user.portfolio.content.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author renuka
 */
public class UserPortfolioItemSessionModelMapper implements ResultSetMapper<Map<String, Object>> {

  @Override
  public Map<String, Object> map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    Map<String, Object> model = new HashMap<>();
    model.put(MapperFields.SESSION_ID, r.getString(MapperFields.SESSION_ID));
    model.put(MapperFields.DCA_CONTENT_ID, r.getLong(MapperFields.DCA_CONTENT_ID));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String SESSION_ID = "session_id";
    private static final String DCA_CONTENT_ID = "dca_content_id";
  }

}
