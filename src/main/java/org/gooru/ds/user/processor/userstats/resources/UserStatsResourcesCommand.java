package org.gooru.ds.user.processor.userstats.resources;

import org.gooru.ds.user.app.components.AppConfiguration;
import org.gooru.ds.user.app.data.ActiveDuration;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserStatsResourcesCommand {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsResourcesCommand.class);
  private String user;
  private String contentType;
  private String activeDuration;
  private Integer offset;
  private Integer limit;

  public Integer getOffset() {
    return offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public String getActiveDuration() {
    return activeDuration;
  }

  public void setActiveDuration(String activeDuration) {
    this.activeDuration = activeDuration;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getUserId() {
    return user;
  }

  static UserStatsResourcesCommand builder(JsonObject requestBody) {
    UserStatsResourcesCommand result = UserStatsResourcesCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserStatsResourceCommandBean asBean() {
    UserStatsResourceCommandBean bean = new UserStatsResourceCommandBean();
    bean.user = user;
    bean.contentType = contentType;
    bean.activeDuration = activeDuration;
    bean.limit = limit;
    bean.offset = offset;

    return bean;
  }

  private static UserStatsResourcesCommand buildFromJsonObject(JsonObject requestBody) {
    UserStatsResourcesCommand result = new UserStatsResourcesCommand();
    result.user = requestBody.getString(CommandAttributes.USER_ID);
    result.contentType = requestBody.getString(CommandAttributes.CONTENT_TYPE);
    result.activeDuration = requestBody.getString(CommandAttributes.ACTIVE_DURATION);
    Integer offset = getAsInt(requestBody, CommandAttributes.OFFSET);
    Integer limit = getAsInt(requestBody, CommandAttributes.LIMIT);
    setOffsetAndLimit(offset, limit, result);


    return result;
  }

  private void validate() {

    if (user == null) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided for request");
    }

    if (contentType == null) {
      LOGGER.info("Resource Content Type not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Resource Content Type not provided in the request");
    }

    if (activeDuration != null && !ActiveDuration.isValidDuration(activeDuration)) {
      LOGGER.info("Invalid active duration provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid active duration");
    }
    if (offset == null || limit == null) {
      LOGGER.info("Invalid offset/limit provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid offset/limit");
    }
  }

  private static void setOffsetAndLimit(Integer offset, Integer limit,
      UserStatsResourcesCommand command) {
    if (offset == null) {
      command.offset = AppConfiguration.getInstance().getDefaultOffset();
    } else {
      if (offset >= 0) {
        command.offset = offset;
      } else {
        command.offset = null;
      }
    }
    if (limit == null) {
      command.limit = AppConfiguration.getInstance().getDefaultLimit();
    } else {
      Integer maxLimit = AppConfiguration.getInstance().getDefaultMaxLimit();
      if (limit > 0 && limit <= maxLimit) {
        command.limit = limit;
      } else if (limit > maxLimit) {
        command.limit = maxLimit;
      } else {
        command.limit = null;
      }
    }
  }


  private static Integer getAsInt(JsonObject requestBody, String key) {
    String value = requestBody.getString(key);
    Integer result = null;
    if (key != null) {
      try {
        result = Integer.valueOf(value);
      } catch (NumberFormatException e) {
        LOGGER.info("Invalid number format for {}", key);
        result = null;
      }
    }
    return result;
  }

  public static class UserStatsResourceCommandBean {
    private String user;
    private String contentType;
    private String activeDuration;
    private Integer offset;
    private Integer limit;

    public String getActiveDuration() {
      return activeDuration;
    }

    public void setActiveDuration(String activeDuration) {
      this.activeDuration = activeDuration;
    }

    public String getContentType() {
      return contentType;
    }

    public void setContentType(String contentType) {
      this.contentType = contentType;
    }

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public Integer getOffset() {
      return offset;
    }

    public void setOffset(Integer offset) {
      this.offset = offset;
    }

    public Integer getLimit() {
      return limit;
    }

    public void setLimit(Integer limit) {
      this.limit = limit;
    }

  }

  static class CommandAttributes {
    private static final String USER_ID = "user";
    private static final String CONTENT_TYPE = "contentType";
    private static final String ACTIVE_DURATION = "activeDuration";
    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";


    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
