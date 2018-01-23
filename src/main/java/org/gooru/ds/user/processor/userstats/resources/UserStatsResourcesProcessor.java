package org.gooru.ds.user.processor.userstats.resources;

import org.gooru.ds.user.app.data.EventBusMessage;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesCommand;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesModelResponse;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourceService;
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
 * @author mukul@gooru
 */
public class UserStatsResourcesProcessor implements MessageProcessor {
	

    private final Vertx vertx;
    private final Message<JsonObject> message;
    private final Future<MessageResponse> result;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsResourcesProcessor.class);
    private EventBusMessage eventBusMessage;
    private final UserStatsResourceService UserStatsResourceService =
        new UserStatsResourceService(DBICreator.getDbiForDefaultDS());

    public UserStatsResourcesProcessor(Vertx vertx, Message<JsonObject> message) {
        this.vertx = vertx;
        this.message = message;
        this.result = Future.future();
    }

    @Override
    public Future<MessageResponse> process() {
        try {
            this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
            UserStatsResourcesCommand command = UserStatsResourcesCommand.builder(eventBusMessage.getRequestBody());
            fetchUserLessonPerf(command);
        } catch (Throwable throwable) {
            LOGGER.warn("Encountered exception", throwable);
            result.fail(throwable);
        }
        return result;
    }

    private void fetchUserLessonPerf(UserStatsResourcesCommand command) {
        try {
            UserStatsResourcesModelResponse outcome = UserStatsResourceService.fetchUserResourceStats(command);
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
