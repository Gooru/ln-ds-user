
package org.gooru.ds.user.processor.domain.competency.perf.report;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.domain.report.utils.DomainReportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class DomainCompetencyPerfReportCommand {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(DomainCompetencyPerfReportCommand.class);
  
  private String classId;
  private String domain;
  private String txCode;
  private String agent;
  private Integer month;
  private Integer year;

  public String getClassId() {
    return classId;
  }

  public String getDomain() {
    return domain;
  }

  public String getTxCode() {
    return txCode;
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

  public static DomainCompetencyPerfReportCommand build(JsonObject request) {
    DomainCompetencyPerfReportCommand command = buildFromJson(request);
    command.validate();
    return command;
  }

  public static DomainCompetencyPerfReportCommand buildFromJson(JsonObject request) {
    DomainCompetencyPerfReportCommand command = new DomainCompetencyPerfReportCommand();
    command.classId = request.getString(CommandAttributes.CLASS_ID, null);
    command.agent = request.getString(CommandAttributes.AGENT, Constants.Params.AGENT_DEFAULT);
    command.domain = request.getString(CommandAttributes.DOMAIN, null);
    command.txCode = request.getString(CommandAttributes.TX_CODE, null);
    command.month = DomainReportUtils.getAsInt(request, CommandAttributes.MONTH);
    command.year = DomainReportUtils.getAsInt(request, CommandAttributes.YEAR);
    return command;
  }

  public void validate() {
    if (classId == null || classId.isEmpty()) {
      LOGGER.warn("invalid class id provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid classId");
    }

    if (domain == null || domain.isEmpty()) {
      LOGGER.warn("invalid domain provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid domain");
    }
    
    if (txCode == null || txCode.isEmpty()) {
      LOGGER.warn("invalid taxonomy code provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid taxonomy code");
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

  public DomainCompetencyPerfReportCommandBean asBean() {
    DomainCompetencyPerfReportCommandBean bean = new DomainCompetencyPerfReportCommandBean();
    bean.classId = classId;
    bean.agent = agent;
    bean.domain = domain;
    bean.txCode = txCode;
    bean.month = month;
    bean.year = year;

    LocalDate localDate = LocalDate.of(bean.year, bean.month, 1);
    LocalDate boundary = localDate.plusMonths(1);
    LocalDateTime ts = LocalDateTime.of(boundary, LocalTime.of(0, 0));
    bean.toDate = Timestamp.valueOf(ts);
    return bean;
  }

  public static class DomainCompetencyPerfReportCommandBean {
    private String classId;
    private String domain;
    private String txCode;
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

    public String getDomain() {
      return domain;
    }

    public void setDomain(String domain) {
      this.domain = domain;
    }

    public String getTxCode() {
      return txCode;
    }

    public void setTxCode(String txCode) {
      this.txCode = txCode;
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
    
  }
  
  static class CommandAttributes {
    private CommandAttributes() {
      throw new AssertionError();
    }

    private static final String CLASS_ID = "classId";
    private static final String DOMAIN = "domain";
    private static final String AGENT = "agent";
    private static final String TX_CODE = "tx_code";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
  }

}
