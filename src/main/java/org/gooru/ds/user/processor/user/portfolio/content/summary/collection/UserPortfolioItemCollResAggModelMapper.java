package org.gooru.ds.user.processor.user.portfolio.content.summary.collection;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author renuka
 */
public class UserPortfolioItemCollResAggModelMapper implements ResultSetMapper<UserPortfolioItemCollResAggModel> {

  @Override
  public UserPortfolioItemCollResAggModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPortfolioItemCollResAggModel model = new UserPortfolioItemCollResAggModel();
    model.setId(r.getString(MapperFields.RESOURCE_ID));
    model.setResourceType(r.getString(MapperFields.RESOURCE_TYPE));
    model.setQuestionType(r.getString(MapperFields.QUESTION_TYPE));
    model.setTimespent(r.getLong(MapperFields.TIMESPENT));
    model.setAttempts(r.getLong(MapperFields.ATTEMPTS));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }
    private static final String RESOURCE_ID = "resource_id";
    private static final String RESOURCE_TYPE = "resource_type";
    private static final String QUESTION_TYPE = "question_type";
    private static final String TIMESPENT = "timespent";
    private static final String ATTEMPTS = "attempts";
  }

}
