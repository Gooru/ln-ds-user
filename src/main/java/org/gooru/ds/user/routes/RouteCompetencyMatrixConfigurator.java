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
public class RouteCompetencyMatrixConfigurator implements RouteConfigurator {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(RouteCompetencyMatrixConfigurator.class);
  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(Constants.EventBus.MBUS_TIMEOUT, 30L) * 1000;
    router.get(Constants.Route.API_COMPETENCY_MATRIX).handler(this::userCompetencyMatrix);
    router.get(Constants.Route.API_COMPETENCY_SUBJECTS).handler(this::competencySubjects);
    router.get(Constants.Route.API_COURSE_COMPETENCY_MATRIX)
        .handler(this::userCourseCompetencyMatrix);
    router.get(Constants.Route.API_DOMAIN_COMPETENCY_MATRIX)
        .handler(this::userDomainCompetencyMatrix);

    router.get(Constants.Route.API_SKYLINE_COMPETENCY_NEXT)
        .handler(this::userSkylineCompetencyNext);
    router.get(Constants.Route.API_COMPETENCY_MATRIX_COORDS)
        .handler(this::userCompetencyMatrixCoords);
    router.get(Constants.Route.API_GRADE_BOUNDARY).handler(this::gradeBoundary);
    router.get(Constants.Route.API_GRADES).handler(this::grades);
    router.get(Constants.Route.API_GRADE_COMPETENCIES).handler(this::gradeCompetency);
  }

  private void userCompetencyMatrix(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_COMPETENCY_MATRIX,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }

  private void competencySubjects(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_COMPETENCY_SUBJECTS,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }

  private void userCourseCompetencyMatrix(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_COMPETENCY_MATRIX_COURSE, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

  private void userDomainCompetencyMatrix(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_COMPETENCY_MATRIX_DOMAIN, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

  // Mukul
  private void userSkylineCompetencyNext(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_SKYLINE_COMPETENCY_NEXT, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

  private void userCompetencyMatrixCoords(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext,
        Constants.Message.MSG_OP_COMPETENCY_MATRIX_COORDS, Constants.EventBus.MBEP_DISPATCHER,
        mbusTimeout, LOGGER);
  }

  private void grades(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_GRADES,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }

  private void gradeBoundary(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_GRADE_BOUNDARY,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }
  
  private void gradeCompetency(RoutingContext routingContext) {
    RouteHandlerUtils.baseHandler(eb, routingContext, Constants.Message.MSG_OP_GRADE_COMPETENCIES,
        Constants.EventBus.MBEP_DISPATCHER, mbusTimeout, LOGGER);
  }
}
