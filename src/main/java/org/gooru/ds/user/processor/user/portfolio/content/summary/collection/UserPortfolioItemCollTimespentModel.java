package org.gooru.ds.user.processor.user.portfolio.content.summary.collection;

/**
 * @author renuka
 */
public class UserPortfolioItemCollTimespentModel {

  private Long timespent;
  private Long attempts;
  private String collectionType;
  
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
  public String getCollectionType() {
    return collectionType;
  }
  public void setCollectionType(String collectionType) {
    this.collectionType = collectionType;
  }

}
