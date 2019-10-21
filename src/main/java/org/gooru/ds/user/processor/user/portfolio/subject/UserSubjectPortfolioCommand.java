package org.gooru.ds.user.processor.user.portfolio.subject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.constants.HttpConstants.HttpStatus;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hazelcast.util.StringUtil;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserSubjectPortfolioCommand {

  private String subjectCode;
  private String userId;
  private String activityType;
  private Date dateUntil;
  private Integer offset;
  private Integer limit;
  private Date startDate;
  private Date endDate;
  
  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserSubjectPortfolioCommand.class);

  public String getSubjectCode() {
    return subjectCode;
  }
  
  public String getUserId() {
    return userId;
  }


  public Date getDateUntil() {
    return dateUntil;
  }

  public void setDateUntil(Date startDate) {
    this.dateUntil = startDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public String getActivityType() {
    return activityType;
  }

  public void setActivityType(String activityType) {
    this.activityType = activityType;
  }
  
  static UserSubjectPortfolioCommand builder(JsonObject requestBody) {
    UserSubjectPortfolioCommand result =
        UserSubjectPortfolioCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserSubjectPortfolioCommandBean asBean() {
    UserSubjectPortfolioCommandBean bean = new UserSubjectPortfolioCommandBean();
    bean.userId = userId;
    bean.subjectCode = subjectCode;
    bean.dateUntil = dateUntil;
    bean.startDate = startDate;
    bean.endDate = endDate;
    bean.limit = limit;
    bean.offset = offset;
    bean.activityType = activityType;
    return bean;
  }

  private static UserSubjectPortfolioCommand buildFromJsonObject(JsonObject requestBody) {
    UserSubjectPortfolioCommand result = new UserSubjectPortfolioCommand();

    result.subjectCode = requestBody.getString(CommandAttributes.SUBJECT_CODE);
    result.userId = requestBody.getString(CommandAttributes.USER_ID);
    
    result.limit = Integer.valueOf(requestBody.getString(CommandAttributes.LIMIT, "50"));
    result.offset = Integer.valueOf(requestBody.getString(CommandAttributes.OFFSET, "0"));
    result.activityType = requestBody.getString(CommandAttributes.ACTIVITY_TYPE);

    String startDate = requestBody.getString(CommandAttributes.START_DATE);
    if (startDate != null) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try {
        result.startDate = (Date) simpleDateFormat.parse(startDate);
      } catch (Exception e) {
        LOGGER.warn("Invalid startDate requested");
        throw new HttpResponseWrapperException(HttpStatus.BAD_REQUEST,
            "Format of startDate is invalid!");
      }
    }

    String dateUntil = requestBody.getString(CommandAttributes.DATE_UNTIL);
    if (dateUntil != null) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try {
        result.dateUntil = (Date) simpleDateFormat.parse(dateUntil);
      } catch (Exception e) {
        LOGGER.warn("Invalid dateUntil requested");
        throw new HttpResponseWrapperException(HttpStatus.BAD_REQUEST,
            "Format of dateUntil is invalid!");
      }
    }

    if (result.startDate == null && result.endDate == null) {
      if (dateUntil != null) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
          result.dateUntil = (Date) simpleDateFormat.parse(dateUntil);
        } catch (Exception e) {
          LOGGER.warn("Invalid date requested");
          throw new HttpResponseWrapperException(HttpStatus.BAD_REQUEST,
              "Format of Date until is invalid!");
        }
      } else {
        LocalDate today = LocalDate.now();
        LocalDate localDate = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        result.dateUntil = java.sql.Date.valueOf(localDate);
      }
    }

    String endDate = requestBody.getString(CommandAttributes.END_DATE);
    if (endDate != null) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try {
        result.endDate = (Date) simpleDateFormat.parse(endDate);
      } catch (Exception e) {
        LOGGER.warn("Invalid endDate requested");
        throw new HttpResponseWrapperException(HttpStatus.BAD_REQUEST,
            "Format of endDate is invalid!");
      }
    }

    return result;
  }

  private void validate() {

    if (ValidatorUtils.isValidUUID(userId)) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided in the request");
    }
    
    if (StringUtil.isNullOrEmpty(subjectCode)) {
      LOGGER.info("Subject Code not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Subject Code not provided in the request");
    }
    
    if (StringUtil.isNullOrEmpty(activityType)) {
      LOGGER.info("activityType to fetch is not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "activityType to fetch is not provided in request");
    }

    if ((startDate == null && endDate != null) || (startDate != null && endDate == null)) {
      LOGGER.warn("start or end date is missing");
      throw new HttpResponseWrapperException(HttpStatus.BAD_REQUEST,
          "start or end date is missing");
    }

    if (limit < 0 || offset < 0) {
      LOGGER.warn("Limit/Offset requested is negative");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Limit/Offset requested should not be negative");
    }

  }

  public static class UserSubjectPortfolioCommandBean {
    
    private String subjectCode;    
    private String userId;
    private Date dateUntil;
    private Integer offset;
    private Integer limit;
    private String activityType;
    private Date startDate;
    private Date endDate;
    
    public String getSubjectCode() {
      return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
      this.subjectCode = subjectCode;
    }

    public String getUserId() {
      return userId;
    }
    public void setUserId(String user) {
      this.userId = user;
    }
    public Date getDateUntil() {
      return dateUntil;
    }

    public void setDateUntil(Date dateUntil) {
      this.dateUntil = dateUntil;
    }

    public Integer getOffset() {
      return offset;
    }

    public void setOffset(Integer offset) {
      this.offset = offset;
    }

    public Integer getLimit() {
      return limit;
    }

    public void setLimit(Integer limit) {
      this.limit = limit;
    }

    public String getActivityType() {
      return activityType;
    }

    public void setActivityType(String activityType) {
      this.activityType = activityType;
    }

    public Date getStartDate() {
      return startDate;
    }

    public void setStartDate(Date startDate) {
      this.startDate = startDate;
    }

    public Date getEndDate() {
      return endDate;
    }

    public void setEndDate(Date endDate) {
      this.endDate = endDate;
    }
  }

  static class CommandAttributes {
    
    private static final String SUBJECT_CODE = "subjectCode";
    private static final String USER_ID = "userId";
    private static final String START_DATE = "startDate";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String ACTIVITY_TYPE = "activityType";
    private static final String END_DATE = "endDate";
    private static final String DATE_UNTIL = "dateUntil";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
