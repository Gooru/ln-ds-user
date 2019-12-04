package org.gooru.ds.user.processor.subjectcompetencymatrix;


class SubjectCompetencyMatrixModel {

  private String subjectCode;
  private String classificationCode;
  private Integer competencyStatus;
  private Integer competencyCount;
  private String subjectName;

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public Integer getCompetencyStatus() {
    return competencyStatus;
  }

  public void setCompetencyStatus(Integer competencyStatus) {
    this.competencyStatus = competencyStatus;
  }

  public Integer getCompetencyCount() {
    return competencyCount;
  }

  public void setCompetencyCount(Integer competencyCount) {
    this.competencyCount = competencyCount;
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

}
