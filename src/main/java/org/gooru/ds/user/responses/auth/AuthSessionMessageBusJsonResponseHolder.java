package org.gooru.ds.user.responses.auth;

import org.gooru.ds.user.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
class AuthSessionMessageBusJsonResponseHolder implements AuthSessionResponseHolder {

  private static final Logger LOG = LoggerFactory.getLogger(AuthSessionResponseHolder.class);
  private final Message<JsonObject> message;
  private boolean isAuthorized = false;

  public AuthSessionMessageBusJsonResponseHolder(Message<JsonObject> message) {
    this.message = message;
    if (message != null) {
      LOG.debug("Received response from Auth End point : {}", message.body().toString());
      String result = message.headers().get(Constants.Message.MSG_OP_STATUS);
      LOG.debug("Received header from Auth response : {}", result);
      if (result != null && result.equalsIgnoreCase(Constants.Message.MSG_OP_STATUS_SUCCESS)) {
        isAuthorized = true;
      }
    }
  }

  @Override
  public boolean isAuthorized() {
    return isAuthorized;
  }

  @Override
  public JsonObject getSession() {
    if (!isAuthorized) {
      return null;
    }
    return message.body();
  }

  @Override
  public boolean isAnonymous() {
    JsonObject jsonObject = message.body();
    String userId = jsonObject.getString(Constants.Message.MSG_USER_ID);
    return !(userId != null && !userId.isEmpty()
        && !userId.equalsIgnoreCase(Constants.Message.MSG_USER_ANONYMOUS));
  }

  @Override
  public String getUser() {
    JsonObject jsonObject = message.body();
    return jsonObject.getString(Constants.Message.MSG_USER_ID);
  }
}
