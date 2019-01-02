package org.gooru.ds.user.processor.atc.recompute;


/**
 * @author mukul@gooru
 */
public class CompetencyStatsModel {

  private Integer completedCompetencies;
  private Integer inprogressCompetencies;
  private Integer totalCompetencies;
  private Double percentScore;
  private Double percentCompletion;
  private String userId;
  private String gradeId;

  public Integer getCompletedCompetencies() {
    return completedCompetencies;
  }

  public void setCompletedCompetencies(Integer completedCompetencies) {
    this.completedCompetencies = completedCompetencies;
  }

  public Integer getInprogressCompetencies() {
    return inprogressCompetencies;
  }

  public void setInprogressCompetencies(Integer inprogressCompetencies) {
    this.inprogressCompetencies = inprogressCompetencies;
  }

  public Integer getTotalCompetencies() {
    return totalCompetencies;
  }

  public void setTotalCompetencies(Integer totalCompetencies) {
    this.totalCompetencies = totalCompetencies;
  }

  public Double getPercentScore() {
    return percentScore;
  }

  public void setPercentScore(Double percentScore) {
    this.percentScore = percentScore;
  }

  public Double getPercentCompletion() {
    return percentCompletion;
  }

  public void setPercentCompletion(Double percentCompletion) {
    this.percentCompletion = percentCompletion;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getGradeId() {
    return gradeId;
  }

  public void setGradeId(String gradeId) {
    this.gradeId = gradeId;
  }

}
