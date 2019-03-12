
package org.gooru.ds.user.processor.domain.competency.perf.report;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class UserCompetencyCompletionModel {

  private String userId;
  private int status;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "UserCompetencyPerformanceModel [userId=" + userId + ", status=" + status + "]";
  }

}
