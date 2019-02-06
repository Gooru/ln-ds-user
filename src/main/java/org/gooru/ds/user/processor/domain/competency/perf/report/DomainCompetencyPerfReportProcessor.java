
package org.gooru.ds.user.processor.domain.competency.perf.report;

import org.gooru.ds.user.app.data.EventBusMessage;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.responses.MessageResponse;
import org.gooru.ds.user.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 14-Jan-2019
 */
public class DomainCompetencyPerfReportProcessor implements MessageProcessor {

  private final static Logger LOGGER =
      LoggerFactory.getLogger(DomainCompetencyPerfReportProcessor.class);
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;

  private final DomainCompetencyPerfReportService service = new DomainCompetencyPerfReportService(
      DBICreator.getDbiForDefaultDS(), DBICreator.getDbiForCoreDS());

  private EventBusMessage eventBusMessage;

  public DomainCompetencyPerfReportProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    try {
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      DomainCompetencyPerfReportCommand command =
          DomainCompetencyPerfReportCommand.build(this.eventBusMessage.getRequestBody());
      fetchDomainCompetencyPerfReport(command);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }

  private void fetchDomainCompetencyPerfReport(DomainCompetencyPerfReportCommand command) {
    try {
      JsonObject response = this.service.fetchDomainCompetencyPerfReport(command);
      result.complete(MessageResponseFactory.createOkayResponse(response));
    } catch (Throwable t) {
      LOGGER.warn("Encountered exception", t);
      result.fail(t);
    }
  }

}
