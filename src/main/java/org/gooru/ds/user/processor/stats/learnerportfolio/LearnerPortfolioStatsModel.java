package org.gooru.ds.user.processor.stats.learnerportfolio;


class LearnerPortfolioStatsModel {

  private String subjectCode;
  private String subjectName;
  private String collectionType;
  private String classificationCode;
  private String classificationName;
  private Integer collectionCount = 0;
  private Integer subjectSeq;
  private Integer classificationSeq;


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


  public String getCollectionType() {
    return collectionType;
  }


  public void setCollectionType(String collectionType) {
    this.collectionType = collectionType;
  }


  public String getClassificationCode() {
    return classificationCode;
  }


  public void setClassificationCode(String classificationCode) {
    this.classificationCode = classificationCode;
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

  public String getClassificationName() {
    return classificationName;
  }

  public void setClassificationName(String classificationName) {
    this.classificationName = classificationName;
  }

}
