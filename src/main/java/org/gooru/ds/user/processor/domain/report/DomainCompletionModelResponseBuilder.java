
package org.gooru.ds.user.processor.domain.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.constants.Constants;

/**
 * @author szgooru Created On 30-Jan-2019
 */
public class DomainCompletionModelResponseBuilder {

  public static DomainCompletionModelResponse buildReponse(String agent,
      Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionMap, int memberCount) {
    if (agent.equalsIgnoreCase(Constants.Params.AGENT_MOBILE)) {
      return buildResponseForMobile(domainCompetencyCompletionMap, memberCount);
    } else {
      return buildResponseForDesktop(domainCompetencyCompletionMap, memberCount);
    }
  }

  private static DomainCompletionModelResponse buildResponseForDesktop(
      Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionMap, int memberCount) {
    DomainCompletionModelResponse response =
        new DomainCompletionModelResponse();
    List<DomainCompletionModel> domains = new ArrayList<>();
    for (Map.Entry<String, DomainCompetencyCompletionModel> entry : domainCompetencyCompletionMap
        .entrySet()) {
      DomainCompetencyCompletionModel dccModel = entry.getValue();
      
      DomainCompletionModel domainCompletionModel = new DomainCompletionModel();
      domainCompletionModel.setAverage_completions(dccModel.getAverage_completions());
      domainCompletionModel.setDomain(dccModel.getDomain());
      domains.add(domainCompletionModel);
    }
    
    response.setMember_count(memberCount);
    response.setDomains(domains);
    return response;
  }

  private static DomainCompletionModelResponse buildResponseForMobile(
      Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionMap, int memberCount) {
    return new DomainCompletionModelResponse();
  }
}
