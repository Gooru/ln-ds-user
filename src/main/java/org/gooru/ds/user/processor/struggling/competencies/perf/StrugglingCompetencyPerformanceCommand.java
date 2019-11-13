
package org.gooru.ds.user.processor.struggling.competencies.perf;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 11-Nov-2019
 */
public class StrugglingCompetencyPerformanceCommand {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(StrugglingCompetencyPerformanceCommand.class);

  private String classId;
  private String competency;
  private Integer month;
  private Integer year;

  public String getClassId() {
    return classId;
  }

  public String getCompetency() {
    return competency;
  }

  public Integer getMonth() {
    return month;
  }

  public Integer getYear() {
    return year;
  }


  public static StrugglingCompetencyPerformanceCommand build(JsonObject requestBody) {
    StrugglingCompetencyPerformanceCommand command = buildFromJson(requestBody);
    command.validate();
    return command;
  }

  private static StrugglingCompetencyPerformanceCommand buildFromJson(JsonObject requestBody) {
    StrugglingCompetencyPerformanceCommand command = new StrugglingCompetencyPerformanceCommand();
    command.classId = requestBody.getString(CommandAttributes.CLASS);
    command.competency = requestBody.getString(CommandAttributes.COMPETENCY);
    command.month = getAsInt(requestBody, CommandAttributes.MONTH);
    command.year = getAsInt(requestBody, CommandAttributes.YEAR);
    return command;
  }

  public StrugglingCompetencyPerformanceCommandBean asBean() {
    StrugglingCompetencyPerformanceCommandBean bean =
        new StrugglingCompetencyPerformanceCommandBean();
    bean.classId = classId;
    bean.competency = competency;
    bean.month = month;
    bean.year = year;

    LocalDate localDate = LocalDate.of(bean.year, bean.month, 1);
    LocalDate boundary = localDate.plusMonths(1);
    LocalDateTime ts = LocalDateTime.of(boundary, LocalTime.of(0, 0));
    bean.toDate = Timestamp.valueOf(ts);
    return bean;
  }

  private void validate() {
    validateClassId();

    if (competency == null || competency.isEmpty()) {
      LOGGER.warn("Invalid competency ");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid competency");
    }
  }

  private void validateClassId() {
    try {
      UUID.fromString(classId);
    } catch (IllegalArgumentException iae) {
      LOGGER.warn("Invalid class id ");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid classId");
    }
  }

  public static class StrugglingCompetencyPerformanceCommandBean {
    private String classId;
    private String competency;
    private Integer month;
    private Integer year;
    private Timestamp toDate;

    public String getClassId() {
      return classId;
    }

    public void setClassId(String classId) {
      this.classId = classId;
    }

    public String getCompetency() {
      return competency;
    }

    public void setCompetency(String competency) {
      this.competency = competency;
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

  static class CommandAttributes {
    private static final String CLASS = "class";
    private static final String COMPETENCY = "competency";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }
}
