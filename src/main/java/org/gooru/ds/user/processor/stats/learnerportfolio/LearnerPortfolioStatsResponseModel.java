package org.gooru.ds.user.processor.stats.learnerportfolio;


class LearnerPortfolioStatsResponseModel {

  private String subjectCode;
  private String subjectName;
  private String classificationCode;
  private String classificationName;
  private Integer subjectSeq;
  private Integer classificationSeq;
  private Integer collectionCount = 0;
  private Integer assessmentCount = 0;
  private Integer assessmentExternalCount = 0;
  private Integer collectionExternalCount = 0;
  private Integer oaCount = 0;


  public Integer getAssessmentCount() {
    return assessmentCount;
  }

  public void setAssessmentCount(Integer assessmentCount) {
    this.assessmentCount = assessmentCount;
  }

  public Integer getAssessmentExternalCount() {
    return assessmentExternalCount;
  }

  public void setAssessmentExternalCount(Integer assessmentExternalCount) {
    this.assessmentExternalCount = assessmentExternalCount;
  }

  public Integer getCollectionExternalCount() {
    return collectionExternalCount;
  }

  public void setCollectionExternalCount(Integer collectionExternalCount) {
    this.collectionExternalCount = collectionExternalCount;
  }

  public Integer getOaCount() {
    return oaCount;
  }

  public void setOaCount(Integer oaCount) {
    this.oaCount = oaCount;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public Integer getCollectionCount() {
    return collectionCount;
  }


  public void setCollectionCount(Integer collectionCount) {
    this.collectionCount = collectionCount;
  }

  public String getClassificationCode() {
    return classificationCode;
  }

  public void setClassificationCode(String classificationCode) {
    this.classificationCode = classificationCode;
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

  public String getClassificationName() {
    return classificationName;
  }

  public void setClassificationName(String classificationName) {
    this.classificationName = classificationName;
  }

}
