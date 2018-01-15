package org.gooru.ds.user.processor.userperf.course;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class UserPerfCourseCommand {

    private String classId;
    private String courseId;
    private String user;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCourseCommand.class);

    public String getclassId() {
        return classId;
    }

    public String getcourseId() {
        return courseId;
    }

    public String getUser() {
        return user;
    }

    static UserPerfCourseCommand builder(JsonObject requestBody) {
        UserPerfCourseCommand result = UserPerfCourseCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserPerfCourseCommandBean asBean() {
        UserPerfCourseCommandBean bean = new UserPerfCourseCommandBean();
        bean.user = user;
        bean.classId = classId;
        bean.courseId = courseId;

        return bean;
    }

    private static UserPerfCourseCommand buildFromJsonObject(JsonObject requestBody) {
        UserPerfCourseCommand result = new UserPerfCourseCommand();

        result.classId = requestBody.getString(CommandAttributes.CLASS_ID);
        result.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
        result.user = requestBody.getString(CommandAttributes.USER);
        return result;
    }

    private void validate() {

        if (user == null) {
            LOGGER.info("User not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "User not provided for request");
        }

        if (classId == null) {
            LOGGER.info("Class not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Class not provided");
        }

        if (courseId == null) {
            LOGGER.info("Course not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Course not provided");
        }
    }

    public static class UserPerfCourseCommandBean {
        private String classId;
        private String courseId;
        private String user;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }
    }

    static class CommandAttributes {
        private static final String CLASS_ID = "classId";
        private static final String COURSE_ID = "courseId";
        private static final String USER = "user";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
