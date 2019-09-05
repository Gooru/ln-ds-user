package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author renuka
 */
public class UserPortfolioMasteryStatusModelMapper implements ResultSetMapper<UserPortfolioMasteryStatusModel> {

  @Override
  public UserPortfolioMasteryStatusModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPortfolioMasteryStatusModel model = new UserPortfolioMasteryStatusModel();
    model.setId(r.getString(MapperFields.COLLECTION_ID));
    model.setGutCode(r.getString(MapperFields.GUT_CODE));
    model.setGutDisplayCode(r.getString(MapperFields.GUT_DISPLAY_CODE));
    model.setStatus(r.getInt(MapperFields.STATUS));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String COLLECTION_ID = "collection_id";
    private static final String GUT_CODE = "gut_code";
    private static final String GUT_DISPLAY_CODE = "tx_comp_display_code";
    private static final String STATUS = "status";

  }

}
