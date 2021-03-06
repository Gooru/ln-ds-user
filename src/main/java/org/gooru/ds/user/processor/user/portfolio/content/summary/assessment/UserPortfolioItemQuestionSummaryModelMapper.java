package org.gooru.ds.user.processor.user.portfolio.content.summary.assessment;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import io.vertx.core.json.JsonArray;

/**
 * @author renuka
 */
public class UserPortfolioItemQuestionSummaryModelMapper implements ResultSetMapper<UserPortfolioItemQuestionSummaryModel> {

  @Override
  public UserPortfolioItemQuestionSummaryModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPortfolioItemQuestionSummaryModel model = new UserPortfolioItemQuestionSummaryModel();
    model.setId(r.getString(MapperFields.RESOURCE_ID));
    model.setResourceType(r.getString(MapperFields.RESOURCE_TYPE));
    model.setTimespent(r.getLong(MapperFields.RESOURCE_TIMESPENT));
    model.setScore(r.getDouble(MapperFields.RESOURCE_SCORE));
    model.setReaction(r.getInt(MapperFields.RESOURCE_REACTION));
    model.setQuestionType(r.getString(MapperFields.QUESTION_TYPE));
    JsonArray answerObject = r.getObject(MapperFields.ANSWER_OBJECT) != null
        ? new JsonArray(r.getObject(MapperFields.ANSWER_OBJECT).toString()) : null;
    model.setAnswerObject(answerObject != null ? answerObject.getList() : null);
    model.setEventTime(r.getTimestamp(MapperFields.UPDATED_AT));
    model.setMaxScore(r.getDouble(MapperFields.MAX_SCORE));
    model.setAnswerStatus(r.getString(MapperFields.ATTEMPT_STATUS));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String RESOURCE_ID = "resource_id";
    private static final String RESOURCE_TYPE = "resource_type";
    private static final String RESOURCE_TIMESPENT = "time_spent";
    private static final String RESOURCE_SCORE = "score";
    private static final String RESOURCE_REACTION = "reaction";
    private static final String QUESTION_TYPE = "question_type";
    private static final String ANSWER_OBJECT = "answer_object";
    private static final String UPDATED_AT = "updated_at";
    private static final String MAX_SCORE = "max_score";
    private static final String ATTEMPT_STATUS = "attempt_status";

  }

}
