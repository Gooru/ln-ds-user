package org.gooru.ds.user.app.data;

import java.util.UUID;
import org.gooru.ds.user.constants.Constants;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 1/10/18.
 */
public final class EventBusMessage {

  private final String sessionToken;
  private final JsonObject requestBody;
  private final UUID userId;
  private final JsonObject session;
  private final TenantContext tenantContext;

  public String getSessionToken() {
    return sessionToken;
  }

  public JsonObject getRequestBody() {
    return requestBody;
  }

  public UUID getUserId() {
    return userId;
  }

  public JsonObject getSession() {
    return session;
  }
  
  public String tenant() {
    return this.tenantContext.tenant();
  }

  public String tenantRoot() {
    return this.tenantContext.tenantRoot();
  }

  private EventBusMessage(String sessionToken, JsonObject requestBody, UUID userId,
      JsonObject session) {
    this.sessionToken = sessionToken;
    this.requestBody = requestBody;
    this.userId = userId;
    this.session = session;
    this.tenantContext = new TenantContext(session);
  }

  public static EventBusMessage eventBusMessageBuilder(Message<JsonObject> message) {
    String sessionToken = message.body().getString(Constants.Message.MSG_SESSION_TOKEN);
    String userId = message.body().getString(Constants.Message.MSG_USER_ID);
    JsonObject requestBody = message.body().getJsonObject(Constants.Message.MSG_HTTP_BODY);
    JsonObject session = message.body().getJsonObject(Constants.Message.MSG_KEY_SESSION);

    return new EventBusMessage(sessionToken, requestBody, UUID.fromString(userId), session);
  }

  private static class TenantContext {

    private static final String TENANT = "tenant";
    private static final String TENANT_ID = "tenant_id";
    private static final String TENANT_ROOT = "tenant_root";

    private final String tenantId;
    private final String tenantRoot;

    TenantContext(JsonObject session) {
      JsonObject tenantJson = session.getJsonObject(TENANT);
      if (tenantJson == null || tenantJson.isEmpty()) {
        throw new IllegalStateException("Tenant Context invalid");
      }
      this.tenantId = tenantJson.getString(TENANT_ID);
      if (tenantId == null || tenantId.isEmpty()) {
        throw new IllegalStateException("Tenant Context with invalid tenant");
      }
      this.tenantRoot = tenantJson.getString(TENANT_ROOT);
    }

    public String tenant() {
      return this.tenantId;
    }

    public String tenantRoot() {
      return this.tenantRoot;
    }
  }
}
