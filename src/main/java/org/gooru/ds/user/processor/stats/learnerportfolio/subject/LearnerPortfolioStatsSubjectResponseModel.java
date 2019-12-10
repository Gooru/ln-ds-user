package org.gooru.ds.user.processor.stats.learnerportfolio.subject;


class LearnerPortfolioStatsSubjectResponseModel {

  private String domainCode;
  private String domainName;
  private Integer domainSeq;
  private Integer collectionCount = 0;
  private Integer assessmentCount = 0;
  private Integer assessmentExternalCount = 0;
  private Integer collectionExternalCount = 0;
  private Integer oaCount = 0;



  public Integer getDomainSeq() {
    return domainSeq;
  }


  public void setDomainSeq(Integer domainSeq) {
    this.domainSeq = domainSeq;
  }


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

  public String getDomainCode() {
    return domainCode;
  }


  public void setDomainCode(String domainCode) {
    this.domainCode = domainCode;
  }


  public String getDomainName() {
    return domainName;
  }


  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

}
