
package org.gooru.ds.user.processor.domain.report;

import java.util.List;

/**
 * @author szgooru Created On 30-Jan-2019
 */
public class DomainCompletionModelResponse {

  private Integer member_count;
  private List<DomainCompletionModel> domains;

  public Integer getMember_count() {
    return member_count;
  }

  public void setMember_count(Integer member_count) {
    this.member_count = member_count;
  }

  public List<DomainCompletionModel> getDomains() {
    return domains;
  }

  public void setDomains(List<DomainCompletionModel> domains) {
    this.domains = domains;
  }

}
