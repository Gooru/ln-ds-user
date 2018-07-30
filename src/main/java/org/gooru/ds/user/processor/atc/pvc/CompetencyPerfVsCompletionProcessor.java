package org.gooru.ds.user.processor.atc.pvc;

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
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class CompetencyPerfVsCompletionProcessor implements MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyPerfVsCompletionProcessor.class);
    private static final String PVC = "pvc";
    private final Vertx vertx;
    private final Message<JsonObject> message;
    private final Future<MessageResponse> result;
    private EventBusMessage eventBusMessage;
    private CompetencyPerfVsCompletionService perfVsCompetencyService =
        new CompetencyPerfVsCompletionService();

    public CompetencyPerfVsCompletionProcessor(Vertx vertx, Message<JsonObject> message) {
        this.vertx = vertx;
        this.message = message;
        this.result = Future.future();
    }

    @Override
    public Future<MessageResponse> process() {
        try {
            this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
            CompetencyPerfVsCompletionCommand command =
                CompetencyPerfVsCompletionCommand.builder(eventBusMessage.getRequestBody());
            fetchUserCompetencyMatrix(command);
        } catch (Throwable throwable) {
            LOGGER.warn("Encountered exception", throwable);
            result.fail(throwable);
        }
        return result;
    }

    private void fetchUserCompetencyMatrix(CompetencyPerfVsCompletionCommand command) {
    	JsonObject jsonObj = new JsonObject();
        try {
            JsonArray pvcArray =
            		perfVsCompetencyService.fetchUserPerformanceCompletion(command);            
            result.complete(MessageResponseFactory.createOkayResponse(jsonObj.put(PVC, pvcArray)));
        } catch (DecodeException e) {
            LOGGER.warn("Not able to convert data to JSON", e);
            result.fail(e);
        } catch (Throwable throwable) {
            LOGGER.warn("Encountered exception", throwable);
            result.fail(throwable);
        }

    }

}
