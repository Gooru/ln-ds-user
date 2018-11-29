package org.gooru.ds.user.processor.baselearnerprofile.read;

import io.vertx.core.json.JsonObject;
import java.util.UUID;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.baselearnerprofile.SubjectInferer;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadBaselineLearnerProfileCommand {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReadBaselineLearnerProfileCommand.class);

  private String courseId;
  private String classId;
  private String user;

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  static ReadBaselineLearnerProfileCommand build(JsonObject requestBody) {
    ReadBaselineLearnerProfileCommand command = buildFromJson(requestBody);
    command.validate();
    return command;
  }

  private static ReadBaselineLearnerProfileCommand buildFromJson(JsonObject requestBody) {
    ReadBaselineLearnerProfileCommand command = new ReadBaselineLearnerProfileCommand();
    command.user = requestBody.getString(CommandAttributes.USER_ID);
    command.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
    command.classId = requestBody.getString(CommandAttributes.CLASS_ID);
    return command;
  }

  public ReadBaselineLearnerProfileCommandBean asBean() {
    ReadBaselineLearnerProfileCommandBean bean = new ReadBaselineLearnerProfileCommandBean();
    bean.classId = classId;
    bean.courseId = courseId;
    bean.user = user;
    bean.subject = initializeSubjectCode(courseId);
    return bean;
  }

  private void validate() {
    validateCourseId(courseId);
    validateUser(user);
    validateClassId(classId);
  }

  private void validateCourseId(String courseId) {
    if (!ValidatorUtils.isValidUUID(courseId)) {
      LOGGER.debug("Invalid Course Id '{}'", courseId);
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid value of courseId");
    }
  }

  private void validateUser(String userId) {
    if (!ValidatorUtils.isValidUUID(userId)) {
      LOGGER.debug("Invalid user '{}'", userId);
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid value of user");
    }
  }

  private void validateClassId(String classId) {
    if (classId != null && !ValidatorUtils.isValidUUID(classId)) {
      LOGGER.debug("Invalid class id '{}'", classId);
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid value of class id");
    }
  }

  private static String initializeSubjectCode(String courseId) {
    return SubjectInferer.build().inferSubjectForCourse(UUID.fromString(courseId));
  }

  public static class ReadBaselineLearnerProfileCommandBean {

    private String courseId;
    private String classId;
    private String user;
    private String subject;

    public String getCourseId() {
      return courseId;
    }

    public void setCourseId(String courseId) {
      this.courseId = courseId;
    }

    public String getClassId() {
      return classId;
    }

    public void setClassId(String classId) {
      this.classId = classId;
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

    private static final String USER_ID = "userId";
    private static final String COURSE_ID = "courseId";
    private static final String CLASS_ID = "classId";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }
}
