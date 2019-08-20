package org.gooru.ds.user.processor.content.portfolio.coll.summary;

import io.vertx.core.json.JsonArray;

/**
 * @author renuka
 */
public class UserPortfolioItemCollResScoreModel {

  private Double score;
  private Double maxScore;
  private String attemptStatus;
  private JsonArray answerObject;
  
  public Double getScore() {
    return score;
  }
  public void setScore(Double score) {
    this.score = score;
  }
  public Double getMaxScore() {
    return maxScore;
  }
  public void setMaxScore(Double maxScore) {
    this.maxScore = maxScore;
  }
  public String getAttemptStatus() {
    return attemptStatus;
  }
  public void setAttemptStatus(String attemptStatus) {
    this.attemptStatus = attemptStatus;
  }
  public JsonArray getAnswerObject() {
    return answerObject;
  }
  public void setAnswerObject(JsonArray answerObject) {
    this.answerObject = answerObject;
  }


}
