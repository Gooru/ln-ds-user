package org.gooru.ds.user.processor.subjectcompetencymatrix;

import java.util.List;

class SubjectCompetencyMatrixResponseModel {

  private String subjectCode;
  private String classificationCode;
  private String classificationName;
  private String subjectName;
  private Integer classificationSeq;
  private Integer subjectSeq;
  private List<CompetencyResponseModel> competencyStats;

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }


  public String getClassificationCode() {
    return classificationCode;
  }

  public void setClassificationCode(String classificationCode) {
    this.classificationCode = classificationCode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getClassificationName() {
    return classificationName;
  }

  public void setClassificationName(String classificationName) {
    this.classificationName = classificationName;
  }

  public List<CompetencyResponseModel> getCompetencyStats() {
    return competencyStats;
  }

  public void setCompetencyStats(List<CompetencyResponseModel> competencyStats) {
    this.competencyStats = competencyStats;
  }

  public Integer getClassificationSeq() {
    return classificationSeq;
  }

  public void setClassificationSeq(Integer classificationSeq) {
    this.classificationSeq = classificationSeq;
  }

  public Integer getSubjectSeq() {
    return subjectSeq;
  }

  public void setSubjectSeq(Integer subjectSeq) {
    this.subjectSeq = subjectSeq;
  }

}
