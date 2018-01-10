package org.gooru.ds.user.processor;

import org.gooru.ds.user.responses.MessageResponse;
import org.gooru.ds.user.responses.MessageResponseFactory;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 1/10/18.
 */
public class StubbedMessageProcessor implements MessageProcessor {

    private final Vertx vertx;
    private final Message<JsonObject> message;

    public StubbedMessageProcessor(Vertx vertx, Message<JsonObject> message) {
        this.vertx = vertx;
        this.message = message;
    }

    @Override
    public Future<MessageResponse> process() {
        Future<MessageResponse> result = Future.future();
        result.complete(
            MessageResponseFactory.createOkayResponse(new JsonObject().put("message", "This is stubbed response")));
        return result;
    }
}
