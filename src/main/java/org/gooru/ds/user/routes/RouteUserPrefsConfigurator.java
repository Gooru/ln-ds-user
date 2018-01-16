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
 * @author ashish on 12/1/18.
 */
public class RouteUserPrefsConfigurator implements RouteConfigurator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteUserPrefsConfigurator.class);
    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
        router.get(Constants.Route.API_USER_PREFS_CONTENT).handler(this::userPrefsContent);
        router.get(Constants.Route.API_USER_PREFS_CURATORS).handler(this::userPrefsCurators);
        router.get(Constants.Route.API_USER_PREFS_PROVIDERS).handler(this::userPrefsProviders);
    }

    private void userPrefsCurators(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_PREFS_CURATORS,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userPrefsContent(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_PREFS_CONTENT,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userPrefsProviders(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_PREFS_PROVIDERS,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }
}

