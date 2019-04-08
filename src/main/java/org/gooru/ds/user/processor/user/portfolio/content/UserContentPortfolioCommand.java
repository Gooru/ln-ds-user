package org.gooru.ds.user.processor.user.portfolio.content;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

public class UserContentPortfolioCommand {
  
  private String activityType;
  private String user;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserContentPortfolioCommand.class);

  public String getActivityType() {
    return activityType;
  }

  public String getUserId() {
    return user;
  }

  static UserContentPortfolioCommand builder(JsonObject requestBody) {
    UserContentPortfolioCommand result =
        UserContentPortfolioCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserContentPortfolioCommandBean asBean() {
    UserContentPortfolioCommandBean bean = new UserContentPortfolioCommandBean();
    bean.user = user;
    bean.activityType = activityType;
    return bean;
  }

  private static UserContentPortfolioCommand buildFromJsonObject(JsonObject requestBody) {
    UserContentPortfolioCommand result = new UserContentPortfolioCommand();

    result.activityType = requestBody.getString(CommandAttributes.ACTIVITY_TYPE);
    result.user = requestBody.getString(CommandAttributes.USER_ID);
    return result;
  }

  private void validate() {

    if (user == null) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided for request");
    }

  }

  public static class UserContentPortfolioCommandBean {
    private String activityType;
    private String user;

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }
    
    public String getActivityType() {
      return activityType;
    }

    public void setActivityType(String activityType) {
      this.activityType = activityType;
    }

  }

  static class CommandAttributes {
    private static final String ACTIVITY_TYPE = "activityType";
    private static final String USER_ID = "user";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }


}
