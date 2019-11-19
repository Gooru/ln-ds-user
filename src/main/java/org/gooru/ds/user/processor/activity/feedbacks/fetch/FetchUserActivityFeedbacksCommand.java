package org.gooru.ds.user.processor.activity.feedbacks.fetch;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

class FetchUserActivityFeedbacksCommand {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(FetchUserActivityFeedbacksCommand.class);

  private String contentId;
  private String userId;

  public String getContentId() {
    return contentId;
  }

  public void setContentId(String contentId) {
    this.contentId = contentId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  static FetchUserActivityFeedbacksCommand builder(JsonObject requestBody) {
    FetchUserActivityFeedbacksCommand result = buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public FetchUserActivityFeedbacksCommandBean asBean() {
    FetchUserActivityFeedbacksCommandBean bean = new FetchUserActivityFeedbacksCommandBean();
    bean.contentId = contentId;
    bean.userId = userId;
    return bean;
  }

  private static FetchUserActivityFeedbacksCommand buildFromJsonObject(JsonObject requestBody) {
    FetchUserActivityFeedbacksCommand result = new FetchUserActivityFeedbacksCommand();
    String contentId = requestBody.getString(CommandAttributes.CONTENT_ID);
    String userId = requestBody.getString(CommandAttributes.USER_ID);
    result.setContentId(contentId);
    result.setUserId(userId);
    return result;
  }

  private void validate() {
    if (userId == null) {
      LOGGER.info("User Id filter not provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "User Id filter not provided for request");
    }
    if (contentId == null) {
      LOGGER.info("Content Id filter not provided for request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Content Id filter not provided for request");
    }
  }


  public static class FetchUserActivityFeedbacksCommandBean {
    private String contentId;
    private String userId;

    public String getContentId() {
      return contentId;
    }

    public void setContentId(String contentId) {
      this.contentId = contentId;
    }

    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }
  }

  static class CommandAttributes {
    private static final String CONTENT_ID = "content_id";
    private static final String USER_ID = "user_id";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
