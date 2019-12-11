package org.gooru.ds.user.processor.stats.learnerportfolio.subjectdomain;


class LearnerPortfolioStatsSubjectDomainResponseModel {

  private String competencyCode;
  private String competencyName;
  private Integer competencySeq;
  private Integer collectionCount = 0;
  private Integer assessmentCount = 0;
  private Integer assessmentExternalCount = 0;
  private Integer collectionExternalCount = 0;
  private Integer oaCount = 0;



  public Integer getCollectionCount() {
    return collectionCount;
  }


  public void setCollectionCount(Integer collectionCount) {
    this.collectionCount = collectionCount;
  }


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


  public String getCompetencyCode() {
    return competencyCode;
  }


  public void setCompetencyCode(String competencyCode) {
    this.competencyCode = competencyCode;
  }


  public String getCompetencyName() {
    return competencyName;
  }


  public void setCompetencyName(String competencyName) {
    this.competencyName = competencyName;
  }


  public Integer getCompetencySeq() {
    return competencySeq;
  }


  public void setCompetencySeq(Integer competencySeq) {
    this.competencySeq = competencySeq;
  }


}
