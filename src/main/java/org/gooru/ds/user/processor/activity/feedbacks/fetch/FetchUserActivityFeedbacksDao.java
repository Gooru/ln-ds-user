package org.gooru.ds.user.processor.activity.feedbacks.fetch;

import java.util.List;
import org.gooru.ds.user.processor.activity.feedbacks.fetch.FetchUserActivityFeedbacksCommand.FetchUserActivityFeedbacksCommandBean;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

interface FetchUserActivityFeedbacksDao {

  @Mapper(FetchUserActivityFeedbacksModelMapper.class)
  @SqlQuery("SELECT feedback_category_id, user_feedback_quantitative, user_feedback_qualitative FROM (SELECT feedback_category_id, "
      + "user_feedback_quantitative, user_feedback_qualitative, rank() OVER (PARTITION BY feedback_category_id ORDER BY updated_at DESC) as row_number  "
      + "FROM user_activity_feedback WHERE content_id = :contentId AND user_id = :userId) as user_feedbacks WHERE row_number = 1")
  List<FetchUserActivityFeedbacksModel> fetchUserActivityFeedbacks(@BindBean FetchUserActivityFeedbacksCommandBean model);

}
