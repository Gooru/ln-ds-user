package org.gooru.ds.user.processor.competencymatrix;

import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 17/1/18.
 */
public class CompetencyMatrixProcessor implements MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyMatrixProcessor.class);
    private final Vertx vertx;
    private final Message<JsonObject> message;
    private final Future<MessageResponse> result;

    public CompetencyMatrixProcessor(Vertx vertx, Message<JsonObject> message) {
        this.vertx = vertx;
        this.message = message;
        this.result = Future.future();
    }

    @Override
    public Future<MessageResponse> process() {
        return result;
    }
}
