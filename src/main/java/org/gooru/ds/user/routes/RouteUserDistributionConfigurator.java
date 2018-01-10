package org.gooru.ds.user.routes;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.routes.utils.DeliveryOptionsBuilder;
import org.gooru.ds.user.routes.utils.RouteRequestUtility;
import org.gooru.ds.user.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * @author ashish on 10/1/18.
 */
public class RouteUserDistributionConfigurator implements RouteConfigurator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteUserDistributionConfigurator.class);
    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
        router.get(Constants.Route.API_USER_DISTRIBUTION).handler(this::userDistribution);
    }

    private void userDistribution(RoutingContext routingContext) {
        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(Constants.Message.MSG_OP, Constants.Message.MSG_OP_USER_DISTRIBUTION);
        eb.<JsonObject>send(Constants.EventBus.MBEP_USER_DISTRIBUTION,
            RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOGGER));
    }

}
