package org.gooru.ds.user.processor.activeuserlist;

import org.gooru.ds.user.app.components.AppConfiguration;
import org.gooru.ds.user.app.data.ActiveDuration;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 11/1/18.
 */
class ActiveUserListCommand {
  private Integer offset;
  private Integer limit;
  private String activeDuration;
  private String subject;

  private static final Logger LOGGER = LoggerFactory.getLogger(ActiveUserListCommand.class);

  public Integer getOffset() {
    return offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public String getActiveDuration() {
    return activeDuration;
  }

  public String getSubject() {
    return subject;
  }

  static ActiveUserListCommand builder(JsonObject requestBody) {
    ActiveUserListCommand result = ActiveUserListCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public ActiveUserListCommandBean asBean() {
    ActiveUserListCommandBean bean = new ActiveUserListCommandBean();
    bean.subject = subject;
    bean.activeDuration = activeDuration;
    bean.limit = limit;
    bean.offset = offset;
    return bean;
  }

  private static ActiveUserListCommand buildFromJsonObject(JsonObject requestBody) {
    ActiveUserListCommand result = new ActiveUserListCommand();
    Integer offset = getAsInt(requestBody, CommandAttributes.OFFSET);
    Integer limit = getAsInt(requestBody, CommandAttributes.LIMIT);

    populateOffsetAndLimit(offset, limit, result);

    result.activeDuration = requestBody.getString(CommandAttributes.ACTIVE_DURATION);
    result.subject = requestBody.getString(CommandAttributes.SUBJECT);
    return result;
  }

  private static void populateOffsetAndLimit(Integer offset, Integer limit,
      ActiveUserListCommand command) {
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

  private void validate() {
    if (offset == null || limit == null) {
      LOGGER.info("Invalid offset/limit provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid offset/limit");
    }
    if (activeDuration != null && !ActiveDuration.isValidDuration(activeDuration)) {
      LOGGER.info("Invalid active duration provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid active duration");
    }
    if (subject == null) {
      LOGGER.info("Subject filter not provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Subject filter not provided for request");
    }
  }

  public static class ActiveUserListCommandBean {
    private Integer offset;
    private Integer limit;
    private String activeDuration;
    private String subject;

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

    public String getActiveDuration() {
      return activeDuration;
    }

    public void setActiveDuration(String activeDuration) {
      this.activeDuration = activeDuration;
    }

    public String getSubject() {
      return subject;
    }

    public void setSubject(String subject) {
      this.subject = subject;
    }
  }

  static class CommandAttributes {
    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";
    private static final String ACTIVE_DURATION = "activeDuration";
    private static final String SUBJECT = "subject";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
