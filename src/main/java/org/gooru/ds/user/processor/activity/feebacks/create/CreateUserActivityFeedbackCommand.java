package org.gooru.ds.user.processor.activity.feebacks.create;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class CreateUserActivityFeedbackCommand {

  private String contentId;
  private String contentType;
  private String userId;
  private Integer userCategoryId;
  private List<UserFeedback> userFeedbacks;

  private static final List<String> MANDATORY_FIELDS =
      new ArrayList<>(Arrays.asList(CommandAttributes.CONTENT_ID, CommandAttributes.CONTENT_TYPE,
          CommandAttributes.USER_ID, CommandAttributes.USER_CATEGORY_ID,
          CommandAttributes.USER_FEEDBACKS));

  private static final List<String> ALLOWED_FIELDS =
      new ArrayList<>(Arrays.asList(CommandAttributes.CONTENT_ID, CommandAttributes.CONTENT_TYPE,
          CommandAttributes.USER_ID, CommandAttributes.USER_CATEGORY_ID,
          CommandAttributes.USER_FEEDBACKS));

  private static final Logger LOGGER =
      LoggerFactory.getLogger(CreateUserActivityFeedbackCommand.class);

  public String getContentId() {
    return contentId;
  }

  public void setContentId(String contentId) {
    this.contentId = contentId;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Integer getUserCategoryId() {
    return userCategoryId;
  }

  public void setUserCategoryId(Integer userCategoryId) {
    this.userCategoryId = userCategoryId;
  }

  public List<UserFeedback> getUserFeedbacks() {
    return userFeedbacks;
  }

  public void setUserFeedbacks(List<UserFeedback> userFeedbacks) {
    this.userFeedbacks = userFeedbacks;
  }

  static CreateUserActivityFeedbackCommand builder(JsonObject requestBody) {
    validate(requestBody);
    CreateUserActivityFeedbackCommand result = buildFromJsonObject(requestBody);
    return result;
  }

  public List<CreateUserActivityFeedbackModel> asBean() {
    List<CreateUserActivityFeedbackModel> userActivityFeedbacks = new ArrayList<>();
    userFeedbacks.forEach(userFeedback -> {
      CreateUserActivityFeedbackModel createUserActivityFeedbackModel =
          new CreateUserActivityFeedbackModel();
      createUserActivityFeedbackModel.setContentId(contentId);
      createUserActivityFeedbackModel.setContentType(contentType);
      createUserActivityFeedbackModel.setUserCategoryId(userCategoryId);
      createUserActivityFeedbackModel.setUserId(userId);
      createUserActivityFeedbackModel.setFeedbackCategoryId(userFeedback.getFeedbackCategoryId());
      createUserActivityFeedbackModel
          .setUserFeedbackQualitative(userFeedback.getUserFeedbackQualitative());
      createUserActivityFeedbackModel
          .setUserFeedbackQuantitative(userFeedback.getUserFeedbackQuantitative());
      userActivityFeedbacks.add(createUserActivityFeedbackModel);
    });

    return userActivityFeedbacks;
  }

  private static CreateUserActivityFeedbackCommand buildFromJsonObject(JsonObject requestBody) {
    CreateUserActivityFeedbackCommand result = new CreateUserActivityFeedbackCommand();
    String contentId = requestBody.getString(CommandAttributes.CONTENT_ID);
    String contentType = requestBody.getString(CommandAttributes.CONTENT_TYPE);
    Integer userCategoryId = requestBody.getInteger(CommandAttributes.USER_CATEGORY_ID);
    String userId = requestBody.getString(CommandAttributes.USER_ID);
    JsonArray userFeedbacks = requestBody.getJsonArray(CommandAttributes.USER_FEEDBACKS);
    List<UserFeedback> userFeedbackResults = new ArrayList<>();
    userFeedbacks.forEach(userFeedback -> {
      UserFeedback userFeedbackResult = new UserFeedback();
      JsonObject userFeedbackJson = (JsonObject) userFeedback;
      Integer feedbackCategoryId =
          userFeedbackJson.getInteger(CommandAttributes.FEEDBACK_CATEGORY_ID);
      String userFeedbackQualitative =
          userFeedbackJson.getString(CommandAttributes.USER_FEEDBACK_QUALITATIVE);
      Integer userFeedbackQuantitative =
          userFeedbackJson.getInteger(CommandAttributes.USER_FEEDBACK_QUANTITATIVE);
      userFeedbackResult.setFeedbackCategoryId(feedbackCategoryId);
      userFeedbackResult.setUserFeedbackQualitative(userFeedbackQualitative);
      userFeedbackResult.setUserFeedbackQuantitative(userFeedbackQuantitative);
      userFeedbackResults.add(userFeedbackResult);
    });
    result.setContentId(contentId);
    result.setContentType(contentType);
    result.setUserCategoryId(userCategoryId);
    result.setUserId(userId);
    result.setUserFeedbacks(userFeedbackResults);
    return result;
  }

  static class UserFeedback {
    private Integer feedbackCategoryId;
    private Integer userFeedbackQuantitative;
    private String userFeedbackQualitative;

    private static final List<String> MANDATORY_FIELDS =
        new ArrayList<>(Arrays.asList(CommandAttributes.FEEDBACK_CATEGORY_ID));

    private static final List<String> ALLOWED_FIELDS =
        new ArrayList<>(Arrays.asList(CommandAttributes.FEEDBACK_CATEGORY_ID,
            CommandAttributes.USER_FEEDBACK_QUALITATIVE,
            CommandAttributes.USER_FEEDBACK_QUANTITATIVE));

    public Integer getFeedbackCategoryId() {
      return feedbackCategoryId;
    }

    public void setFeedbackCategoryId(Integer feebackCategoryId) {
      this.feedbackCategoryId = feebackCategoryId;
    }

    public Integer getUserFeedbackQuantitative() {
      return userFeedbackQuantitative;
    }

    public void setUserFeedbackQuantitative(Integer userFeedbackQuantitative) {
      this.userFeedbackQuantitative = userFeedbackQuantitative;
    }

    public String getUserFeedbackQualitative() {
      return userFeedbackQualitative;
    }

    public void setUserFeedbackQualitative(String userFeedbackQualitative) {
      this.userFeedbackQualitative = userFeedbackQualitative;
    }
  }

  private static void validate(JsonObject requestBody) {
    JsonObject allowedFieldErrors =
        ValidatorUtils.validateAllowedFields(ALLOWED_FIELDS, requestBody);
    if (allowedFieldErrors != null) {
      LOGGER.info("Not allowed fields are provided in the request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          allowedFieldErrors);
    }
    JsonObject mandatoryErrors =
        ValidatorUtils.validateMandatoryFields(MANDATORY_FIELDS, requestBody);
    if (mandatoryErrors != null) {
      LOGGER.info("mandatory fields are not provided in the request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, mandatoryErrors);
    }

    JsonArray userFeedbacks = requestBody.getJsonArray(CommandAttributes.USER_FEEDBACKS);
    if (userFeedbacks.isEmpty()) {
      LOGGER.info("user_feedbacks field should be empty");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "user_feedbacks field should be empty");
    }
    userFeedbacks.forEach(userFeedback -> {
      JsonObject userFeedbackJson = (JsonObject) userFeedback;
      JsonObject userFeebackAllowedFieldErrors =
          ValidatorUtils.validateAllowedFields(UserFeedback.ALLOWED_FIELDS, userFeedbackJson);
      if (userFeebackAllowedFieldErrors != null) {
        LOGGER.info("Not allowed fields are provided in the user feedback json object");
        throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
            userFeebackAllowedFieldErrors);
      }
      JsonObject userFeebackMandatoryFieldErrors =
          ValidatorUtils.validateMandatoryFields(UserFeedback.MANDATORY_FIELDS, userFeedbackJson);
      if (userFeebackMandatoryFieldErrors != null) {
        LOGGER.info("mandatory fields are not provided in the user feedback json object");
        throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
            userFeebackMandatoryFieldErrors);
      }

    });

  }

  static class CommandAttributes {
    private static final String CONTENT_ID = "content_id";
    private static final String CONTENT_TYPE = "content_type";
    private static final String USER_ID = "user_id";
    private static final String USER_CATEGORY_ID = "user_category_id";
    private static final String USER_FEEDBACKS = "user_feedbacks";
    private static final String FEEDBACK_CATEGORY_ID = "feeback_category_id";
    private static final String USER_FEEDBACK_QUANTITATIVE = "user_feedback_quantitative";
    private static final String USER_FEEDBACK_QUALITATIVE = "user_feedback_qualitative";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
