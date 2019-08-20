package org.gooru.ds.user.processor.content.portfolio.coll.summary;

import io.vertx.core.json.JsonArray;

/**
 * @author renuka
 */
public class UserPortfolioItemCollResAggModel {

  private Long timespent;
  private Long attempts;
  private String id;
  private String resourceType;
  private String questionType;
  private Integer reaction = 0;
  private Double score;
  private JsonArray answerObject;
  public Long getTimespent() {
    return timespent;
  }
  public void setTimespent(Long timespent) {
    this.timespent = timespent;
  }
  public Long getAttempts() {
    return attempts;
  }
  public void setAttempts(Long attempts) {
    this.attempts = attempts;
  }
  public String getId() {
    return id;
  }
  public void setId(String resourceId) {
    this.id = resourceId;
  }
  public String getResourceType() {
    return resourceType;
  }
  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }
  public String getQuestionType() {
    return questionType;
  }
  public void setQuestionType(String questionType) {
    this.questionType = questionType;
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
  public JsonArray getAnswerObject() {
    return answerObject;
  }
  public void setAnswerObject(JsonArray answerObject) {
    this.answerObject = answerObject;
  }


}
