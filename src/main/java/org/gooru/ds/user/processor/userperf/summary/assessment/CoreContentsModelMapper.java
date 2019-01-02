package org.gooru.ds.user.processor.userperf.summary.assessment;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru on 07-Jun-2018
 */
public class CoreContentsModelMapper implements ResultSetMapper<CoreContentsModel> {

  private static final String ID = "id";
  private static final String TITLE = "title";
  private static final String SUB_FORMAT = "content_subformat";

  @Override
  public CoreContentsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    CoreContentsModel model = new CoreContentsModel();
    model.setId(r.getString(ID));
    model.setTitle(r.getString(TITLE));
    model.setContentType(r.getString(SUB_FORMAT));
    return model;
  }

}
