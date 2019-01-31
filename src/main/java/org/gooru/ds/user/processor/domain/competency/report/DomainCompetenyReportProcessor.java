
package org.gooru.ds.user.processor.domain.competency.report;

import org.gooru.ds.user.app.data.EventBusMessage;
import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 14-Jan-2019
 */
public class DomainCompetenyReportProcessor implements MessageProcessor {

  private final static Logger LOGGER =
      LoggerFactory.getLogger(DomainCompetenyReportProcessor.class);
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;

  private EventBusMessage eventBusMessage;

  public DomainCompetenyReportProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    try {
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      DomainCompetencyReportCommand command =
          DomainCompetencyReportCommand.build(this.eventBusMessage.getRequestBody());

    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }

  private void fetchDomainCompetencyCompletionReport(DomainCompetencyReportCommand command) {

  }

}
