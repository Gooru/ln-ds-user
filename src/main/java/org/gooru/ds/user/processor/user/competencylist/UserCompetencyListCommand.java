package org.gooru.ds.user.processor.user.competencylist;

import org.gooru.ds.user.app.components.AppConfiguration;
import org.gooru.ds.user.app.data.ActiveDuration;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

public class UserCompetencyListCommand {

  private String activeDuration;
  private String user;
  private Integer offset;
  private Integer limit;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserCompetencyListCommand.class);

  public String getActiveDuration() {
    return activeDuration;
  }

  public String getUser() {
    return user;
  }

  public Integer getOffset() {
    return offset;
  }

  public Integer getLimit() {
    return limit;
  }

  static UserCompetencyListCommand builder(JsonObject requestBody) {
    UserCompetencyListCommand result = UserCompetencyListCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserCompetencyListCommandBean asBean() {
    UserCompetencyListCommandBean bean = new UserCompetencyListCommandBean();
    bean.user = user;
    bean.activeDuration = activeDuration;
    bean.limit = limit;
    bean.offset = offset;

    return bean;
  }

  private static UserCompetencyListCommand buildFromJsonObject(JsonObject requestBody) {
    UserCompetencyListCommand result = new UserCompetencyListCommand();

    result.activeDuration = requestBody.getString(CommandAttributes.ACTIVE_DURATION);
    result.user = requestBody.getString(CommandAttributes.USER);
    Integer offset = getAsInt(requestBody, CommandAttributes.OFFSET);
    Integer limit = getAsInt(requestBody, CommandAttributes.LIMIT);
    setOffsetAndLimit(offset, limit, result);

    return result;
  }

  private void validate() {
    if (activeDuration != null && !ActiveDuration.isValidDuration(activeDuration)) {
      LOGGER.info("Invalid active duration provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid active duration");
    }
    if (user == null) {
      LOGGER.info("User not provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided for request");
    }

    if (offset == null || limit == null) {
      LOGGER.info("Invalid offset/limit provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid offset/limit");
    }
  }

  private static void setOffsetAndLimit(Integer offset, Integer limit,
      UserCompetencyListCommand command) {
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


  public static class UserCompetencyListCommandBean {
    private String activeDuration;
    private String user;
    private Integer offset;
    private Integer limit;

    public String getActiveDuration() {
      return activeDuration;
    }

    public void setActiveDuration(String activeDuration) {
      this.activeDuration = activeDuration;
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
    private static final String ACTIVE_DURATION = "activeDuration";
    private static final String USER = "user";
    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
