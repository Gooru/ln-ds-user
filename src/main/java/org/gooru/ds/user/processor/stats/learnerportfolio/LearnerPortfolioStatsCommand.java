package org.gooru.ds.user.processor.stats.learnerportfolio;

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


class LearnerPortfolioStatsCommand {
  private static final Logger LOGGER = LoggerFactory.getLogger(LearnerPortfolioStatsCommand.class);
  private String user;
  private String subject;
  private Integer month;
  private Integer year;

  public String getUser() {
    return user;
  }

  public LearnerPortfolioStatsCommandBean asBean() {
    LearnerPortfolioStatsCommandBean bean = new LearnerPortfolioStatsCommandBean();
    bean.user = user;
    bean.subject = subject;
    bean.month = month;
    bean.year = year;

    LocalDate localDate = LocalDate.of(year, month, 1);
    LocalDate boundary = localDate.plusMonths(1);
    LocalDateTime ts = LocalDateTime.of(boundary, LocalTime.of(0, 0));

    bean.toDate = Timestamp.valueOf(ts);
    LOGGER.debug("setting toDate: {}", bean.toDate.toString());
    return bean;
  }

  static LearnerPortfolioStatsCommand builder(JsonObject requestBody) {
    LearnerPortfolioStatsCommand result =
        LearnerPortfolioStatsCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  private static LearnerPortfolioStatsCommand buildFromJsonObject(JsonObject requestBody) {
    LearnerPortfolioStatsCommand command = new LearnerPortfolioStatsCommand();
    command.user = requestBody.getString(CommandAttributes.USER);
    command.subject = requestBody.getString(CommandAttributes.TX_SUBJECT_CODE);
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

  public static class LearnerPortfolioStatsCommandBean {
    private String user;
    private String subject;
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

  }

  static class CommandAttributes {
    private static final String USER = "user";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final String TX_SUBJECT_CODE = "tx_subject_code";


    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
