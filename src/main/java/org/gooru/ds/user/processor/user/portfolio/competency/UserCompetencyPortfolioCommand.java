package org.gooru.ds.user.processor.user.portfolio.competency;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserCompetencyPortfolioCommand {
  
  private String competencyCode;
  private String gutCode;
  private String user;
  private Integer status;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserCompetencyPortfolioCommand.class);

  public String getcompetencyCode() {
    return competencyCode;
  }

  public String getGutCode() {
    return gutCode;
  }

  public String getUserId() {
    return user;
  }

  public Integer getStatus() {
    return status;
  }

  static UserCompetencyPortfolioCommand builder(JsonObject requestBody) {
    UserCompetencyPortfolioCommand result =
        UserCompetencyPortfolioCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserCompetencyPortfolioCommandBean asBean() {
    UserCompetencyPortfolioCommandBean bean = new UserCompetencyPortfolioCommandBean();
    bean.user = user;
    bean.competencyCode = competencyCode;
    bean.gutCode = gutCode;
    bean.status = status;
    return bean;
  }

  private static UserCompetencyPortfolioCommand buildFromJsonObject(JsonObject requestBody) {
    UserCompetencyPortfolioCommand result = new UserCompetencyPortfolioCommand();

    result.competencyCode = requestBody.getString(CommandAttributes.COMPETENCY_CODE);
    result.gutCode = requestBody.getString(CommandAttributes.GUT_CODE);
    result.user = requestBody.getString(CommandAttributes.USER_ID);

    String strStatus = requestBody.getString(CommandAttributes.STATUS);
    try {
      result.status = strStatus != null ? Integer.parseInt(strStatus) : null;
    } catch (NumberFormatException nfe) {
      LOGGER.info("status is not valid integer");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Status should be valid integer between 1 to 5");
    }

    return result;
  }

  private void validate() {

    if (user == null) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided for request");
    }

    if (competencyCode == null && gutCode == null) {
      LOGGER.info("Competency and Gut code not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Either Competency or gut code should be provided");
    }

    if (status != null && (status < 1 || status > 5)) {
      LOGGER.info("status is not provided correctly");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Status should be between 1 to 5");
    }
  }

  public static class UserCompetencyPortfolioCommandBean {
    private String competencyCode;
    private String gutCode;
    private String user;
    private Integer status;

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public String getCompetencyCode() {
      return competencyCode;
    }

    public void setCompetencyCode(String competencyCode) {
      this.competencyCode = competencyCode;
    }

    public String getGutCode() {
      return gutCode;
    }

    public void setGutCode(String gutCode) {
      this.gutCode = gutCode;
    }

    public Integer getStatus() {
      return status;
    }

    public void setStatus(Integer status) {
      this.status = status;
    }

  }

  static class CommandAttributes {
    private static final String COMPETENCY_CODE = "competencyCode";
    private static final String GUT_CODE = "gutCode";
    private static final String USER_ID = "user";
    private static final String STATUS = "status";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }


}
