package org.gooru.ds.user.processor.user.distribution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.gooru.ds.user.app.components.AppConfiguration;
import org.gooru.ds.user.app.data.EventBusMessage;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.responses.MessageResponse;
import org.gooru.ds.user.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public class UserDistributionProcessor implements MessageProcessor {
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserDistributionProcessor.class);
  private EventBusMessage eventBusMessage;
  private final UserDistributionService userDistributionService =
      new UserDistributionService(DBICreator.getDbiForDefaultDS());

  public UserDistributionProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    try {
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      UserDistributionCommand command =
          UserDistributionCommand.builder(eventBusMessage.getRequestBody());
      fetchUserDistribution(command);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }

  private void fetchUserDistribution(UserDistributionCommand command) {
    try {
      // UserDistributionModelResponse outcome =
      // userDistributionService.fetchUserDistribution(command);
      // String resultString = new ObjectMapper().writeValueAsString(outcome);
      String stubPath = AppConfiguration.getInstance().getConfigAsString("user.distribution.json");

      JsonObject response = null;
      Scanner scan = null;
      try {
        scan = new Scanner(new File(stubPath));
        String content = scan.useDelimiter("\\Z").next();
        response = new JsonObject(content);
      } catch (FileNotFoundException e) {
        LOGGER.error("error while reading file");
        result.fail(e);
      } finally {
        if (scan != null) {
          scan.close();
        }
      }

      result.complete(MessageResponseFactory.createOkayResponse(response));
    } /*
       * catch (JsonProcessingException e) { LOGGER.error("Not able to convert data to JSON", e);
       * result.fail(e); }
       */catch (DecodeException e) {
      LOGGER.warn("Not able to convert data to JSON", e);
      result.fail(e);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }

  }
}
