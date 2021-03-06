package org.gooru.ds.user.processor.user.portfolio.content.summary.assessment;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author renuka
 */
public class UserPortfolioItemQuestionSummaryModel {

  private String id;
  private String title;
  private String resourceType;
  private Long timespent;
  private Integer reaction;
  private Double score;
  private String questionType;
  private List<?> answerObject;
  private Timestamp eventTime ;
  private Double maxScore;
  private Boolean isGraded;
  private String answerStatus;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public Integer getReaction() {
    return reaction;
  }

  public void setReaction(Integer reaction) {
    this.reaction = reaction;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getQuestionType() {
    return questionType;
  }

  public void setQuestionType(String questionType) {
    this.questionType = questionType;
  }

  public List<?> getAnswerObject() {
    return answerObject;
  }

  public void setAnswerObject(List<?> answerObject) {
    this.answerObject = answerObject;
  }

  public Timestamp getEventTime() {
    return eventTime;
  }

  public void setEventTime(Timestamp eventTime) {
    this.eventTime = eventTime;
  }

  public Double getMaxScore() {
    return maxScore;
  }

  public void setMaxScore(Double maxScore) {
    this.maxScore = maxScore;
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

  public String getAnswerStatus() {
    return answerStatus;
  }

  public void setAnswerStatus(String attemptStatus) {
    this.answerStatus = attemptStatus;
  }

}
