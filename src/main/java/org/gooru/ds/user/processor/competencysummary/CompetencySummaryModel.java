package org.gooru.ds.user.processor.competencysummary;


/**
 * @author mukul@gooru
 */
public class CompetencySummaryModel {

  private Integer completedCompetencies;
  private Integer inprogressCompetencies;
  private Integer totalCompetencies;
  private Double percentScore;
  private Double percentCompletion;
  private Integer gradeId;
  private String grade;

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

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }
  
  public Integer getGradeId() {
    return gradeId;
  }
}
