
package org.gooru.ds.user.processor.domain.competency.perf.report;

import org.skife.jdbi.v2.DBI;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class DomainCompetencyPerfReportService {

  public DomainCompetencyPerfReportService(DBI defaultDbi, DBI coreDbi) {

  }

  public JsonObject fetchDomainCompetencyPerfReport(DomainCompetencyPerfReportCommand command) {
    JsonObject result = new JsonObject();
    return result;
  }
}
