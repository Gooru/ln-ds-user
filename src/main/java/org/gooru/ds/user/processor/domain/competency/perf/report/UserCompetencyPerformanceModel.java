
package org.gooru.ds.user.processor.domain.competency.perf.report;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class UserCompetencyPerformanceModel {

  private String userId;
  private double score;
  private int status;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "UserCompetencyPerformanceModel [userId=" + userId + ", score=" + score + ", status="
        + status + "]";
  }
  
}
