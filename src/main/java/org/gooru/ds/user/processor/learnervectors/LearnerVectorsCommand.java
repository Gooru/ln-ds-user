package org.gooru.ds.user.processor.learnervectors;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.gooru.ds.user.app.components.utilities.CommonUtils;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


class LearnerVectorsCommand {
  private static final Logger LOGGER = LoggerFactory.getLogger(LearnerVectorsCommand.class);
  private String user;
  private String subject;
  private String domain;
  private Integer month;
  private Integer year;
  private String compCode;

  public String getUser() {
    return user;
  }

  public LearnerVectorsCommandBean asBean() {
    LearnerVectorsCommandBean bean = new LearnerVectorsCommandBean();
    bean.user = user;
    bean.subject = subject;
    bean.domain = domain;
    bean.month = month;
    bean.year = year;
    bean.compCode = compCode;

    LocalDate localDate = LocalDate.of(year, month, 1);
    LocalDate boundary = localDate.plusMonths(1);
    LocalDateTime ts = LocalDateTime.of(boundary, LocalTime.of(0, 0));

    bean.toDate = Timestamp.valueOf(ts);
    LOGGER.debug("setting toDate: {}", bean.toDate.toString());
    return bean;
  }

  static LearnerVectorsCommand builder(JsonObject requestBody) {
    LearnerVectorsCommand result = LearnerVectorsCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  private static LearnerVectorsCommand buildFromJsonObject(JsonObject requestBody) {
    LearnerVectorsCommand command = new LearnerVectorsCommand();
    command.user = requestBody.getString(CommandAttributes.USER);
    command.subject = requestBody.getString(CommandAttributes.TX_SUBJECT_CODE);
    command.domain = requestBody.getString(CommandAttributes.TX_DOMAIN_CODE);
    command.compCode = requestBody.getString(CommandAttributes.TX_COMP_CODE);
    String strMonth = requestBody.getString(CommandAttributes.MONTH, null);
    command.month = strMonth != null ? Integer.parseInt(strMonth) : null;
    String strYear = requestBody.getString(CommandAttributes.YEAR, null);
    command.year = strYear != null ? Integer.parseInt(strYear) : null;
    return command;
  }

  private void validate() {
    if (user == null) {
      LOGGER.info("User not provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided for request");
    }
    if (month == null || (month < 1 || month > 12)) {
      month = CommonUtils.currentMonth();
    }

    int currentYear = CommonUtils.currentYear();
    if (year == null || year > currentYear) {
      year = currentYear;
      month = CommonUtils.currentMonth();
    }
  }

  public static class LearnerVectorsCommandBean {
    private String user;
    private String subject;
    private String domain;
    private String compCode;
    private Integer month;
    private Integer year;
    private Timestamp toDate;

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

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public String getSubject() {
      return subject;
    }

    public void setSubject(String subject) {
      this.subject = subject;
    }

    public String getDomain() {
      return domain;
    }

    public void setDomain(String domain) {
      this.domain = domain;
    }

    public String getCompCode() {
      return compCode;
    }

    public void setCompCode(String compCode) {
      this.compCode = compCode;
    }
  }

  static class CommandAttributes {
    private static final String USER = "user";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final String TX_SUBJECT_CODE = "tx_subject_code";
    private static final String TX_DOMAIN_CODE = "tx_domain_code";
    private static final String TX_COMP_CODE = "tx_comp_code";


    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
