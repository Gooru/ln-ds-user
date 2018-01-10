package org.gooru.ds.user.processor;

import org.gooru.ds.user.constants.Constants;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public class MessageProcessorBuilder {
    private MessageProcessorBuilder() {
        throw new AssertionError();
    }

    public static MessageProcessor buildProcessor(Vertx vertx, Message<JsonObject> message, String op) {
        switch (op) {
        case Constants.Message.MSG_OP_USER_DISTRIBUTION:
            return new StubbedMessageProcessor(vertx, message);
        default:
            return null;
        }

    }

}
