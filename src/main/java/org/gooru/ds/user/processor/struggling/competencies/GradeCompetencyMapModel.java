
package org.gooru.ds.user.processor.struggling.competencies;

/**
 * @author szgooru Created On 17-Oct-2019
 */
public class GradeCompetencyMapModel {

  private Long gradeId;
  private Long domainId;
  private String domainCode;
  private String domainName;
  private Integer domainSeq;
  private String compCode;
  private String compName;
  private String compStudentDesc;
  private Integer compSeq;

  public Long getGradeId() {
    return gradeId;
  }

  public void setGradeId(Long gradeId) {
    this.gradeId = gradeId;
  }

  public Long getDomainId() {
    return domainId;
  }

  public void setDomainId(Long domainId) {
    this.domainId = domainId;
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

  public String getCompCode() {
    return compCode;
  }

  public void setCompCode(String compCode) {
    this.compCode = compCode;
  }

  public String getCompName() {
    return compName;
  }

  public void setCompName(String compName) {
    this.compName = compName;
  }

  public String getCompStudentDesc() {
    return compStudentDesc;
  }

  public void setCompStudentDesc(String compStudentDesc) {
    this.compStudentDesc = compStudentDesc;
  }

  public Integer getCompSeq() {
    return compSeq;
  }

  public void setCompSeq(Integer compSeq) {
    this.compSeq = compSeq;
  }

}
