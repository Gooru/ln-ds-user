package org.gooru.ds.user.responses.auth;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public interface AuthSessionResponseHolder extends AuthResponseHolder {
  JsonObject getSession();
}
