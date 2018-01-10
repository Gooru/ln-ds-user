package org.gooru.ds.user.app.components;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public interface Initializer {

    void initializeComponent(Vertx vertx, JsonObject config);

}
