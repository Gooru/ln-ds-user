
package org.gooru.ds.user.processor.struggling.competencies;

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
 * @author szgooru Created On 30-Sep-2019
 */
public class StrugglingCompetenciesProcessor implements MessageProcessor {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(StrugglingCompetenciesProcessor.class);
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;
  private EventBusMessage eventBusMessage;
  
  private final static StrugglingCompetenciesService SERVICE = new StrugglingCompetenciesService(DBICreator.getDbiForDefaultDS());

  public StrugglingCompetenciesProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    
    try {
      LOGGER.debug("request to fetch the struggling competencies");
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      StrugglingCompetenciesCommand command =
          StrugglingCompetenciesCommand.build(this.eventBusMessage.getRequestBody());
      LOGGER.debug("Request Data: {}", command.toString());
      fetchStrugglingCompetencyReport(command);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }
  
  private void fetchStrugglingCompetencyReport(StrugglingCompetenciesCommand command) {
    try {
      JsonObject response = SERVICE.fetchStrugglingCompetencies(command.asBean());
      result.complete(MessageResponseFactory.createOkayResponse(response));
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
  }

}
