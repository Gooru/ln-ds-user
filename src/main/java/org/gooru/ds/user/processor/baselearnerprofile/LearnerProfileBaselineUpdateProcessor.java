package org.gooru.ds.user.processor.baselearnerprofile;

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
public class LearnerProfileBaselineUpdateProcessor implements MessageProcessor {

    private final Vertx vertx;
    private final Message<JsonObject> message;
    private final Future<MessageResponse> result;

    private static final Logger LOGGER = LoggerFactory.getLogger(LearnerProfileBaselineUpdateProcessor.class);
    private EventBusMessage eventBusMessage;
    private final LearnerProfileReadService learnerProfileReadService =
            new LearnerProfileReadService(DBICreator.getDbiForDefaultDS());

    public LearnerProfileBaselineUpdateProcessor(Vertx vertx, Message<JsonObject> message) {
        this.vertx = vertx;
        this.message = message;
        this.result = Future.future();
    }

    @Override
    public Future<MessageResponse> process() {
        try {
            this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
            LearnerProfileBaselineUpdateCommand command = LearnerProfileBaselineUpdateCommand.builder(eventBusMessage.getRequestBody());
            updateBaselineLearnerProfile(command);
        } catch (Throwable throwable) {
            LOGGER.warn("Encountered exception", throwable);
            result.fail(throwable);
        }
        return result;
    }
    
    private void updateBaselineLearnerProfile(LearnerProfileBaselineUpdateCommand command) {
    	ExecutionStatus status = learnerProfileReadService.fetchCurrentLearnerProfileAndSetBaseline(command);
    	
    	if (status.isSuccessFul()) {    		
    		result.complete(MessageResponseFactory.createOkayResponse());
    	} else {
    		result.failed();
    	}
    }
}
