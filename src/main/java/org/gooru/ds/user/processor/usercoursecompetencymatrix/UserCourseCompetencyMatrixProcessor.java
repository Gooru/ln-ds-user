package org.gooru.ds.user.processor.usercoursecompetencymatrix;

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
 * @author ashish on 13/2/18.
 */
public class UserCourseCompetencyMatrixProcessor implements MessageProcessor {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserCourseCompetencyMatrixProcessor.class);
  private final Vertx vertx;
  private final Message<JsonObject> message;
  private final Future<MessageResponse> result;
  private EventBusMessage eventBusMessage;
  private UserCourseCompetencyMatrixService userCourseCompetencyMatrixService =
      new UserCourseCompetencyMatrixService(DBICreator.getDbiForDefaultDS());

  public UserCourseCompetencyMatrixProcessor(Vertx vertx, Message<JsonObject> message) {
    this.vertx = vertx;
    this.message = message;
    this.result = Future.future();
  }

  @Override
  public Future<MessageResponse> process() {
    try {
      this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
      UserCourseCompetencyMatrixCommand command =
          UserCourseCompetencyMatrixCommand.builder(eventBusMessage.getRequestBody());
      fetchUserCompetencyMatrix(command);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }
    return result;
  }

  private void fetchUserCompetencyMatrix(UserCourseCompetencyMatrixCommand command) {
    try {
      UserCourseCompetencyMatrixModelResponse outcome =
          userCourseCompetencyMatrixService.fetchUserCourseCompetencyMatrix(command);
      String resultString = new ObjectMapper().writeValueAsString(outcome);
      result.complete(MessageResponseFactory.createOkayResponse(new JsonObject(resultString)));
    } catch (JsonProcessingException e) {
      LOGGER.error("Not able to convert data to JSON", e);
      result.fail(e);
    } catch (DecodeException e) {
      LOGGER.warn("Not able to convert data to JSON", e);
      result.fail(e);
    } catch (Throwable throwable) {
      LOGGER.warn("Encountered exception", throwable);
      result.fail(throwable);
    }

  }
}
