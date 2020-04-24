package org.gooru.ds.user.processor.competency.subjects;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 * 
 */
public class CompetencySubjectListCommand {

  private String classificationType;
  private String tenantId;

  private static final Logger LOGGER = LoggerFactory.getLogger(CompetencySubjectListCommand.class);

  public String getClassificationType() {
    return classificationType;
  }

  public String getTenantId() {
    return tenantId;
  }


  static CompetencySubjectListCommand builder(JsonObject requestBody, String tenantId) {
    CompetencySubjectListCommand result =
        CompetencySubjectListCommand.buildFromJsonObject(requestBody, tenantId);
    result.validate();
    return result;
  }

  public CompetencySubjectListCommandBean asBean() {
    CompetencySubjectListCommandBean bean = new CompetencySubjectListCommandBean();
    bean.classificationType = classificationType;

    return bean;
  }

  private static CompetencySubjectListCommand buildFromJsonObject(JsonObject requestBody,
      String tenantId) {
    CompetencySubjectListCommand result = new CompetencySubjectListCommand();
    result.classificationType = requestBody.getString(CommandAttributes.CLASSIFICATION_TYPE);
    result.tenantId = tenantId;
    return result;
  }

  private void validate() {

    if (classificationType == null) {
      LOGGER.info("Classification Type not provided with the request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid classficationType");
    }
  }


  public static class CompetencySubjectListCommandBean {
    private String classificationType;

    public String getClassificationType() {
      return classificationType;
    }

    public void setClassificationType(String classificationType) {
      this.classificationType = classificationType;
    }

  }

  static class CommandAttributes {
    private static final String CLASSIFICATION_TYPE = "classificationType";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }


}
