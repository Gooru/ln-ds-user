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


public class RouteUserActivityFeedbackConfigurator implements RouteConfigurator {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(RouteUserActivityFeedbackConfigurator.class);
  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
    router.post(Constants.Route.API_USER_ACTIVITY_FEEDBACKS_CREATE)
        .handler(this::createUserActivityFeedback);
    router.get(Constants.Route.API_USER_ACTIVITY_FEEDBACKS_FETCH)
        .handler(this::fetchUserActivityFeedbacks);
  }

  private void createUserActivityFeedback(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_USER_ACTIVITY_FEEDBACK_CREATE, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

  private void fetchUserActivityFeedbacks(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_USER_ACTIVITY_FEEDBACK_FETCH, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

}

