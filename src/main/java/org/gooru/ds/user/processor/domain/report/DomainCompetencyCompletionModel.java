
package org.gooru.ds.user.processor.domain.report;

import java.util.Map;

/**
 * @author szgooru Created On 29-Jan-2019
 */
public class DomainCompetencyCompletionModel {

  private DomainModel domain;
  private Map<String, CompetencyCompletionModel> competencies;
  private Integer average_completions;

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

  public Integer getAverage_completions() {
    return average_completions;
  }

  public void setAverage_completions(Integer average_completions) {
    this.average_completions = average_completions;
  }


}
