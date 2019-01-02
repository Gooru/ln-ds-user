package org.gooru.ds.user.processor.usercompetencymatrix;

/**
 * @author ashish on 17/1/18.
 */
class UserCompetencyMatrixModel {
  private String courseCode;
  private String domainCode;
  private String competencyCode;
  private String competencyName;
  private String competencyDesc;
  private String competencyStudentDesc;
  private Integer competencySeq;
  private Integer status;

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public String getDomainCode() {
    return domainCode;
  }

  public void setDomainCode(String domainCode) {
    this.domainCode = domainCode;
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

  public String getCompetencyDesc() {
    return competencyDesc;
  }

  public void setCompetencyDesc(String competencyDesc) {
    this.competencyDesc = competencyDesc;
  }

  public String getCompetencyStudentDesc() {
    return competencyStudentDesc;
  }

  public void setCompetencyStudentDesc(String competencyStudentDesc) {
    this.competencyStudentDesc = competencyStudentDesc;
  }

  public Integer getCompetencySeq() {
    return competencySeq;
  }

  public void setCompetencySeq(Integer competencySeq) {
    this.competencySeq = competencySeq;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
