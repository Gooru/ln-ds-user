package org.gooru.ds.user.responses.auth;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public final class AuthSessionResponseHolderBuilder {

  public static AuthSessionResponseHolder build(Message<JsonObject> message) {
    return new AuthSessionMessageBusJsonResponseHolder(message);
  }

  private AuthSessionResponseHolderBuilder() {
    throw new AssertionError();
  }
}
