package org.gooru.ds.user.processor.user.portfolio.content.summary.assessment;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hazelcast.util.StringUtil;
import io.vertx.core.json.JsonObject;

/**
 * @author renuka
 */
public class UserPortfolioItemSummaryCommand {

  private String userId;
  private String itemId;
  private String itemType;
  private String sessionId;
  private String contentSource;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioItemSummaryCommand.class);

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

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getContentSource() {
    return contentSource;
  }

  public void setContentSource(String contentSource) {
    this.contentSource = contentSource;
  }

  static UserPortfolioItemSummaryCommand builder(JsonObject requestBody) {
    UserPortfolioItemSummaryCommand result = UserPortfolioItemSummaryCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public UserPortfolioItemSummaryCommandBean asBean() {
    UserPortfolioItemSummaryCommandBean bean = new UserPortfolioItemSummaryCommandBean();
    bean.userId = userId;
    bean.itemId = itemId;
    bean.itemType = itemType;
    bean.contentSource = contentSource;
    bean.sessionId = sessionId;
    return bean;
  }

  private static UserPortfolioItemSummaryCommand buildFromJsonObject(JsonObject requestBody) {
    UserPortfolioItemSummaryCommand result = new UserPortfolioItemSummaryCommand();
    result.userId = requestBody.getString(CommandAttributes.USER_ID);
    result.itemId = requestBody.getString(CommandAttributes.ITEM_ID);
    result.itemType = requestBody.getString(CommandAttributes.ITEM_TYPE);
    result.sessionId = requestBody.getString(CommandAttributes.SESSION_ID);
    result.contentSource = requestBody.getString(CommandAttributes.CONTENT_SOURCE);
    return result;
  }

  private void validate() {

    if (!ValidatorUtils.isValidUUID(userId)) {
      LOGGER.info("User not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User not provided in request");
    }
    if (!ValidatorUtils.isValidUUID(sessionId)) {
      LOGGER.info("sessionId not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "sessionId not provided");
    }
    if (!ValidatorUtils.isValidUUID(itemId)) {
      LOGGER.info("itemId not provided");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "itemId not provided");
    }
    if (StringUtil.isNullOrEmpty(contentSource)) {
      LOGGER.info("contentSource is missing");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "contentSource is missing");
    }

  }

  public static class UserPortfolioItemSummaryCommandBean {
    private String userId;
    private String itemId;
    private String itemType;
    private String sessionId;
    private String contentSource;
    
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
    public String getSessionId() {
      return sessionId;
    }
    public void setSessionId(String sessionId) {
      this.sessionId = sessionId;
    }
    public String getContentSource() {
      return contentSource;
    }
    public void setContentSource(String contentSource) {
      this.contentSource = contentSource;
    }

  }

  static class CommandAttributes {
    private static final String USER_ID = "userId";
    private static final String ITEM_ID = "itemId";
    private static final String ITEM_TYPE = "itemType";
    private static final String SESSION_ID = "sessionId";
    private static final String CONTENT_SOURCE = "contentSource";
    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
