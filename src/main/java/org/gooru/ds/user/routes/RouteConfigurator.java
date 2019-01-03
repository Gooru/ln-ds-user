package org.gooru.ds.user.routes;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * @author ashish on 10/1/18.
 */
public interface RouteConfigurator {
  void configureRoutes(Vertx vertx, Router router, JsonObject config);
}
