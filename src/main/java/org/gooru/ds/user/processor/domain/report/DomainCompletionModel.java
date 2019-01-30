
package org.gooru.ds.user.processor.domain.report;

public class DomainCompletionModel {
  private DomainModel domain;
  private Integer average_completions;

  public DomainModel getDomain() {
    return domain;
  }

  public void setDomain(DomainModel domain) {
    this.domain = domain;
  }

  public Integer getAverage_completions() {
    return average_completions;
  }

  public void setAverage_completions(Integer average_completions) {
    this.average_completions = average_completions;
  }

}
