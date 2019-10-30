package org.gooru.ds.user.processor.user.portfolio.content.item;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.constants.HttpConstants.HttpStatus;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author renuka
 */
public class UserPortfolioItemPerfCommand {

  private String userId;
  private String itemId;
  private String itemType;
  private Date dateUntil;
  private Integer offset;
  private Integer limit;
  private Date startDate;
  private Date endDate;
  private String contentSource;
  private Boolean aggByContentSource;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioItemPerfCommand.class);

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
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

  public String getContentSource() {
    return contentSource;
  }

  public void setContentSource(String contentSource) {
    this.contentSource = contentSource;
  }
  
  public Boolean getAggByContentSource() {
    return aggByContentSource;
  }

  public void setAggByContentSource(Boolean aggByContentSource) {
    this.aggByContentSource = aggByContentSource;
  }

  static UserPortfolioItemPerfCommand builder(JsonObject requestBody) {
    UserPortfolioItemPerfCommand result = UserPortfolioItemPerfCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserPortfolioUniqueItemPerfCommandBean asBean() {
    UserPortfolioUniqueItemPerfCommandBean bean = new UserPortfolioUniqueItemPerfCommandBean();
    bean.userId = userId;
    bean.itemId = itemId;
    bean.itemType = itemType;
    bean.dateUntil = dateUntil;
    bean.limit = limit;
    bean.offset = offset;
    bean.contentSource = contentSource;
    bean.startDate = startDate;
    bean.endDate = endDate;
    bean.aggByContentSource = aggByContentSource;
    return bean;
  }

  private static UserPortfolioItemPerfCommand buildFromJsonObject(JsonObject requestBody) {
    UserPortfolioItemPerfCommand result = new UserPortfolioItemPerfCommand();
    result.userId = requestBody.getString(CommandAttributes.USER_ID);
    result.itemId = requestBody.getString(CommandAttributes.ITEM_ID);
    result.itemType = requestBody.getString(CommandAttributes.ITEM_TYPE);
    result.limit = Integer.valueOf(requestBody.getString(CommandAttributes.LIMIT, "50"));
    result.offset = Integer.valueOf(requestBody.getString(CommandAttributes.OFFSET, "0"));
    result.contentSource = requestBody.getString(CommandAttributes.CONTENT_SOURCE);
    
    try {
      result.aggByContentSource =
          Boolean.valueOf(requestBody.getString(CommandAttributes.AGG_BY_CONTENT_SOURCE, "false"));
    } catch (Exception e) {
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Format of aggregateBycontentSource is invalid!");
    }
    
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
    
    if (!ValidatorUtils.isValidUUID(userId)) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided in request");
    }
    
    if (!ValidatorUtils.isValidUUID(itemId)) {
      LOGGER.info("Item Id is not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Item Id not provided in request");
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

  public static class UserPortfolioUniqueItemPerfCommandBean {
    private String userId;
    private String itemId;
    private String itemType;
    private Date dateUntil;
    private Integer offset;
    private Integer limit;
    private Date startDate;
    private Date endDate;
    private String contentSource;
    private Boolean aggByContentSource;
    
    public String getUserId() {
      return userId;
    }
    public void setUserId(String userId) {
      this.userId = userId;
    }
    public String getItemId() {
      return itemId;
    }
    public void setItemId(String itemId) {
      this.itemId = itemId;
    }
    public String getItemType() {
      return itemType;
    }
    public void setItemType(String itemType) {
      this.itemType = itemType;
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

    public String getContentSource() {
      return contentSource;
    }
    public void setContentSource(String contentSource) {
      this.contentSource = contentSource;
    }
    
    public Boolean getAggBycontentSource() {
      return aggByContentSource;
    }
    public void setAggByContentSource(Boolean aggByContentSource) {
      this.aggByContentSource = aggByContentSource;
    }

  }

  static class CommandAttributes {
    private static final String USER_ID = "userId";
    private static final String ITEM_ID = "itemId";
    private static final String ITEM_TYPE = "itemType";
    private static final String START_DATE = "startDate";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String CONTENT_SOURCE = "contentSource";
    private static final String END_DATE = "endDate";
    private static final String DATE_UNTIL = "dateUntil";
    private static final String AGG_BY_CONTENT_SOURCE = "aggByContentSource";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
