package org.gooru.ds.user.processor.userperf.lesson;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class UserPerfLessonCommand {

    private String classId;
    private String courseId;
    private String unitId;
    private String user;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfLessonCommand.class);

    public String getclassId() {
        return classId;
    }

    public String getcourseId() {
        return courseId;
    }

    public String getunitId() {
        return unitId;
    }

    public String getUserId() {
        return user;
    }

    static UserPerfLessonCommand builder(JsonObject requestBody) {
        UserPerfLessonCommand result = UserPerfLessonCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserPerfLessonCommandBean asBean() {
        UserPerfLessonCommandBean bean = new UserPerfLessonCommandBean();
        bean.user = user;
        bean.classId = classId;
        bean.courseId = courseId;
        bean.unitId = unitId;

        return bean;
    }

    private static UserPerfLessonCommand buildFromJsonObject(JsonObject requestBody) {
        UserPerfLessonCommand result = new UserPerfLessonCommand();

        result.classId = requestBody.getString(CommandAttributes.CLASS_ID);
        result.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
        result.unitId = requestBody.getString(CommandAttributes.UNIT_ID);
        result.user = requestBody.getString(CommandAttributes.USER_ID);
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

        if (unitId == null) {
            LOGGER.info("Unit not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Unit not provided");
        }
    }

    public static class UserPerfLessonCommandBean {
        private String classId;
        private String courseId;
        private String unitId;
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

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }
    }

    static class CommandAttributes {
        private static final String CLASS_ID = "classId";
        private static final String COURSE_ID = "courseId";
        private static final String UNIT_ID = "unitId";
        private static final String USER_ID = "user";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
