package org.gooru.ds.user.responses;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.constants.HttpConstants;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
public final class MessageResponseFactory {

  private static final String API_VERSION_DEPRECATED = "API version is deprecated";

  private MessageResponseFactory() {
    throw new AssertionError();
  }

  public static MessageResponse createInvalidRequestResponse() {
    return new MessageResponse.Builder().setStatusBadRequest()
        .setResponseBody(new JsonObject().put(Constants.Message.MSG_MESSAGE, "Invalid request"))
        .build();
  }

  public static MessageResponse createForbiddenResponse() {
    return new MessageResponse.Builder().setStatusForbidden()
        .setResponseBody(new JsonObject().put(Constants.Message.MSG_MESSAGE, "Forbidden")).build();
  }

  public static MessageResponse createInternalErrorResponse() {
    return new MessageResponse.Builder().setStatusInternalError()
        .setResponseBody(new JsonObject().put(Constants.Message.MSG_MESSAGE, "Internal error"))
        .build();
  }

  public static MessageResponse createInvalidRequestResponse(String message) {
    return new MessageResponse.Builder().setStatusBadRequest()
        .setResponseBody(new JsonObject().put(Constants.Message.MSG_MESSAGE, message)).build();
  }

  public static MessageResponse createForbiddenResponse(String message) {
    return new MessageResponse.Builder().setStatusForbidden()
        .setResponseBody(new JsonObject().put(Constants.Message.MSG_MESSAGE, message)).build();
  }

  public static MessageResponse createInternalErrorResponse(String message) {
    return new MessageResponse.Builder().setStatusInternalError()
        .setResponseBody(new JsonObject().put(Constants.Message.MSG_MESSAGE, message)).build();
  }

  public static MessageResponse createNotFoundResponse(String message) {
    return new MessageResponse.Builder().setStatusNotFound()
        .setResponseBody(new JsonObject().put(Constants.Message.MSG_MESSAGE, message)).build();
  }

  public static MessageResponse createOkayResponse(JsonObject body) {
    return new MessageResponse.Builder().setStatusOkay().setResponseBody(body).build();
  }

  public static MessageResponse createOkayResponseWithCompression(JsonObject body) {
    return new MessageResponse.Builder().setStatusOkay().setResponseBody(body)
        .setHeader("Content-Encoding", "gzip").build();
  }

  public static MessageResponse createOkayResponse() {
    return new MessageResponse.Builder().setStatusOkay().build();
  }

  public static MessageResponse createCreatedResponse(String location) {
    return new MessageResponse.Builder().setStatusCreated()
        .setHeader(HttpConstants.HEADER_LOCATION, location).build();
  }

  public static MessageResponse createNoContentResponse() {
    return new MessageResponse.Builder().setStatusNoOutput().setResponseBody(new JsonObject())
        .build();
  }

  public static MessageResponse createVersionDeprecatedResponse() {
    return new MessageResponse.Builder().setStatusHttpCode(HttpConstants.HttpStatus.GONE)
        .setContentTypeJson().setResponseBody(
            new JsonObject().put(Constants.Message.MSG_MESSAGE, API_VERSION_DEPRECATED))
        .build();
  }
}
