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
 * @author szgooru on 20-Jul-2018
 */
public class RouteUserLearnerProfileConfigurator implements RouteConfigurator {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(RouteUserLearnerProfileConfigurator.class);
  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;

    router.get(Constants.Route.API_USER_BASELINE_LEARNER_PROFILE)
        .handler(this::baselineLearnerProfile);
    router.get(Constants.Route.API_LEARNER_VECTORS).handler(this::learnerVectors);
    router.get(Constants.Route.API_LEARNER_PREFS).handler(this::learnerPrefs);
  }

  private void baselineLearnerProfile(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_READ_BASELINE_LEARNER_PROFILE, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

  private void learnerVectors(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_LEARNER_VECTORS,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }

  private void learnerPrefs(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_LEARNER_PREFS,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }
}
