package org.gooru.ds.user.processor;

import org.gooru.ds.user.responses.MessageResponse;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public interface MessageProcessor {

    Future<MessageResponse> process();

    static MessageProcessor buildStubbedProcessor(Vertx vertx, Message<JsonObject> message) {
        return new StubbedMessageProcessor(vertx, message);
    }
}
