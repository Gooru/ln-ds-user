package org.gooru.ds.user.processor.activity.feebacks.create;


public class CreateUserActivityFeedbackModel {
  private String contentId;
  private String contentType;
  private String userId;
  private int userCategoryId;
  private int feedbackCategoryId;
  private Integer userFeedbackQuantitative;
  private String userFeedbackQualitative;

  public String getContentId() {
    return contentId;
  }

  public void setContentId(String contentId) {
    this.contentId = contentId;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getUserCategoryId() {
    return userCategoryId;
  }

  public void setUserCategoryId(int userCategoryId) {
    this.userCategoryId = userCategoryId;
  }

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
