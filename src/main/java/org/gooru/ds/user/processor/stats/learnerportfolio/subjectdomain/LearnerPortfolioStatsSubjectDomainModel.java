package org.gooru.ds.user.processor.stats.learnerportfolio.subjectdomain;


class LearnerPortfolioStatsSubjectDomainModel {

  private String competencyCode;
  private String competencyName;
  private Integer competencySeq;
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
