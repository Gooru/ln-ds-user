
package org.gooru.ds.user.processor.domain.report;

import java.util.Map;

/**
 * @author szgooru Created On 29-Jan-2019
 */
public class DomainCompetencyCompletionModel {

  private DomainModel domain;
  private Map<String, CompetencyCompletionModel> competencies;
  private Integer avgCompletion;

  public DomainModel getDomain() {
    return domain;
  }

  public void setDomain(DomainModel domain) {
    this.domain = domain;
  }

  public Map<String, CompetencyCompletionModel> getCompetencies() {
    return competencies;
  }

  public void setCompetencies(Map<String, CompetencyCompletionModel> competencies) {
    this.competencies = competencies;
  }

  public Integer getAvgCompletion() {
    return avgCompletion;
  }

  public void setAvgCompletion(Integer avgCompletion) {
    this.avgCompletion = avgCompletion;
  }


}
