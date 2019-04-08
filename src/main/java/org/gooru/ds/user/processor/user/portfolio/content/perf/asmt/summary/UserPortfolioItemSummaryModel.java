package org.gooru.ds.user.processor.user.portfolio.content.perf.asmt.summary;

/**
 * @author renuka
 */
public class UserPortfolioItemSummaryModel {

  private String id;
  private String type;
  private Long timeSpent;
  private Integer reaction;
  private Double score;

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

  public Long getTimeSpent() {
    return timeSpent;
  }

  public void setTimeSpent(Long timeSpent) {
    this.timeSpent = timeSpent;
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

}
