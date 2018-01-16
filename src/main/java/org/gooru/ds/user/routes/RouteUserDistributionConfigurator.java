package org.gooru.ds.user.routes;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.routes.utils.RouteHandlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
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
        router.get(Constants.Route.API_ACTIVE_USER_LIST).handler(this::activeUserList);
    }

    private void activeUserList(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_ACTIVE_USER_LIST,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userDistribution(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_DISTRIBUTION,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

}
