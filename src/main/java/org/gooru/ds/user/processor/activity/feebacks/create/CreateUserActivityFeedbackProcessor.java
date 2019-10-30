package org.gooru.ds.user.processor.activity.feebacks.create;

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
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;

public class CreateUserActivityFeedbackProcessor implements MessageProcessor {
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;
  private static final Logger LOGGER =
      LoggerFactory.getLogger(CreateUserActivityFeedbackProcessor.class);
  private EventBusMessage eventBusMessage;
  private final CreateUserActivityFeedbackService createUserActivityFeedbackService =
      new CreateUserActivityFeedbackService(DBICreator.getDbiForDefaultDS());

  public CreateUserActivityFeedbackProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    try {
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      CreateUserActivityFeedbackCommand command =
          CreateUserActivityFeedbackCommand.builder(eventBusMessage.getRequestBody());
      createUserActivityFeedback(command);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }

  private void createUserActivityFeedback(CreateUserActivityFeedbackCommand command) {
    try {
      createUserActivityFeedbackService.createUserActivityFeedback(command);
      result.complete(MessageResponseFactory.createOkayResponse());
    } catch (DecodeException e) {
      LOGGER.warn("Not able to convert data to JSON", e);
      result.fail(e);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }

  }
}
