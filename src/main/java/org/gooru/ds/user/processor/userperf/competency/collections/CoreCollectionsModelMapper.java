package org.gooru.ds.user.processor.userperf.competency.collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author gooru on 16-May-2018
 */
public class CoreCollectionsModelMapper implements ResultSetMapper<CoreCollectionsModel> {

  private static final String ID = "id";
  private static final String TITLE = "title";

  @Override
  public CoreCollectionsModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    CoreCollectionsModel model = new CoreCollectionsModel();
    model.setId(r.getString(ID));
    model.setTitle(r.getString(TITLE));
    return model;
  }

}
