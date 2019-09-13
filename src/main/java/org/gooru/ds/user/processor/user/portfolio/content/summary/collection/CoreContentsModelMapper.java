package org.gooru.ds.user.processor.user.portfolio.content.summary.collection;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author renuka
 */
public class CoreContentsModelMapper implements ResultSetMapper<CoreContentsModel> {

  private static final String ID = "id";
  private static final String TITLE = "title";
  private static final String CONTENT_FORMAT = "content_format";
  private static final String SUB_FORMAT = "content_subformat";

  @Override
  public CoreContentsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    CoreContentsModel model = new CoreContentsModel();
    model.setId(r.getString(ID));
    model.setTitle(r.getString(TITLE));
    model.setType(r.getString(CONTENT_FORMAT));
    model.setSubType(r.getString(SUB_FORMAT));
    return model;
  }

}
