
package org.gooru.ds.user.processor.domain.report.dbhelpers;

/**
 * @author szgooru Created On 18-Jan-2019
 */
public class GradeCompetencyBound {

  private String domainCode;
  private String lowlineCode;
  private String highlineCode;

  public String getDomainCode() {
    return domainCode;
  }

  public void setDomainCode(String domainCode) {
    this.domainCode = domainCode;
  }

  public String getLowlineCode() {
    return lowlineCode;
  }

  public void setLowlineCode(String lowlineCode) {
    this.lowlineCode = lowlineCode;
  }

  public String getHighlineCode() {
    return highlineCode;
  }

  public void setHighlineCode(String highlineCode) {
    this.highlineCode = highlineCode;
  }

  @Override
  public String toString() {
    return "GradeCompetencyBound [domainCode=" + domainCode + ", lowlineCode=" + lowlineCode
        + ", highlineCode=" + highlineCode + "]";
  }

}
