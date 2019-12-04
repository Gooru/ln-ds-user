
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.gooru.ds.user.app.components.utilities.CommonUtils;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 27-Sep-2019
 */
public class StrugglingCompetenciesCommand {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(StrugglingCompetenciesCommand.class);

  private Set<Long> grades;
  private String classId;
  private Integer month;
  private Integer year;

  public Set<Long> getGrades() {
    return grades;
  }

  public String getClassId() {
    return classId;
  }

  public Integer getMonth() {
    return month;
  }

  public Integer getYear() {
    return year;
  }

  static StrugglingCompetenciesCommand build(JsonObject requestBody) {
    StrugglingCompetenciesCommand command = buildFromJson(requestBody);
    command.validate();
    return command;
  }

  private static StrugglingCompetenciesCommand buildFromJson(JsonObject requestBody) {
    StrugglingCompetenciesCommand command = new StrugglingCompetenciesCommand();
    String strGrades = requestBody.getString(CommandAttributes.GRADES);
    
    try {
      if (strGrades != null && !strGrades.isEmpty()) {
        String[] gradeArray = strGrades.split(",");
        Set<String> setOfString = new HashSet<>(Arrays.asList(gradeArray));
        command.grades =
            setOfString.stream().map(s -> Long.parseLong(s)).collect(Collectors.toSet());
      }
    } catch (NumberFormatException nfe) {
      LOGGER.warn("Invalid input format of the grades ");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid format of the grades");
    }
    
    command.classId = requestBody.getString(CommandAttributes.CLASS);
    command.month = getAsInt(requestBody, CommandAttributes.MONTH);
    command.year = getAsInt(requestBody, CommandAttributes.YEAR);
    return command;
  }

  private void validate() {
    validateClassId();
    validateMonthYearParams();
  }
  
  private void validateClassId() {
    try {
      UUID.fromString(classId);
    } catch (IllegalArgumentException iae) {
      LOGGER.warn("Invalid class id ");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid classId");
    }
  }
  
  private void validateMonthYearParams() {
    if (month == null || year == null) {
      LOGGER.debug("invalid month/year, using current");
      month = CommonUtils.currentMonth();
      year = CommonUtils.currentYear();
    }
  }

  public StrugglingCompetenciesCommandBean asBean() {
    StrugglingCompetenciesCommandBean bean = new StrugglingCompetenciesCommandBean();
    bean.grades = grades;
    bean.classId = classId;
    bean.month = month;
    bean.year = year;
    
    LocalDate localDate = LocalDate.of(bean.year, bean.month, 1);
    LocalDate boundary = localDate.plusMonths(1);
    LocalDateTime ts = LocalDateTime.of(boundary, LocalTime.of(0, 0));
    bean.toDate = Timestamp.valueOf(ts);
    LOGGER.debug("setting toDate: {}", bean.toDate.toString());
    return bean;
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
  
  @Override
  public String toString() {
    return "StrugglingCompetenciesCommand [grades=" + grades + ", classId=" + classId + ", month="
        + month + ", year=" + year + "]";
  }

  public static class StrugglingCompetenciesCommandBean {
    private Set<Long> grades;
    private String classId;
    private Integer month;
    private Integer year;
    private Timestamp toDate;

    public Set<Long> getGrades() {
      return grades;
    }

    public void setGrades(Set<Long> grades) {
      this.grades = grades;
    }

    public String getClassId() {
      return classId;
    }

    public void setClassId(String classId) {
      this.classId = classId;
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

    public Timestamp getToDate() {
      return toDate;
    }

    public void setToDate(Timestamp toDate) {
      this.toDate = toDate;
    }
  }

  static class CommandAttributes {
    private static final String GRADES = "grades";
    private static final String CLASS = "class";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }
}
