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
        router.get(Constants.Route.API_USER_STATS_CONTENT).handler(this::userStatsContents);
        router.get(Constants.Route.API_USER_STATS_PROVIDER).handler(this::userStatsProviders);
        router.get(Constants.Route.API_USER_STATS_CURATOR).handler(this::userStatsCurators);
    }

    private void userStatsCurators(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_STATS_CURATORS,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userStatsProviders(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_STATS_PROVIDERS,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userStatsContents(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_STATS_CONTENTS,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userStatsJourneys(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_STATS_JOURNEYS,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userStatsCompetency(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_STATS_COMPETENCY,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userStatsTimespent(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_STATS_TIMESPENT,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

    private void userStatsCourses(RoutingContext routingContext) {
        RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_STATS_COURSES,
            Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
    }

}

