package org.gooru.ds.user.processor.userperf.collections;

/**
 * @author mukul@gooru
 */
public class UserPerfCollectionsModel {

  private String id;
  private String title;
  private String sessionId;
  private String collectionType;
  private Long timeSpent;
  private Integer reaction;
  private Double score;
  private Integer sequenceId;

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

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
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

  public String getCollectionType() {
    return collectionType;
  }

  public void setCollectionType(String collectionType) {
    this.collectionType = collectionType;
  }

  public Integer getSequenceId() {
    return sequenceId;
  }

  public void setSequenceId(Integer sequenceId) {
    this.sequenceId = sequenceId;
  }


}
