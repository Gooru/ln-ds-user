package org.gooru.ds.user.processor.competencysummary;

import org.gooru.ds.user.app.data.EventBusMessage;
import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.responses.MessageResponse;
import org.gooru.ds.user.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class UserCompetencySummaryProcessor implements MessageProcessor {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserCompetencySummaryProcessor.class);
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;
  private EventBusMessage eventBusMessage;
  private UserCompetencySummaryService competencyStatsService =
      new UserCompetencySummaryService();

  public UserCompetencySummaryProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    try {
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      UserCompetencySummaryCommand command =
          UserCompetencySummaryCommand.builder(eventBusMessage.getRequestBody());
      fetchUserCompetencyStats(command);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }

  private void fetchUserCompetencyStats(UserCompetencySummaryCommand command) {
    try {
      CompetencySummaryResponse response =
          competencyStatsService.fetchUserGradeCompetencyStats(command);
      String resultString = new ObjectMapper().writeValueAsString(response);
      result.complete(MessageResponseFactory.createOkayResponse(new JsonObject(resultString)));
    } catch (DecodeException e) {
      LOGGER.warn("Not able to convert data to JSON", e);
      result.fail(e);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }

  }

}
