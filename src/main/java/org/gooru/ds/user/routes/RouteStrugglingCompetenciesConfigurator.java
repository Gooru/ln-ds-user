
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
 * @author szgooru Created On 27-Sep-2019
 */
public class RouteStrugglingCompetenciesConfigurator implements RouteConfigurator {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(RouteStrugglingCompetenciesConfigurator.class);
  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;

    router.get(Constants.Route.API_STRUGGLING_COMPETENCIES)
        .handler(this::strugglingCompetencies);
  }

  private void strugglingCompetencies(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_STRUGGLING_COMPETENCIES, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

}
