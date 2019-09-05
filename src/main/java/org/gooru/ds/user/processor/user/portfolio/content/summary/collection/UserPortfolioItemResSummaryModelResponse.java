package org.gooru.ds.user.processor.user.portfolio.content.summary.collection;

import java.util.List;

/**
 * @author renuka
 */
public class UserPortfolioItemResSummaryModelResponse {

  private String id;
  private String questionType;
  private String resourceType;
  private Long timespent;
  private Double score;
  private Integer reaction;
  private Long views;
  private List<?> answerObject;
  private String answerStatus;
  private String title;
  private Boolean isGraded;
  private Double maxScore;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getQuestionType() {
    return questionType;
  }
  public void setQuestionType(String questionType) {
    this.questionType = questionType;
  }
  public String getResourceType() {
    return resourceType;
  }
  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }
  public Long getTimespent() {
    return timespent;
  }
  public void setTimespent(Long timespent) {
    this.timespent = timespent;
  }
  public Double getScore() {
    return score;
  }
  public void setScore(Double score) {
    this.score = score;
  }
  public Integer getReaction() {
    return reaction;
  }
  public void setReaction(Integer reaction) {
    this.reaction = reaction;
  }
  public Long getViews() {
    return views;
  }
  public void setViews(Long views) {
    this.views = views;
  }
  public List<?> getAnswerObject() {
    return answerObject;
  }
  public void setAnswerObject(List<?> answerObject) {
    this.answerObject = answerObject;
  }
  public String getAnswerStatus() {
    return answerStatus;
  }
  public void setAnswerStatus(String answerStatus) {
    this.answerStatus = answerStatus;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Boolean getIsGraded() {
    if (isGraded == null) {
      isGraded = true;
    }
    return isGraded;
  }
  public void setIsGraded(Boolean isGraded) {
    this.isGraded = isGraded;
  }
  public Double getMaxScore() {
    return maxScore;
  }
  public void setMaxScore(Double maxScore) {
    this.maxScore = maxScore;
  }

}
