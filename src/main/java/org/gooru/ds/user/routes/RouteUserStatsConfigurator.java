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
 * @author ashish on 12/1/18.
 */
public class RouteUserStatsConfigurator implements RouteConfigurator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteUserStatsConfigurator.class);
    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
        router.get(Constants.Route.API_USER_STATS_COMPETENCY).handler(this::userStatsCompetency);
        router.get(Constants.Route.API_USER_STATS_JOURNEYS).handler(this::userStatsJourneys);
        router.get(Constants.Route.API_USER_STATS_TIMESPENT).handler(this::userStatsTimespent);
        router.get(Constants.Route.API_USER_STATS_COURSES).handler(this::userStatsCourses);
    }

    private void userStatsJourneys(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_STATS_JOURNEYS, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void userStatsCompetency(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_STATS_COMPETENCY, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void userStatsTimespent(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_STATS_TIMESPENT, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void userStatsCourses(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_STATS_COURSES, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void baseHandler(RoutingContext routingContext, String op, String eventBusEndPoint) {
        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(Constants.Message.MSG_OP, op);
        eb.<JsonObject>send(eventBusEndPoint, RouteRequestUtility.getBodyForMessage(routingContext, true), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOGGER));

    }

}

