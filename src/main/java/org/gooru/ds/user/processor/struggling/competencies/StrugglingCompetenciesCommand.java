
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.gooru.ds.user.app.components.utilities.CommonUtils;
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
  private Set<String> students;
  private Integer month;
  private Integer year;

  public Set<Long> getGrades() {
    return grades;
  }

  public Set<String> getStudents() {
    return students;
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
    if (strGrades != null && !strGrades.isEmpty()) {
      String[] gradeArray = strGrades.split(",");
      Set<String> setOfString = new HashSet<>(Arrays.asList(gradeArray));
      command.grades =
          setOfString.stream().map(s -> Long.parseLong(s)).collect(Collectors.toSet());
    }
    
    String strStudents = requestBody.getString(CommandAttributes.STUDENTS);
    if (strStudents != null && !strStudents.isEmpty()) {
      String[] studentArray = strStudents.split(",");
      command.students = new HashSet<>(Arrays.asList(studentArray));
    }
    
    command.month = getAsInt(requestBody, CommandAttributes.MONTH);
    command.year = getAsInt(requestBody, CommandAttributes.YEAR);
    return command;
  }

  private void validate() {
    
    validateMonthYearParams();
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
    bean.students = students;
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
    return "StrugglingCompetenciesCommand [grades=" + grades + ", students=" + students + ", month="
        + month + ", year=" + year + "]";
  }

  public static class StrugglingCompetenciesCommandBean {
    private Set<Long> grades;
    private Set<String> students;
    private Integer month;
    private Integer year;
    private Timestamp toDate;

    public Set<Long> getGrades() {
      return grades;
    }

    public void setGrades(Set<Long> grades) {
      this.grades = grades;
    }

    public Set<String> getStudents() {
      return students;
    }

    public void setStudents(Set<String> students) {
      this.students = students;
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
    private static final String STUDENTS = "students";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }
}
