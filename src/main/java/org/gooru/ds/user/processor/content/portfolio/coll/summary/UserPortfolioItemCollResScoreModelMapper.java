package org.gooru.ds.user.processor.content.portfolio.coll.summary;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import io.vertx.core.json.JsonArray;

/**
 * @author renuka
 */
public class UserPortfolioItemCollResScoreModelMapper implements ResultSetMapper<UserPortfolioItemCollResScoreModel> {

  @Override
  public UserPortfolioItemCollResScoreModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPortfolioItemCollResScoreModel model = new UserPortfolioItemCollResScoreModel();
    model.setScore(r.getDouble(MapperFields.SCORE));
    model.setMaxScore(r.getDouble(MapperFields.MAX_SCORE));
    model.setAttemptStatus(r.getString(MapperFields.ATTEMPT_STATUS));
    model.setAnswerObject(r.getString(MapperFields.ANSWER_OBJECT) != null ? new JsonArray(r.getString(MapperFields.ANSWER_OBJECT)) : null);
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }
    private static final String SCORE = "score";
    private static final String MAX_SCORE = "max_score";
    private static final String ATTEMPT_STATUS = "attempt_status";
    private static final String ANSWER_OBJECT = "answer_object";
  }

}
