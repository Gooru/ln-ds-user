package org.gooru.ds.user.processor.user.course.competency.report;

import java.util.Arrays;
import java.util.List;
import org.gooru.ds.user.app.components.utilities.CommonUtils;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru on 17-Jul-2018
 */
public class UserCourseCompetencyReportCommand {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserCourseCompetencyReportCommand.class);

  public static final String FILTER_CUMULATIVE = "cumulative";
  public static final String FILTER_WINDOW = "window";
  private List<String> VALID_FILTERS = Arrays.asList(FILTER_CUMULATIVE, FILTER_WINDOW);

  private String classId;
  private String courseId;
  private String studentId;
  private String filter;
  private Integer fromMonth;
  private Integer fromYear;
  private Integer toMonth;
  private Integer toYear;

  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public String getCourseId() {
    return courseId;
  }

  public String getStudentId() {
    return studentId;
  }

  public String getFilter() {
    return filter;
  }

  public Integer getFromMonth() {
    return fromMonth;
  }

  public Integer getFromYear() {
    return fromYear;
  }

  public Integer getToMonth() {
    return toMonth;
  }

  public Integer getToYear() {
    return toYear;
  }

  public UserCourseCompetencyReportCommandBean asBean() {
    UserCourseCompetencyReportCommandBean bean = new UserCourseCompetencyReportCommandBean();
    bean.classId = classId;
    bean.courseId = courseId;
    bean.studentId = studentId;
    bean.filter = filter;
    bean.fromMonth = fromMonth;
    bean.fromYear = fromYear;
    bean.toMonth = toMonth;
    bean.toYear = toYear;

    return bean;
  }

  static UserCourseCompetencyReportCommand build(JsonObject requestBody) {
    UserCourseCompetencyReportCommand command =
        UserCourseCompetencyReportCommand.buildFromJsonObject(requestBody);
    command.validate();

    return command;
  }

  private static UserCourseCompetencyReportCommand buildFromJsonObject(JsonObject requestBody) {
    UserCourseCompetencyReportCommand command = new UserCourseCompetencyReportCommand();
    command.classId = requestBody.getString(CommandAttributes.CLASS_ID);
    command.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
    command.studentId = requestBody.getString(CommandAttributes.STUDENT_ID, null);
    command.filter = requestBody.getString(CommandAttributes.FILTER);

    command.fromMonth = getAsInt(requestBody, CommandAttributes.FROM_MONTH);
    command.fromYear = getAsInt(requestBody, CommandAttributes.FROM_YEAR);
    command.toMonth = getAsInt(requestBody, CommandAttributes.TO_MONTH);
    command.toYear = getAsInt(requestBody, CommandAttributes.TO_YEAR);

    return command;
  }

  private void validate() {
    if (classId == null || classId.isEmpty()) {
      LOGGER.debug("invalid class id provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid classId");
    }

    if (courseId == null || courseId.isEmpty()) {
      LOGGER.debug("invalid course id provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid course id");
    }

    if (filter == null || filter.isEmpty() || !VALID_FILTERS.contains(filter)) {
      filter = FILTER_CUMULATIVE;
    }

    if (filter.equalsIgnoreCase(FILTER_CUMULATIVE)) {
      validateToParams();
    } else if (filter.equalsIgnoreCase(FILTER_WINDOW)) {
      validateFromParams();
      validateToParams();
    }
  }

  private void validateToParams() {
    if (toMonth == null || toYear == null) {
      LOGGER.debug("invalid toMonth/toYear, using current");
      toMonth = CommonUtils.currentMonth();
      toYear = CommonUtils.currentYear();
    }
  }

  private void validateFromParams() {
    if (fromMonth == null || fromYear == null) {
      LOGGER.debug("invalid fromMonth/fromYear provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid fromMonth/fromYear");
    }

    if (fromMonth < 1 || fromMonth > 12) {
      LOGGER.debug("fromMonth not in range of 1 to 12");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "fromMonth not in range of 1 to 12");
    }

    int currentYear = CommonUtils.currentYear();
    if (fromYear > currentYear) {
      LOGGER.debug("fromYear could not be future year");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "fromYear could not be future year");
    }
  }

  private static Integer getAsInt(JsonObject requestBody, String key) {
    String value = requestBody.getString(key);
    if (value == null || value.isEmpty()) {
      return null;
    }

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

  public static class UserCourseCompetencyReportCommandBean {
    private String classId;
    private String courseId;
    private String studentId;
    private String filter;
    private Integer fromMonth;
    private Integer fromYear;
    private Integer toMonth;
    private Integer toYear;
    private String subject;

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

    public String getStudentId() {
      return studentId;
    }

    public void setStudentId(String studentId) {
      this.studentId = studentId;
    }

    public String getFilter() {
      return filter;
    }

    public void setFilter(String filter) {
      this.filter = filter;
    }

    public Integer getFromMonth() {
      return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
      this.fromMonth = fromMonth;
    }

    public Integer getFromYear() {
      return fromYear;
    }

    public void setFromYear(Integer fromYear) {
      this.fromYear = fromYear;
    }

    public Integer getToMonth() {
      return toMonth;
    }

    public void setToMonth(Integer toMonth) {
      this.toMonth = toMonth;
    }

    public Integer getToYear() {
      return toYear;
    }

    public void setToYear(Integer toYear) {
      this.toYear = toYear;
    }

    public String getSubject() {
      return subject;
    }

    public void setSubject(String subject) {
      this.subject = subject;
    }
  }

  static class CommandAttributes {
    private static final String CLASS_ID = "classId";
    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";
    private static final String FILTER = "filter";
    private static final String FROM_MONTH = "fromMonth";
    private static final String FROM_YEAR = "fromYear";
    private static final String TO_MONTH = "toMonth";
    private static final String TO_YEAR = "toYear";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }
}
