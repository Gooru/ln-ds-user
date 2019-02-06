package org.gooru.ds.user.processor.grade.competency;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class GradeCompetencyCommand {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(GradeCompetencyCommand.class);

  private Integer gradeId;
  private String subjectCode;

  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }


  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  static GradeCompetencyCommand builder(JsonObject requestBody) {
    GradeCompetencyCommand command = buildFromJsonObject(requestBody);
    command.subjectCode = requestBody.getString(CommandAttributes.SUBJECT_CODE);
    command.gradeId = requestBody.getString(CommandAttributes.GRADE_ID) != null
        ? Integer.valueOf(requestBody.getString(CommandAttributes.GRADE_ID))
        : null;
    command.validate();
    return command;
  }

  private void validate() {
    if (subjectCode == null) {
      LOGGER.debug("Invalid subjectCode");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid subjectCode");
    }
    if (gradeId == null || gradeId <= 0) {
      LOGGER.debug("Invalid gradeId");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid gradeId");
    }
  }

  private static GradeCompetencyCommand buildFromJsonObject(JsonObject requestBody) {
    GradeCompetencyCommand command = new GradeCompetencyCommand();
    return command;
  }

  static class CommandAttributes {
    private static final String SUBJECT_CODE = "subject";
    private static final String GRADE_ID = "gradeId";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }



}
