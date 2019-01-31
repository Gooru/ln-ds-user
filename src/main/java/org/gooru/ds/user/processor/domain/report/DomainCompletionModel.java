
package org.gooru.ds.user.processor.domain.report;

public class DomainCompletionModel {
  private DomainModel domain;
  private Long average_completions;

  public DomainModel getDomain() {
    return domain;
  }

  public void setDomain(DomainModel domain) {
    this.domain = domain;
  }

  public Long getAverage_completions() {
    return average_completions;
  }

  public void setAverage_completions(Long average_completions) {
    this.average_completions = average_completions;
  }

}
