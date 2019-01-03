package org.gooru.ds.user.processor.userstats.competency;

/**
 * @author ashish on 12/1/18.
 */
class UserStatsCompetencyModel {
  private Integer inProgress;
  private Integer completed;

  public Integer getInProgress() {
    return inProgress;
  }

  public void setInProgress(Integer inProgress) {
    this.inProgress = inProgress;
  }

  public Integer getCompleted() {
    return completed;
  }

  public void setCompleted(Integer completed) {
    this.completed = completed;
  }
}
