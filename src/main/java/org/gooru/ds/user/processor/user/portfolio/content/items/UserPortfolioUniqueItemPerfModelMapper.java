package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author renuka
 */
public class UserPortfolioUniqueItemPerfModelMapper implements ResultSetMapper<UserPortfolioUniqueItemPerfModel> {

  @Override
  public UserPortfolioUniqueItemPerfModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPortfolioUniqueItemPerfModel model = new UserPortfolioUniqueItemPerfModel();
    model.setId(r.getString(MapperFields.COLLECTION_ID));
    model.setType(r.getString(MapperFields.COLLECTION_TYPE));
    model.setTimespent(r.getLong(MapperFields.TIMESPENT));
    model.setScore(r.getDouble(MapperFields.SCORE));
    model.setMaxScore(r.getDouble(MapperFields.MAX_SCORE));
    model.setSessionId(r.getString(MapperFields.SESSION_ID));
    model.setContentSource(r.getString(MapperFields.CONTENT_SOURCE));
    model.setActivityTimestamp(r.getTimestamp(MapperFields.CREATED_AT));
    model.setStatus(!r.getBoolean(MapperFields.STATUS) ? "in-progress" : "complete");
    String gradingStatus = null;
    if (r.getObject(MapperFields.IS_GRADED) != null) {
      gradingStatus = "complete";
      if (!r.getBoolean(MapperFields.IS_GRADED)) {
        gradingStatus = "in-progress";
      }
    }

    model.setGradingStatus(gradingStatus);
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String COLLECTION_ID = "collection_id";
    private static final String COLLECTION_TYPE = "collection_type";
    private static final String TIMESPENT = "timespent";
    private static final String SCORE = "score";
    private static final String MAX_SCORE = "max_score";
    private static final String CREATED_AT = "created_at";
    private static final String STATUS = "status";
    private static final String IS_GRADED = "is_graded";
    private static final String CONTENT_SOURCE = "content_source";
    private static final String SESSION_ID = "session_id";

  }

}
