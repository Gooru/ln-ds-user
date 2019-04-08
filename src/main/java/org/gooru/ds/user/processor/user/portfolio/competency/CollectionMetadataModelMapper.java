package org.gooru.ds.user.processor.user.portfolio.competency;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 */
public class CollectionMetadataModelMapper implements ResultSetMapper<CollectionMetadataModel> {

  private static final String ID = "id";
  private static final String TITLE = "title";

  @Override
  public CollectionMetadataModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    CollectionMetadataModel model = new CollectionMetadataModel();
    model.setId(r.getString(ID));
    model.setTitle(r.getString(TITLE));
    return model;
  }

}
