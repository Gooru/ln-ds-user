
package org.gooru.ds.user.processor.domain.report;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 14-Jan-2019
 */
public class DomainReportCommand {

  private String classId;
  private String agent;
  private Integer month;
  private Integer year;

  private static final Logger LOGGER = LoggerFactory.getLogger(DomainReportCommand.class);

  public String getClassId() {
    return classId;
  }

  public String getAgent() {
    return agent;
  }

  public Integer getMonth() {
    return month;
  }

  public Integer getYear() {
    return year;
  }

  public static DomainReportCommand build(JsonObject request) {
    DomainReportCommand command = buildFromJson(request);
    command.validate();
    return command;
  }

  private static DomainReportCommand buildFromJson(JsonObject request) {
    DomainReportCommand command = new DomainReportCommand();
    command.classId = request.getString(CommandAttributes.CLASS_ID, null);
    command.agent = request.getString(CommandAttributes.AGENT, Constants.Params.AGENT_DEFAULT);
    command.month = getAsInt(request, CommandAttributes.MONTH);
    command.year =  getAsInt(request, CommandAttributes.YEAR);
    LOGGER.debug(command.toString());
    return command;
  }

  private void validate() {
    if (classId == null || classId.isEmpty()) {
      LOGGER.warn("invalid class id provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid classId");
    }

    if (!Constants.Params.SUPPORTED_AGENTS.contains(agent)) {
      LOGGER.warn("invalid agent provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid agent");
    }

    LocalDate now = LocalDate.now();
    if (month == null || year == null) {
      LOGGER.warn("invalid month/year, using current");
      month = now.getMonthValue();
      year = now.getYear();
    }

    if (month < 1 || month > 12) {
      LOGGER.debug("month not in range of 1 to 12");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "month not in range of 1 to 12");
    }

    int currentYear = now.getYear();
    if (year > currentYear) {
      LOGGER.debug("year could not be future year");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "year could not be future year");
    }
  }

  public DomainReportCommandBean asBean() {
    DomainReportCommandBean bean = new DomainReportCommandBean();
    bean.classId = classId;
    bean.agent = agent;
    bean.month = month;
    bean.year = year;

    LOGGER.debug("bean: {}", bean.toString());
    LocalDate localDate = LocalDate.of(bean.year, bean.month, 1);
    LocalDate boundary = localDate.plusMonths(1);
    LocalDateTime ts = LocalDateTime.of(boundary, LocalTime.of(0, 0));
    bean.toDate = Timestamp.valueOf(ts);
    LOGGER.debug("setting toDate: {}", bean.toDate.toString());
    return bean;
  }
  
  @Override
  public String toString() {
    return "DomainReportCommand [classId=" + classId + ", agent=" + agent + ", month=" + month
        + ", year=" + year + "]";
  }

  public static class DomainReportCommandBean {
    private String classId;
    private String agent;
    private Integer month;
    private Integer year;
    private Timestamp toDate;

    public String getClassId() {
      return classId;
    }

    public void setClassId(String classId) {
      this.classId = classId;
    }

    public String getAgent() {
      return agent;
    }

    public void setAgent(String agent) {
      this.agent = agent;
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

    @Override
    public String toString() {
      return "DomainReportCommandBean [classId=" + classId + ", agent=" + agent + ", month=" + month
          + ", year=" + year + ", toDate=" + toDate + "]";
    }
  }

  static class CommandAttributes {
    private CommandAttributes() {
      throw new AssertionError();
    }

    private static final String CLASS_ID = "classId";
    private static final String AGENT = "agent";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
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
}
