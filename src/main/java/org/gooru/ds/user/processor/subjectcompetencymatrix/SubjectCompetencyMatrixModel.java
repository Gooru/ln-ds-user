package org.gooru.ds.user.processor.subjectcompetencymatrix;


class SubjectCompetencyMatrixModel {

  private String subjectCode;
  private String classificationCode;
  private String classificationName;
  private Integer competencyStatus;
  private Integer competencyCount;
  private String subjectName;
  private Integer classificationSeq;
  private Integer subjectSeq;

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

  public String getClassificationName() {
    return classificationName;
  }

  public void setClassificationName(String classificationName) {
    this.classificationName = classificationName;
  }



  public Integer getSubjectSeq() {
    return subjectSeq;
  }

  public void setSubjectSeq(Integer subjectSeq) {
    this.subjectSeq = subjectSeq;
  }

  public Integer getClassificationSeq() {
    return classificationSeq;
  }

  public void setClassificationSeq(Integer classificationSeq) {
    this.classificationSeq = classificationSeq;
  }

}
