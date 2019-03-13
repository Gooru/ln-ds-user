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
 * @author mukul@gooru
 */
public class RouteNavigatorCourseConfigurator implements RouteConfigurator {

  private static final Logger LOGGER = LoggerFactory.getLogger(RouteUserDetailsConfigurator.class);
  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
    router.get(Constants.Route.API_NC_PERF_VS_COMPLETION).handler(this::userPvC);
    router.post(Constants.Route.API_STATS_COMPETENCY_COMPLETION).handler(this::competencyStats);
  }

  private void userPvC(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_USERS_PERF_VS_COMPLETION, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

  private void competencyStats(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_STATS_COMPETENCY_COMPLETION,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }
}
