package org.gooru.ds.user.processor.user.portfolio.content.summary.collection;

import java.sql.Timestamp;

/**
 * @author renuka
 */
public class UserPortfolioItemSummaryModel {

  private String id;
  private String type;
  private Long timespent = 0l;
  private Integer reaction;
  private Double score;
  private Double maxScore;
  private Timestamp eventTime;
  private Long views = 0l;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public Timestamp getEventTime() {
    return eventTime;
  }

  public void setEventTime(Timestamp eventTime) {
    this.eventTime = eventTime;
  }

  public Long getViews() {
    return views;
  }

  public void setViews(Long views) {
    this.views = views;
  }

  public Double getMaxScore() {
    return maxScore;
  }

  public void setMaxScore(Double maxScore) {
    this.maxScore = maxScore;
  }

}
