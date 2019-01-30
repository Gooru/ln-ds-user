
package org.gooru.ds.user.processor.domain.report;

/**
 * @author szgooru Created On 29-Jan-2019
 */
public class CompetencyCompletionModel {

  private String competencyCode;
  private String competencyName;
  private String competencyDesc;
  private Integer competencySeq;
  private Integer avgCompletion;

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

  public Integer getCompetencySeq() {
    return competencySeq;
  }

  public void setCompetencySeq(Integer competencySeq) {
    this.competencySeq = competencySeq;
  }

  public Integer getAvgCompletion() {
    return avgCompletion;
  }

  public void setAvgCompletion(Integer avgCompletion) {
    this.avgCompletion = avgCompletion;
  }

  @Override
  public String toString() {
    return "CompetencyCompletionModel [competencyCode=" + competencyCode + ", competencySeq="
        + competencySeq + ", avgCompletion=" + avgCompletion + "]";
  }

}
