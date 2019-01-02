package org.gooru.ds.user.processor.userprefs.content;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsContentCommand {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserPrefsContentCommand.class);
  private String user;

  public String getUser() {
    return user;
  }

  public UserPrefsContentCommandBean asBean() {
    UserPrefsContentCommandBean bean = new UserPrefsContentCommandBean();
    bean.user = user;
    return bean;
  }

  static UserPrefsContentCommand builder(JsonObject requestBody) {
    UserPrefsContentCommand result = UserPrefsContentCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  private static UserPrefsContentCommand buildFromJsonObject(JsonObject requestBody) {
    UserPrefsContentCommand result = new UserPrefsContentCommand();
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

  public static class UserPrefsContentCommandBean {
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
