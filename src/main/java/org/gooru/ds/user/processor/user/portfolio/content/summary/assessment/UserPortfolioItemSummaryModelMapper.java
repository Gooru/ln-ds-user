package org.gooru.ds.user.processor.user.portfolio.content.summary.assessment;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author renuka
 */
public class UserPortfolioItemSummaryModelMapper implements ResultSetMapper<UserPortfolioItemSummaryModel> {

  @Override
  public UserPortfolioItemSummaryModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPortfolioItemSummaryModel model = new UserPortfolioItemSummaryModel();
    model.setId(r.getString(MapperFields.ID));
    model.setType(r.getString(MapperFields.TYPE));
    model.setTimespent(r.getLong(MapperFields.TIMESPENT));
    model.setScore(r.getDouble(MapperFields.SCORE));
    model.setReaction(r.getInt(MapperFields.REACTION));
    model.setEventTime(r.getString(MapperFields.UPDATED_AT));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String ID = "collection_id";
    private static final String TYPE = "collection_type";
    private static final String TIMESPENT = "timespent";
    private static final String SCORE = "score";
    private static final String REACTION = "reaction";
    private static final String UPDATED_AT = "updated_at";
  }

}
