package org.gooru.ds.user.routes.utils;

import org.gooru.ds.user.constants.Constants;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.ext.web.RoutingContext;

/**
 * @author ashish on 10/1/18.
 */
public final class DeliveryOptionsBuilder {
  private DeliveryOptionsBuilder() {
    throw new AssertionError();
  }

  public static DeliveryOptions buildWithApiVersion(RoutingContext context) {
    final String apiVersion = context.request().getParam("version");
    VersionValidatorUtility.validateVersion(apiVersion);
    return new DeliveryOptions().addHeader(Constants.Message.MSG_API_VERSION, apiVersion);
  }

}
