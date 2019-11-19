
package org.gooru.ds.user.processor.struggling.competencies.perf;

import java.sql.Timestamp;

/**
 * @author szgooru Created On 12-Nov-2019
 */
public class StrugglingCompetencyPerformanceModel {

  private String userId;
  private String collectionId;
  private Double score;
  private Timestamp updatedAt;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(String collectionId) {
    this.collectionId = collectionId;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }


}
