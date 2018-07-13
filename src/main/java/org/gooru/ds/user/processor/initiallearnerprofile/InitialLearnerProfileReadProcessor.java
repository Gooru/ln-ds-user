package org.gooru.ds.user.processor.initiallearnerprofile;

import org.gooru.ds.user.app.data.EventBusMessage;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.constants.ExecutionStatus;
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
 * @author mukul@gooru
 * 
 */
public class InitialLearnerProfileReadProcessor implements MessageProcessor {
	
    private final Vertx vertx;
    private final Message<JsonObject> message;
    private final Future<MessageResponse> result;
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialLearnerProfileReadProcessor.class);
    private EventBusMessage eventBusMessage;
    private final InitialLearnerProfileReadService baseUserProfileReadService =
        new InitialLearnerProfileReadService(DBICreator.getDbiForDefaultDS());

    public InitialLearnerProfileReadProcessor(Vertx vertx, Message<JsonObject> message) {
        this.vertx = vertx;
        this.message = message;
        this.result = Future.future();
    }

    @Override
    public Future<MessageResponse> process() {
        try {
            this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
            InitialLearnerProfileReadCommand command = InitialLearnerProfileReadCommand.builder(eventBusMessage.getRequestBody());
            updateInitialLearnerProfile(command);
        } catch (Throwable throwable) {
            LOGGER.warn("Encountered exception", throwable);
            result.fail(throwable);
        }
        return result;
    }

    private void updateInitialLearnerProfile(InitialLearnerProfileReadCommand command) {        
            ExecutionStatus status = baseUserProfileReadService.fetchBaseProfile(command);
            if (status.isSuccessFul()) {
            	result.complete(MessageResponseFactory.createOkayResponse());            	
            } else {
            	result.failed();
            }
    }
}
