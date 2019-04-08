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

public class RouteUserPortfolioConfigurator implements RouteConfigurator {

  private static final Logger LOGGER = LoggerFactory.getLogger(RouteUserPortfolioConfigurator.class);
  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
//    router.get(Constants.Route.API_USER_PORTFOLIO_COMPETENCY).handler(this::userPortfolioCompetency);
//    router.get(Constants.Route.API_USER_PORTFOLIO_DOMAIN).handler(this::userPortfolioDomain);
//    router.get(Constants.Route.API_USER_PORTFOLIO_SUBJECT).handler(this::userPortfolioSubject);
//    router.get(Constants.Route.API_USER_PORTFOLIO_CONTENT).handler(this::userPortfolioContent);
  }

//  private void userPortfolioCompetency(RoutingContext routingContext) {
//    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_PORTFOLIO_COMPETENCY,
//        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
//  }
//  
//
//  private void userPortfolioDomain(RoutingContext routingContext) {
//    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_PORTFOLIO_DOMAIN,
//        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
//  }
//  
//  
//  private void userPortfolioSubject(RoutingContext routingContext) {
//    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_PORTFOLIO_SUBJECT,
//        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
//  }
//  
//  
//  private void userPortfolioContent(RoutingContext routingContext) {
//    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT,
//        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
//  }
  
}
