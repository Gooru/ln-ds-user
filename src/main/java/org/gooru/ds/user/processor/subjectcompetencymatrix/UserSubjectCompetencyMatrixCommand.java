package org.gooru.ds.user.processor.subjectcompetencymatrix;

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


class UserSubjectCompetencyMatrixCommand {
  private String user;
  private Integer month;
  private Integer year;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserSubjectCompetencyMatrixCommand.class);


  static UserSubjectCompetencyMatrixCommand builder(JsonObject requestBody) {
    UserSubjectCompetencyMatrixCommand result = buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserSubjectCompetencyMatrixCommandBean asBean() {
    UserSubjectCompetencyMatrixCommandBean bean = new UserSubjectCompetencyMatrixCommandBean();
    bean.user = user;
    bean.month = month;
    bean.year = year;

    LocalDate localDate = LocalDate.of(year, month, 1);
    LocalDate boundary = localDate.plusMonths(1);
    LocalDateTime ts = LocalDateTime.of(boundary, LocalTime.of(0, 0));

    bean.toDate = Timestamp.valueOf(ts);
    LOGGER.debug("setting toDate: {}", bean.toDate.toString());
    return bean;
  }

  private void validate() {
    if (user == null) {
      LOGGER.debug("Provided null user");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid user");
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

  private static UserSubjectCompetencyMatrixCommand buildFromJsonObject(JsonObject requestBody) {
    UserSubjectCompetencyMatrixCommand command = new UserSubjectCompetencyMatrixCommand();
    command.user = requestBody.getString(CommandAttributes.USER);
    String strMonth = requestBody.getString(CommandAttributes.MONTH, null);
    command.month = strMonth != null ? Integer.parseInt(strMonth) : null;

    String strYear = requestBody.getString(CommandAttributes.YEAR, null);
    command.year = strYear != null ? Integer.parseInt(strYear) : null;
    return command;
  }


  public static class UserSubjectCompetencyMatrixCommandBean {
    private String user;
    private Integer month;
    private Integer year;
    private Timestamp toDate;


    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
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
    private static final String USER = "user";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }
}
