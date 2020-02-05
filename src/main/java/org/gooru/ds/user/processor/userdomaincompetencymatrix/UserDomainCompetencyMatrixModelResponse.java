package org.gooru.ds.user.processor.userdomaincompetencymatrix;

import java.util.List;

/**
 * @author ashish on 13/2/18.
 */
class UserDomainCompetencyMatrixModelResponse {
  List<UserCompetencyMatrixDomainModelResponse> userCompetencyMatrix;
  long lastUpdated;

  public List<UserCompetencyMatrixDomainModelResponse> getUserCompetencyMatrix() {
    return userCompetencyMatrix;
  }

  public void setUserCompetencyMatrix(
      List<UserCompetencyMatrixDomainModelResponse> userCompetencyMatrix) {
    this.userCompetencyMatrix = userCompetencyMatrix;
  }

  public long getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(long lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  static class UserCompetencyMatrixDomainModelResponse {
    private String domainCode;
    private List<UserCompetencyMatrixCompetencyModelResponse> competencies;

    public String getDomainCode() {
      return domainCode;
    }

    public void setDomainCode(String domainCode) {
      this.domainCode = domainCode;
    }

    public List<UserCompetencyMatrixCompetencyModelResponse> getCompetencies() {
      return competencies;
    }

    public void setCompetencies(List<UserCompetencyMatrixCompetencyModelResponse> competencies) {
      this.competencies = competencies;
    }
  }

  static class UserCompetencyMatrixCompetencyModelResponse {
    private String competencyCode;
    private String competencyName;
    private String competencyDesc;
    private String competencyStudentDesc;
    private Integer competencySeq;
    private Integer status;
    private String source;

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
    
    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }
  }
}
