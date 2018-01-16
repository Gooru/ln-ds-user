package org.gooru.ds.user.routes.utils;

import org.gooru.ds.user.constants.Constants;
import org.slf4j.Logger;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @author ashish on 16/1/18.
 */
public class RouteHandlerUtils {

    public static void baseHandler(EventBus eb, RoutingContext routingContext, String op, String eventBusEndPoint,
        long mbusTimeout, Logger logger) {

        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(Constants.Message.MSG_OP, op);
        eb.<JsonObject>send(eventBusEndPoint, RouteRequestUtility.getBodyForMessage(routingContext, true), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, logger));

    }

}
