package org.gooru.ds.user.processor.activity.feedbacks.fetch;

import java.util.List;


class FetchUserActivityFeedbacksModelResponse {

  private List<FetchUserActivityFeedbacksModel> userActivityFeedbacks;

  public List<FetchUserActivityFeedbacksModel> getUserActivityFeedbacks() {
    return userActivityFeedbacks;
  }

  public void setUserActivityFeedbacks(List<FetchUserActivityFeedbacksModel> userActivityFeedbacks) {
    this.userActivityFeedbacks = userActivityFeedbacks;
  }
}
