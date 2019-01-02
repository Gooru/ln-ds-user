package org.gooru.ds.user.processor.userperf.competency.assessments;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserPerfCompetencyAssessmentsCommand {


  private String competencyId;
  private String user;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserPerfCompetencyAssessmentsCommand.class);

  public String getcompetencyId() {
    return competencyId;
  }

  public String getUserId() {
    return user;
  }

  static UserPerfCompetencyAssessmentsCommand builder(JsonObject requestBody) {
    UserPerfCompetencyAssessmentsCommand result =
        UserPerfCompetencyAssessmentsCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserPerfCompetencyAssessmentsCommandBean asBean() {
    UserPerfCompetencyAssessmentsCommandBean bean = new UserPerfCompetencyAssessmentsCommandBean();
    bean.user = user;
    bean.competencyId = competencyId;

    return bean;
  }

  private static UserPerfCompetencyAssessmentsCommand buildFromJsonObject(JsonObject requestBody) {
    UserPerfCompetencyAssessmentsCommand result = new UserPerfCompetencyAssessmentsCommand();

    result.competencyId = requestBody.getString(CommandAttributes.COMPETENCY_ID);
    result.user = requestBody.getString(CommandAttributes.USER_ID);
    return result;
  }

  private void validate() {

    if (user == null) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided for request");
    }

    if (competencyId == null) {
      LOGGER.info("Competency not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Competency not provided");
    }

  }

  public static class UserPerfCompetencyAssessmentsCommandBean {
    private String competencyId;
    private String user;

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public String getCompetencyId() {
      return competencyId;
    }

    public void setCompetencyId(String competencyId) {
      this.competencyId = competencyId;
    }

  }

  static class CommandAttributes {
    private static final String COMPETENCY_ID = "competencyId";
    private static final String USER_ID = "user";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }


}
