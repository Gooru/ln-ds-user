package org.gooru.ds.user.processor.usercoursecompetencymatrix;

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

/**
 * @author ashish on 13/2/18.
 */
class UserCourseCompetencyMatrixCommand {
  private String subject;
  private String user;
  private Integer month;
  private Integer year;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserCourseCompetencyMatrixCommand.class);

  public String getSubject() {
    return subject;
  }

  public String getUser() {
    return user;
  }

  public Integer getMonth() {
    return month;
  }

  public Integer getYear() {
    return year;
  }

  static UserCourseCompetencyMatrixCommand builder(JsonObject requestBody) {
    UserCourseCompetencyMatrixCommand result = buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserCourseCompetencyMatrixCommandBean asBean() {
    UserCourseCompetencyMatrixCommandBean bean = new UserCourseCompetencyMatrixCommandBean();
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

  private void validate() {
    if (subject == null) {
      LOGGER.debug("Provided null subject");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid subject");
    } else if (user == null) {
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

  private static UserCourseCompetencyMatrixCommand buildFromJsonObject(JsonObject requestBody) {
    UserCourseCompetencyMatrixCommand command = new UserCourseCompetencyMatrixCommand();
    command.user = requestBody.getString(CommandAttributes.USER);
    command.subject = requestBody.getString(CommandAttributes.SUBJECT);

    String strMonth = requestBody.getString(CommandAttributes.MONTH, null);
    command.month = strMonth != null ? Integer.parseInt(strMonth) : null;

    String strYear = requestBody.getString(CommandAttributes.YEAR, null);
    command.year = strYear != null ? Integer.parseInt(strYear) : null;

    return command;
  }

  public static class UserCourseCompetencyMatrixCommandBean {
    private String subject;
    private String user;
    private Integer month;
    private Integer year;
    private Timestamp toDate;

    public String getSubject() {
      return subject;
    }

    public void setSubject(String subject) {
      this.subject = subject;
    }

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
    private static final String SUBJECT = "subject";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
