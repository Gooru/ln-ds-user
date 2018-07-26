package org.gooru.ds.user.routes;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.routes.utils.DeliveryOptionsBuilder;
import org.gooru.ds.user.routes.utils.RouteHandlerUtils;
import org.gooru.ds.user.routes.utils.RouteRequestUtility;
import org.gooru.ds.user.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * @author ashish on 10/1/18.
 */
class RouteInternalConfigurator implements RouteConfigurator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteInternalConfigurator.class);
    private EventBus eb = null;
    private long mbusTimeout;


    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
        router.get(Constants.Route.API_INTERNAL_INITIAL_LEARNER_PROFILE).handler(this::initialLearnerProfile);
        router.get(Constants.Route.API_INTERNAL_BASE_LEARNER_PROFILE).handler(this::baseLearnerProfile);
        
        LOGGER.debug("Configuring routes for internal route");
        router.route(Constants.Route.API_INTERNAL_BANNER).handler(routingContext -> {
            JsonObject result =
                new JsonObject().put("Organisation", "gooru.org").put("Product", "suggestions").put("purpose", "api")
                    .put("mission", "Honor the human right to education");
            routingContext.response().end(result.toString());
        });

        final MetricsService metricsService = MetricsService.create(vertx);
        router.route(Constants.Route.API_INTERNAL_METRICS).handler(routingContext -> {
            JsonObject ebMetrics = metricsService.getMetricsSnapshot(vertx);
            routingContext.response().end(ebMetrics.toString());
        });

    }
 
    private void initialLearnerProfile(RoutingContext routingContext) {
    	DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout)
    			.addHeader(Constants.Message.MSG_OP, Constants.Message.MSG_OP_INITIAL_LEARNER_PROFILE);
    	eb.<JsonObject>send(Constants.EventBus.MBEP_DISPATCHER, RouteRequestUtility.getBodyForMessage(routingContext, true), options);            
    	routingContext.response().setStatusCode(200).end();   


    }

    private void baseLearnerProfile(RoutingContext routingContext) {
    	DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout)
    			.addHeader(Constants.Message.MSG_OP, Constants.Message.MSG_OP_BASE_LEARNER_PROFILE);
    	eb.<JsonObject>send(Constants.EventBus.MBEP_DISPATCHER, RouteRequestUtility.getBodyForMessage(routingContext, true), options);            
    	routingContext.response().setStatusCode(200).end();   
    }
}
