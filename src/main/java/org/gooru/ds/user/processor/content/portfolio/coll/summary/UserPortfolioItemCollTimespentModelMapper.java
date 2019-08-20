package org.gooru.ds.user.processor.content.portfolio.coll.summary;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author renuka
 */
public class UserPortfolioItemCollTimespentModelMapper implements ResultSetMapper<UserPortfolioItemCollTimespentModel> {

  @Override
  public UserPortfolioItemCollTimespentModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPortfolioItemCollTimespentModel model = new UserPortfolioItemCollTimespentModel();
    model.setAttempts(r.getLong(MapperFields.ATTEMPTS));
    model.setTimespent(r.getLong(MapperFields.TIMESPENT));
    model.setCollectionType(r.getString(MapperFields.COLLECTION_TYPE));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String TIMESPENT = "timespent";
    private static final String ATTEMPTS = "attempts";
    private static final String COLLECTION_TYPE = "collection_type";
  }

}
