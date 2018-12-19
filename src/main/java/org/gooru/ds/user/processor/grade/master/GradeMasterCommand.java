package org.gooru.ds.user.processor.grade.master;

import io.vertx.core.json.JsonObject;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author szgooru on 21-Sep-2018
 */
public class GradeMasterCommand {

  private String subject;
  private String fwCode;
  private static final String DEFAULT_FW = "GUT";

  private static final Logger LOGGER = LoggerFactory.getLogger(GradeMasterCommand.class);

  static GradeMasterCommand builder(JsonObject requestBody) {
    GradeMasterCommand command = buildFromJson(requestBody);
    command.validate();
    return command;
  }

  private void validate() {
    if (ValidatorUtils.isNullOrEmpty(subject)) {
      LOGGER.debug("invalid subject sent in request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid subject");
    }

    LOGGER.info("SUBJECT:={}", subject);
  }

  private static GradeMasterCommand buildFromJson(JsonObject requestBody) {
    GradeMasterCommand command = new GradeMasterCommand();
    command.subject = requestBody.getString(CommandAttributes.SUBJECT);
    command.fwCode = requestBody.getString(CommandAttributes.FW_CODE);
    if (command.fwCode == null) {
      command.fwCode = DEFAULT_FW;
    }
    return command;
  }

  public GradeMasterCommandBean asBean() {
    GradeMasterCommandBean bean = new GradeMasterCommandBean();
    bean.subject = subject;
    bean.fwCode = fwCode;
    return bean;
  }

  public static class GradeMasterCommandBean {

    private String subject;
    private String fwCode;

    public String getFwCode() {
      return fwCode;
    }

    public void setFwCode(String fwCode) {
      this.fwCode = fwCode;
    }

    public String getSubject() {
      return subject;
    }

    public void setSubject(String subject) {
      this.subject = subject;
    }
  }

  static class CommandAttributes {

    private static final String SUBJECT = "subject";
    private static final String FW_CODE = "fw_code";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
