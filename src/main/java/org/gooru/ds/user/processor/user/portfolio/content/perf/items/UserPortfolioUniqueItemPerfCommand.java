package org.gooru.ds.user.processor.user.portfolio.content.perf.items;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.constants.HttpConstants.HttpStatus;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hazelcast.util.StringUtil;
import io.vertx.core.json.JsonObject;

/**
 * @author renuka
 */
public class UserPortfolioUniqueItemPerfCommand {

  private List<String> userIds;
  private Date dateUntil;
  private Integer offset;
  private Integer limit;
  private Integer month;
  private Integer year;
  private Boolean showSuggestedContent;
  private Boolean aggregateByContentSource;
  private String contentSource;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioUniqueItemPerfCommand.class);
  public static Pattern YEAR_PATTERN = Pattern.compile("^\\d{4}$");

  public List<String> getUserIds() {
    return userIds;
  }

  public void setUserIds(List<String> userIds) {
    this.userIds = userIds;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Date getDateUntil() {
    return dateUntil;
  }

  public void setDateUntil(Date startDate) {
    this.dateUntil = startDate;
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

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Boolean getShowSuggestedContent() {
    return showSuggestedContent;
  }

  public void setShowSuggestedContent(Boolean showSuggestedContent) {
    this.showSuggestedContent = showSuggestedContent;
  }

  public Boolean getAggregateByContentSource() {
    return aggregateByContentSource;
  }

  public void setAggregateByContentSource(Boolean aggregateByContentSource) {
    this.aggregateByContentSource = aggregateByContentSource;
  }

  public String getContentSource() {
    return contentSource;
  }

  public void setContentSource(String contentSource) {
    this.contentSource = contentSource;
  }

  static UserPortfolioUniqueItemPerfCommand builder(JsonObject requestBody) {
    UserPortfolioUniqueItemPerfCommand result = UserPortfolioUniqueItemPerfCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserPortfolioUniqueItemPerfCommandBean asBean() {
    UserPortfolioUniqueItemPerfCommandBean bean = new UserPortfolioUniqueItemPerfCommandBean();
    bean.userIds = userIds;
    bean.dateUntil = dateUntil;
    bean.limit = limit;
    bean.offset = offset;
    bean.contentSource = contentSource;
    bean.showSuggestedContent = showSuggestedContent;
    bean.aggregateByContentSource = aggregateByContentSource;
    bean.month = month;
    bean.year = year;

    return bean;
  }

  private static UserPortfolioUniqueItemPerfCommand buildFromJsonObject(JsonObject requestBody) {
    UserPortfolioUniqueItemPerfCommand result = new UserPortfolioUniqueItemPerfCommand();
    LOGGER.info("requestBody : {}", requestBody);
    if (requestBody.containsKey(CommandAttributes.USER_IDS) && !StringUtil.isNullOrEmptyAfterTrim(requestBody.getString(CommandAttributes.USER_IDS))) {
      String[] userIds = requestBody.getString(CommandAttributes.USER_IDS).split(",");
      result.userIds = Arrays.asList(userIds);
    }
    result.limit = Integer.valueOf(requestBody.getString(CommandAttributes.LIMIT, "50"));
    result.offset = Integer.valueOf(requestBody.getString(CommandAttributes.OFFSET, "0"));
    result.contentSource = requestBody.getString(CommandAttributes.CONTENT_SOURCE);
    try {
      result.aggregateByContentSource =
          requestBody.getBoolean(CommandAttributes.AGGREGATE_BY_CONTENT_SOURCE, false);
    } catch (Exception e) {
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Format of aggregateBycontentSource is invalid!");
    }

    try {
      result.showSuggestedContent =
          requestBody.getBoolean(CommandAttributes.SHOW_SUGGESTED_CONTENT, false);
    } catch (Exception e) {
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Format of showSuggestedContent is invalid!");
    }
    try {
      result.month = requestBody.getString(CommandAttributes.MONTH) != null
          ? Integer.parseInt(requestBody.getString(CommandAttributes.MONTH))
          : null;
      result.year = requestBody.getString(CommandAttributes.YEAR) != null
          ? Integer.parseInt(requestBody.getString(CommandAttributes.YEAR))
          : null;
    } catch (Exception e) {
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Format of Year/ Month is invalid!");
    }

    String dateTill = requestBody.getString(CommandAttributes.START_DATE);

    if (dateTill != null) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try {
        result.dateUntil = (Date) simpleDateFormat.parse(dateTill);
      } catch (Exception e) {
        LOGGER.warn("Invalid date requested");
        throw new HttpResponseWrapperException(HttpStatus.BAD_REQUEST,
            "Format of Start date is invalid!");
      }
    } else {
      LocalDate today = LocalDate.now();
      LocalDate localDate = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
      Date dateUntil = java.sql.Date.valueOf(localDate);
      result.dateUntil = dateUntil;
    }
    return result;
  }

  private void validate() {

    if (userIds == null) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided for request");
    }
    
    if (dateUntil == null && (month == null && year == null)) {
      LOGGER.warn("Period from when data has to be fetched is missing");
      throw new HttpResponseWrapperException(HttpStatus.BAD_REQUEST,
          "Period from when data has to be fetched is missing");
    }

    if (year != null && month != null
        && (!YEAR_PATTERN.matcher(year.toString()).matches() || (month < 1 || month > 12))) {
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "year/month is invalid");
    }
    
    if (limit < 0 || offset < 0) {
      LOGGER.warn("Limit/Offset requested is negative");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Limit/Offset requested should not be negative");
    }
  }

  public static class UserPortfolioUniqueItemPerfCommandBean {
    private List<String> userIds;
    private Date dateUntil;
    private Integer offset;
    private Integer limit;
    private Integer month;
    private Integer year;
    private Boolean showSuggestedContent;
    private Boolean aggregateByContentSource;
    private String contentSource;
    
    public List<String> getUserIds() {
      return userIds;
    }
    public void setUserIds(List<String> userIds) {
      this.userIds = userIds;
    }
    public Integer getYear() {
      return year;
    }
    public void setYear(Integer year) {
      this.year = year;
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
    public Integer getMonth() {
      return month;
    }
    public void setMonth(Integer month) {
      this.month = month;
    }
    public Boolean getShowSuggestedContent() {
      return showSuggestedContent;
    }
    public void setShowSuggestedContent(Boolean showSuggestedContent) {
      this.showSuggestedContent = showSuggestedContent;
    }
    public Boolean getAggregateBycontentSource() {
      return aggregateByContentSource;
    }
    public void setAggregateByContentSource(Boolean aggregateByContentSource) {
      this.aggregateByContentSource = aggregateByContentSource;
    }
    public String getContentSource() {
      return contentSource;
    }
    public void setContentSource(String contentSource) {
      this.contentSource = contentSource;
    }

  }

  static class CommandAttributes {
    private static final String USER_IDS = "userIds";
    private static final String START_DATE = "startDate";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String SHOW_SUGGESTED_CONTENT = "showSuggestedContent";
    private static final String AGGREGATE_BY_CONTENT_SOURCE = "aggregateByContentSource";
    private static final String CONTENT_SOURCE = "contentSource";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
