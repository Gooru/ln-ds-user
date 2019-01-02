package org.gooru.ds.user.processor.usergrades;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 17/1/18.
 */
class UserGradesCommand {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserGradesCommand.class);
  private String user;

  public String getUser() {
    return user;
  }

  public UserGradesCommandBean asBean() {
    UserGradesCommandBean bean = new UserGradesCommandBean();
    bean.user = user;
    return bean;
  }

  static UserGradesCommand builder(JsonObject requestBody) {
    UserGradesCommand result = UserGradesCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  private static UserGradesCommand buildFromJsonObject(JsonObject requestBody) {
    UserGradesCommand result = new UserGradesCommand();
    result.user = requestBody.getString(CommandAttributes.USER);
    return result;
  }

  private void validate() {
    if (user == null) {
      LOGGER.info("User not provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided for request");
    }
  }

  public static class UserGradesCommandBean {
    private String user;

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }
  }

  static class CommandAttributes {
    private static final String USER = "user";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
