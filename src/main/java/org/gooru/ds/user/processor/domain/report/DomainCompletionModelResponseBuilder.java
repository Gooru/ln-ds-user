
package org.gooru.ds.user.processor.domain.report;

import java.util.Map;
import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.processor.domain.report.utils.DomainReportResponseConstants;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 30-Jan-2019
 */
public class DomainCompletionModelResponseBuilder {

  public static JsonObject buildReponse(String agent,
      Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionMap, int memberCount) {
    if (agent.equalsIgnoreCase(Constants.Params.AGENT_MOBILE)) {
      return buildResponseForMobile(domainCompetencyCompletionMap, memberCount);
    } else {
      return buildResponseForDesktop(domainCompetencyCompletionMap, memberCount);
    }
  }

  /*
   * private static DomainCompletionModelResponse buildResponseForDesktop( Map<String,
   * DomainCompetencyCompletionModel> domainCompetencyCompletionMap, int memberCount) {
   * DomainCompletionModelResponse response = new DomainCompletionModelResponse();
   * List<DomainCompletionModel> domains = new ArrayList<>(); for (Map.Entry<String,
   * DomainCompetencyCompletionModel> entry : domainCompetencyCompletionMap .entrySet()) {
   * DomainCompetencyCompletionModel dccModel = entry.getValue();
   * 
   * DomainCompletionModel domainCompletionModel = new DomainCompletionModel();
   * domainCompletionModel.setAverage_completions(dccModel.getAverage_completions());
   * domainCompletionModel.setDomain(dccModel.getDomain()); domains.add(domainCompletionModel); }
   * 
   * response.setMember_count(memberCount); response.setDomains(domains); return response; }
   */

  private static JsonObject buildResponseForDesktop(
      Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionMap, int memberCount) {
    JsonObject response = new JsonObject();
    JsonArray domains = new JsonArray();
    for (Map.Entry<String, DomainCompetencyCompletionModel> entry : domainCompetencyCompletionMap
        .entrySet()) {
      DomainCompetencyCompletionModel dccModel = entry.getValue();
      DomainModel domainModel = dccModel.getDomain();

      JsonObject domainArrayObj = new JsonObject();

      JsonObject domain = new JsonObject();
      domain.put(DomainReportResponseConstants.DesktopResponseConstants.TX_DOMAIN_CODE,
          domainModel.getTx_domain_code());
      domain.put(DomainReportResponseConstants.DesktopResponseConstants.TX_DOMAIN_NAME,
          domainModel.getTx_domain_name());
      domain.put(DomainReportResponseConstants.DesktopResponseConstants.TX_DOMAIN_SEQ,
          domainModel.getTx_domain_seq());
      domainArrayObj.put(DomainReportResponseConstants.DesktopResponseConstants.DOMAIN, domain);

      JsonArray competenciesArray = new JsonArray();
      for (Map.Entry<String, CompetencyCompletionModel> models : dccModel.getCompetencies()
          .entrySet()) {
        CompetencyCompletionModel ccm = models.getValue();
        JsonObject competency = new JsonObject();
        competency.put(DomainReportResponseConstants.DesktopResponseConstants.TX_COMP_CODE,
            ccm.getCompetencyCode());
        competency.put(DomainReportResponseConstants.DesktopResponseConstants.TX_COMP_NAME,
            ccm.getCompetencyName());
        competency.put(DomainReportResponseConstants.DesktopResponseConstants.TX_COMP_DESC,
            ccm.getCompetencyDesc());
        competency.put(DomainReportResponseConstants.DesktopResponseConstants.COMPLETIONS,
            ccm.getPercentageCompletion());

        competenciesArray.add(competency);
      }
      domainArrayObj.put(DomainReportResponseConstants.DesktopResponseConstants.COMPETENCIES,
          competenciesArray);

      domainArrayObj.put(
          DomainReportResponseConstants.DesktopResponseConstants.DOMAIN_AVERAGE_COMPLETIONS,
          dccModel.getAverage_completions());

      domains.add(domainArrayObj);
    }

    response.put(DomainReportResponseConstants.DesktopResponseConstants.MEMBER_COUNT, memberCount);
    response.put(DomainReportResponseConstants.DesktopResponseConstants.DOMAINS, domains);
    return response;
  }

  private static JsonObject buildResponseForMobile(
      Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionMap, int memberCount) {
    JsonObject response = new JsonObject();
    JsonArray domains = new JsonArray();
    for (Map.Entry<String, DomainCompetencyCompletionModel> entry : domainCompetencyCompletionMap
        .entrySet()) {
      DomainCompetencyCompletionModel dccModel = entry.getValue();
      DomainModel domainModel = dccModel.getDomain();

      JsonObject domainArrayObj = new JsonObject();

      JsonObject domain = new JsonObject();
      domain.put(DomainReportResponseConstants.MobileResponseConstants.TX_DOMAIN_CODE,
          domainModel.getTx_domain_code());
      domain.put(DomainReportResponseConstants.MobileResponseConstants.TX_DOMAIN_NAME,
          domainModel.getTx_domain_name());
      domain.put(DomainReportResponseConstants.MobileResponseConstants.TX_DOMAIN_SEQ,
          domainModel.getTx_domain_seq());
      domainArrayObj.put(DomainReportResponseConstants.MobileResponseConstants.DOMAIN, domain);

      JsonArray competenciesArray = new JsonArray();
      for (Map.Entry<String, CompetencyCompletionModel> models : dccModel.getCompetencies()
          .entrySet()) {
        CompetencyCompletionModel ccm = models.getValue();
        JsonObject competency = new JsonObject();
        competency.put(DomainReportResponseConstants.MobileResponseConstants.TX_COMP_CODE,
            ccm.getCompetencyCode());
        competency.put(DomainReportResponseConstants.MobileResponseConstants.TX_COMP_NAME,
            ccm.getCompetencyName());
        competency.put(DomainReportResponseConstants.MobileResponseConstants.TX_COMP_DESC,
            ccm.getCompetencyDesc());
        competency.put(DomainReportResponseConstants.MobileResponseConstants.COMPLETIONS,
            ccm.getPercentageCompletion());

        competenciesArray.add(competency);
      }
      domainArrayObj.put(DomainReportResponseConstants.MobileResponseConstants.COMPETENCIES,
          competenciesArray);

      domainArrayObj.put(
          DomainReportResponseConstants.MobileResponseConstants.DOMAIN_AVERAGE_COMPLETIONS,
          dccModel.getAverage_completions());

      domains.add(domainArrayObj);
    }
    
    response.put(DomainReportResponseConstants.MobileResponseConstants.MEMBER_COUNT, memberCount);
    response.put(DomainReportResponseConstants.MobileResponseConstants.DOMAINS, domains);
    return response;
  }
}
