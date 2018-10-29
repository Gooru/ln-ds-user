package org.gooru.ds.user.processor.baselearnerprofile.read;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.gooru.ds.user.app.data.EventBusMessage;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.responses.MessageResponse;
import org.gooru.ds.user.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadBaselineLearnerProfileProcessor implements MessageProcessor {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReadBaselineLearnerProfileProcessor.class);
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;
  private EventBusMessage eventBusMessage;

  private final ReadBaselineLearnerProfileService service = new ReadBaselineLearnerProfileService(
      DBICreator.getDbiForDefaultDS());

  public ReadBaselineLearnerProfileProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    try {
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      ReadBaselineLearnerProfileCommand command = ReadBaselineLearnerProfileCommand
          .build(this.eventBusMessage.getRequestBody());
      readBaselineLearnerProfile(command);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }

  private void readBaselineLearnerProfile(ReadBaselineLearnerProfileCommand command) {
    String outcome = this.service.fetchBaselineLearnerProfile(command);
    result.complete(MessageResponseFactory.createOkayResponse(new JsonObject(outcome)));
  }
}
