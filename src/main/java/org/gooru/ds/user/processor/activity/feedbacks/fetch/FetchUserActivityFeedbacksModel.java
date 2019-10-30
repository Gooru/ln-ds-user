package org.gooru.ds.user.processor.activity.feedbacks.fetch;


public class FetchUserActivityFeedbacksModel {
  
  private int feedbackCategoryId;
  private Integer userFeedbackQuantitative;
  private String userFeedbackQualitative;

  public int getFeedbackCategoryId() {
    return feedbackCategoryId;
  }

  public void setFeedbackCategoryId(int feedbackCategoryId) {
    this.feedbackCategoryId = feedbackCategoryId;
  }

  public String getUserFeedbackQualitative() {
    return userFeedbackQualitative;
  }

  public void setUserFeedbackQualitative(String userFeedbackQualitative) {
    this.userFeedbackQualitative = userFeedbackQualitative;
  }

  public Integer getUserFeedbackQuantitative() {
    return userFeedbackQuantitative;
  }

  public void setUserFeedbackQuantitative(Integer userFeedbackQuantitative) {
    this.userFeedbackQuantitative = userFeedbackQuantitative;
  }
}
