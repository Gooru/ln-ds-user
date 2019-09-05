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

public class RouteUserPortfolioContentConfigurator implements RouteConfigurator {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(RouteUserPortfolioContentConfigurator.class);
  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
    router.get(Constants.Route.API_USER_PORTFOLIO_CONTENT_ITEMS_PERF)
        .handler(this::userPortfolioItemsPerf);
    router.get(Constants.Route.API_USER_PORTFOLIO_CONTENT_ITEM_PERF)
        .handler(this::userPortfolioItemPerf);
    router.get(Constants.Route.API_USER_PORTFOLIO_CONTENT_ASMT_SUMMARY)
        .handler(this::userPortfolioAsmtSummary);
    router.get(Constants.Route.API_USER_PORTFOLIO_CONTENT_COLL_SUMMARY)
        .handler(this::userPortfolioCollSummary);
    router.get(Constants.Route.API_USER_PORTFOLIO_CONTENT_OA_SUMMARY)
        .handler(this::userPortfolioOASummary);
  }

  private void userPortfolioItemsPerf(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_ITEMS_PERF,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }

  private void userPortfolioItemPerf(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_ITEM_PERF,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }

  private void userPortfolioAsmtSummary(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_ASMT_SUMMARY,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }
  
  private void userPortfolioCollSummary(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_COLL_SUMMARY,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }
  
  private void userPortfolioOASummary(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_OA_SUMMARY,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }

}
