package org.gooru.ds.user.exceptions;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.constants.HttpConstants;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public final class HttpResponseWrapperException extends RuntimeException {
  private final HttpConstants.HttpStatus status;
  private final JsonObject payload;

  public HttpResponseWrapperException(HttpConstants.HttpStatus status, JsonObject payload) {
    this.status = status;
    this.payload = payload;
  }

  public HttpResponseWrapperException(HttpConstants.HttpStatus status, String message) {
    super(message);
    this.status = status;
    this.payload = new JsonObject().put(Constants.Message.MSG_MESSAGE, message);
  }

  public int getStatus() {
    return this.status.getCode();
  }

  public JsonObject getBody() {
    return this.payload;
  }
}
