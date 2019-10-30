package org.gooru.ds.user.processor.activity.feedbacks.fetch;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class FetchUserActivityFeedbacksModelMapper
    implements ResultSetMapper<FetchUserActivityFeedbacksModel> {

  @Override
  public FetchUserActivityFeedbacksModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    FetchUserActivityFeedbacksModel model = new FetchUserActivityFeedbacksModel();
    model.setFeedbackCategoryId(r.getInt(MapperFields.FEEDBACK_CATEGORY_ID));
    model.setUserFeedbackQualitative(r.getString(MapperFields.USER_FEEDBACK_QUALITATIVE));
    String userFeedbackQuantitative = r.getString(MapperFields.USER_FEEDBACK_QUANTITATIVE);
    model.setUserFeedbackQuantitative(
        userFeedbackQuantitative != null ? Integer.parseInt(userFeedbackQuantitative) : null);
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String FEEDBACK_CATEGORY_ID = "feedback_category_id";
    private static final String USER_FEEDBACK_QUANTITATIVE = "user_feedback_quantitative";
    private static final String USER_FEEDBACK_QUALITATIVE = "user_feedback_qualitative";

  }

}
