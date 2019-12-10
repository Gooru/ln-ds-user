package org.gooru.ds.user.processor.stats.learnerportfolio.subject;


class LearnerPortfolioStatsSubjectModel {

  private String domainCode;
  private String domainName;
  private Integer domainSeq;
  private String collectionType;
  private Integer collectionCount = 0;


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

  public Integer getDomainSeq() {
    return domainSeq;
  }

  public void setDomainSeq(Integer domainSeq) {
    this.domainSeq = domainSeq;
  }



}
