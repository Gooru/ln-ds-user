package org.gooru.ds.user.processor.atc.recompute;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class ATCCompetencyStatsCommand {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ATCCompetencyStatsCommand.class);

  private String subjectCode;
  private String classId;
  private String courseId;
  private Integer month;
  private Integer year;
  private Date statsDate;

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }
  
  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }
  
  public Date getStatsDate() {
    return statsDate;
  }

  public void setStatsDate(Date statsDate) {
    this.statsDate = statsDate;
  }

  static ATCCompetencyStatsCommand builder(JsonObject requestBody) {
    ATCCompetencyStatsCommand command = buildFromJsonObject(requestBody);
    command.classId = requestBody.getString(CommandAttributes.CLASS_ID);
    command.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
    command.subjectCode = requestBody.getString(CommandAttributes.SUBJECT_CODE);
    command.year = requestBody.getString(CommandAttributes.YEAR) != null
        ? Integer.valueOf(requestBody.getString(CommandAttributes.YEAR))
        : null;
    command.month = requestBody.getString(CommandAttributes.MONTH) != null
        ? Integer.valueOf(requestBody.getString(CommandAttributes.MONTH))
        : null;
    command.validate();
    
    if (command.month != null && command.year != null) {
    LocalDate localDate = LocalDate.of(command.year, command.month, 1);
    command.statsDate = Date.valueOf(localDate); 
    }

    return command;
  }

  private void validate() {
    if (classId == null) {
      LOGGER.debug("Invalid ClassId");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid classId");
    } else if (subjectCode == null) {
      LOGGER.debug("Invalid subjectCode");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid subjectCode");
    } else if (courseId == null) {
      LOGGER.debug("Invalid CourseId");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid courseId");
    }
  }

  private static ATCCompetencyStatsCommand buildFromJsonObject(JsonObject requestBody) {
    ATCCompetencyStatsCommand command = new ATCCompetencyStatsCommand();
    return command;
  }

  static class CommandAttributes {
    private static final String SUBJECT_CODE = "subjectCode";
    private static final String CLASS_ID = "classId";
    private static final String COURSE_ID = "courseId";
    private static final String YEAR = "year";
    private static final String MONTH = "month";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }



}
