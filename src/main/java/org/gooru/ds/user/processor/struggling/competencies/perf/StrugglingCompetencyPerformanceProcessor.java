
package org.gooru.ds.user.processor.struggling.competencies.perf;

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
 * @author szgooru Created On 11-Nov-2019
 */
public class StrugglingCompetencyPerformanceProcessor implements MessageProcessor {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(StrugglingCompetencyPerformanceProcessor.class);
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;
  private EventBusMessage eventBusMessage;
  
  private final static StrugglingCompetencyPerformanceService SERVICE = new StrugglingCompetencyPerformanceService(DBICreator.getDbiForDefaultDS());
  
  public StrugglingCompetencyPerformanceProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    try {
      LOGGER.debug("request to fetch the struggling competency performance");
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      StrugglingCompetencyPerformanceCommand command =
          StrugglingCompetencyPerformanceCommand.build(this.eventBusMessage.getRequestBody());
      LOGGER.debug("Request Data: {}", command.toString());
      fetchStrugglingCompetencyPerformance(command);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }

  private void fetchStrugglingCompetencyPerformance(
      StrugglingCompetencyPerformanceCommand command) {
    try {
      JsonObject response = SERVICE.fetchCompetencyPerformance(command.asBean());
      result.complete(MessageResponseFactory.createOkayResponse(response));
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
  }

}
