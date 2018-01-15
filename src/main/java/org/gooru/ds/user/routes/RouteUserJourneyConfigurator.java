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
 * @author mukul@gooru
 */
public class RouteUserJourneyConfigurator implements RouteConfigurator {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteUserJourneyConfigurator.class);
    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
        router.post(Constants.Route.API_USER_JOURNEY).handler(this::userJourney);
        router.get(Constants.Route.API_USER_PERF_COURSE).handler(this::userPerfCourse);
        router.get(Constants.Route.API_USER_PERF_LESSONS).handler(this::userPerfLessons);
        router.get(Constants.Route.API_USER_PERF_ASSESSMENTS).handler(this::userPerfAssessments);
        router.get(Constants.Route.API_USER_PERF_COLLECTIONS).handler(this::userPerfCollections);
        router.get(Constants.Route.API_USER_SUMMARY_ASSESSMENT).handler(this::userSummaryAssessment);
        router.get(Constants.Route.API_USER_SUMMARY_COLLECTION).handler(this::userSummaryCollection);
    }

    private void userJourney(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_JOURNEY, Constants.EventBus.MBEP_DISPATCHER);
    }
    
    private void userPerfCourse(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_PERF_COURSE, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void userPerfLessons(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_PERF_LESSONS, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void userPerfAssessments(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_PERF_ASSESSMENTS, Constants.EventBus.MBEP_DISPATCHER);
    }
    
    private void userPerfCollections(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_PERF_COLLECTIONS, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void userSummaryAssessment(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_SUMMARY_ASSESSMENT, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void userSummaryCollection(RoutingContext routingContext) {
        baseHandler(routingContext, Constants.Message.MSG_OP_USER_SUMMARY_COLLECTION, Constants.EventBus.MBEP_DISPATCHER);
    }

    private void baseHandler(RoutingContext routingContext, String op, String eventBusEndPoint) {
        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(Constants.Message.MSG_OP, op);
        eb.<JsonObject>send(eventBusEndPoint, RouteRequestUtility.getBodyForMessage(routingContext, true), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOGGER));

    }


}
