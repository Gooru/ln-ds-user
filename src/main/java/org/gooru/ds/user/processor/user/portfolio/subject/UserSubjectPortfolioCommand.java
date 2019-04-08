package org.gooru.ds.user.processor.user.portfolio.subject;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserSubjectPortfolioCommand {

  private String subjectCode;
  private String user;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserSubjectPortfolioCommand.class);

  public String getSubjectCode() {
    return subjectCode;
  }
  
  public String getUserId() {
    return user;
  }

  static UserSubjectPortfolioCommand builder(JsonObject requestBody) {
    UserSubjectPortfolioCommand result =
        UserSubjectPortfolioCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserSubjectPortfolioCommandBean asBean() {
    UserSubjectPortfolioCommandBean bean = new UserSubjectPortfolioCommandBean();
    bean.user = user;
    bean.subjectCode = subjectCode;
    return bean;
  }

  private static UserSubjectPortfolioCommand buildFromJsonObject(JsonObject requestBody) {
    UserSubjectPortfolioCommand result = new UserSubjectPortfolioCommand();

    result.subjectCode = requestBody.getString(CommandAttributes.SUBJECT_CODE);
    result.user = requestBody.getString(CommandAttributes.USER_ID);

    return result;
  }

  private void validate() {

    if (user == null) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided in the request");
    }
    
    if (subjectCode == null) {
      LOGGER.info("Subject Code not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Subject Code not provided in the request");
    }

  }

  public static class UserSubjectPortfolioCommandBean {
    
    private String subjectCode;    
    private String user;
    
    public String getSubjectCode() {
      return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
      this.subjectCode = subjectCode;
    }

    public String getUser() {
      return user;
    }
    public void setUser(String user) {
      this.user = user;
    }

  }

  static class CommandAttributes {
    
    private static final String SUBJECT_CODE = "subjectCode";
    private static final String USER_ID = "user";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
