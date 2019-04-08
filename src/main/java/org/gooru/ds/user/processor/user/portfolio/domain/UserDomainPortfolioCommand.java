package org.gooru.ds.user.processor.user.portfolio.domain;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserDomainPortfolioCommand {

  private String subjectCode;
  private String domainCode;
  private String user;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserDomainPortfolioCommand.class);

  public String getSubjectCode() {
    return subjectCode;
  }
  
  public String getdomainCode() {
    return domainCode;
  }

  public String getUserId() {
    return user;
  }

  static UserDomainPortfolioCommand builder(JsonObject requestBody) {
    UserDomainPortfolioCommand result =
        UserDomainPortfolioCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserDomainPortfolioCommandBean asBean() {
    UserDomainPortfolioCommandBean bean = new UserDomainPortfolioCommandBean();
    bean.user = user;
    bean.domainCode = domainCode;
    bean.subjectCode = subjectCode;
    return bean;
  }

  private static UserDomainPortfolioCommand buildFromJsonObject(JsonObject requestBody) {
    UserDomainPortfolioCommand result = new UserDomainPortfolioCommand();

    result.subjectCode = requestBody.getString(CommandAttributes.SUBJECT_CODE);
    result.domainCode = requestBody.getString(CommandAttributes.DOMAIN_CODE);
    result.user = requestBody.getString(CommandAttributes.USER_ID);

    return result;
  }

  private void validate() {

    if (user == null) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided in the request");
    }

    if (domainCode == null) {
      LOGGER.info("Domain Code not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Domain Code not provided in the request");
    }
    
    if (subjectCode == null) {
      LOGGER.info("Subject Code not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Subject Code not provided in the request");
    }

  }

  public static class UserDomainPortfolioCommandBean {
    
    private String subjectCode;
    private String domainCode;
    private String user;
    
    public String getSubjectCode() {
      return subjectCode;
    }
    public String getDomainCode() {
      return domainCode;
    }
    public void setDomainCode(String domainCode) {
      this.domainCode = domainCode;
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
    private static final String DOMAIN_CODE = "domainCode";
    private static final String USER_ID = "user";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }


}
