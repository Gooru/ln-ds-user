package org.gooru.ds.user.bootstrap.verticles;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.exceptions.MessageResponseWrapperException;
import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.processor.MessageProcessorBuilder;
import org.gooru.ds.user.responses.MessageResponse;
import org.gooru.ds.user.responses.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public class DispatcherVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherVerticle.class);

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        EventBus eb = vertx.eventBus();
        eb.localConsumer(Constants.EventBus.MBEP_DISPATCHER, this::processMessage).completionHandler(result -> {
            if (result.succeeded()) {
                LOGGER.info("Tracker end point ready to listen");
                startFuture.complete();
            } else {
                LOGGER.error("Error registering the Tracker handler. Halting the machinery");
                startFuture.fail(result.cause());
                Runtime.getRuntime().halt(1);
            }
        });
    }

    private void processMessage(Message<JsonObject> message) {
        String op = message.headers().get(Constants.Message.MSG_OP);
        MessageProcessor processor = MessageProcessorBuilder.buildProcessor(vertx, message, op);
        if (processor != null) {
            vertx.<MessageResponse>executeBlocking(future -> {
                processor.process().setHandler(asyncResult -> future.complete(asyncResult.result()));
            }, event -> {
                finishResponse(message, event);
            });
        } else {
            LOGGER.warn("Invalid operation type");
            ResponseUtil.processFailure(message);
        }
    }

    private void finishResponse(Message<JsonObject> message, AsyncResult<MessageResponse> asyncResult) {
        if (asyncResult.succeeded()) {
            message.reply(asyncResult.result().reply(), asyncResult.result().deliveryOptions());
        } else {
            LOGGER.warn("Failed to process command", asyncResult.cause());
            if (asyncResult.cause() instanceof HttpResponseWrapperException) {
                HttpResponseWrapperException exception = (HttpResponseWrapperException) asyncResult.cause();
                message.reply(new JsonObject().put(Constants.Message.MSG_HTTP_STATUS, exception.getStatus())
                    .put(Constants.Message.MSG_HTTP_BODY, exception.getBody())
                    .put(Constants.Message.MSG_HTTP_HEADERS, new JsonObject()));
            } else if (asyncResult.cause() instanceof MessageResponseWrapperException) {
                MessageResponseWrapperException exception = (MessageResponseWrapperException) asyncResult.cause();
                message.reply(exception.getMessageResponse().reply(), exception.getMessageResponse().deliveryOptions());
            } else {
                message.reply(new JsonObject().put(Constants.Message.MSG_HTTP_STATUS, 500)
                    .put(Constants.Message.MSG_HTTP_BODY, new JsonObject())
                    .put(Constants.Message.MSG_HTTP_HEADERS, new JsonObject()));
            }
        }
    }

}
