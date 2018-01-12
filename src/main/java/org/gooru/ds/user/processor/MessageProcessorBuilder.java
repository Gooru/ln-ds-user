package org.gooru.ds.user.processor;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.processor.activeuserlist.ActiveUserListProcessor;
import org.gooru.ds.user.processor.user.distribution.UserDistributionProcessor;

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
            return new UserDistributionProcessor(vertx, message);
        case Constants.Message.MSG_OP_ACTIVE_USER_LIST:
            return new ActiveUserListProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_STATS_COMPETENCY:
        case Constants.Message.MSG_OP_USER_STATS_JOURNEYS:
        case Constants.Message.MSG_OP_USER_STATS_TIMESPENT:
            return new StubbedMessageProcessor(vertx, message);
        default:
            return null;
        }

    }

}
