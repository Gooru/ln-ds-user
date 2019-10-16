package org.gooru.ds.user.processor.activity.feebacks.create;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.Transaction;

interface CreateUserActivityFeedbackDao {

  @Transaction
  @SqlBatch("insert into user_activity_feedback (content_id, content_type, user_id, user_category_id, feedback_category_id, "
      + "user_feedback_quantitative, user_feedback_qualitative) values (:contentId, :contentType, :userId, :userCategoryId, "
      + ":feedbackCategoryId, :userFeedbackQuantitative, :userFeedbackQualitative)")
  void createUserActivityFeedback(@BindBean List<CreateUserActivityFeedbackModel> model);

}
