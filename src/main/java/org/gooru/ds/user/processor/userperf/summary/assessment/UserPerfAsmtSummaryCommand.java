package org.gooru.ds.user.processor.userperf.summary.assessment;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class UserPerfAsmtSummaryCommand {

    private String classId;
    private String courseId;
    private String unitId;
    private String lessonId;
    private String assessmentId;
    private String sessionId;
    private String user;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfAsmtSummaryCommand.class);

    public String getclassId() {
        return classId;
    }

    public String getcourseId() {
        return courseId;
    }

    public String getunitId() {
        return unitId;
    }

    public String getlessonId() {
        return lessonId;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUserId() {
        return user;
    }

    static UserPerfAsmtSummaryCommand builder(JsonObject requestBody) {
        UserPerfAsmtSummaryCommand result = UserPerfAsmtSummaryCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserPerfAsmtSummaryCommandBean asBean() {
        UserPerfAsmtSummaryCommandBean bean = new UserPerfAsmtSummaryCommandBean();
        bean.user = user;
        bean.classId = classId;
        bean.courseId = courseId;
        bean.unitId = unitId;
        bean.lessonId = lessonId;
        bean.assessmentId = assessmentId;
        bean.sessionId = sessionId;

        return bean;
    }

    private static UserPerfAsmtSummaryCommand buildFromJsonObject(JsonObject requestBody) {
        UserPerfAsmtSummaryCommand result = new UserPerfAsmtSummaryCommand();

        result.classId = requestBody.getString(CommandAttributes.CLASS_ID);
        result.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
        result.unitId = requestBody.getString(CommandAttributes.UNIT_ID);
        result.lessonId = requestBody.getString(CommandAttributes.LESSON_ID);
        result.assessmentId = requestBody.getString(CommandAttributes.ASSESSMENT_ID);
        result.sessionId = requestBody.getString(CommandAttributes.SESSION_ID);
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
            LOGGER.info("Class not provided, assumed to be a request for Independent Learner");
            //throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Class not provided");
        }

        if (courseId == null) {
            LOGGER.info("Course not provided");
            //throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Course not provided");
        }

        if (unitId == null) {
            LOGGER.info("Unit not provided");
            //throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Unit not provided");
        }

        if (lessonId == null) {
            LOGGER.info("Lesson not provided");
            //throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Lesson not provided");
        }

        if (assessmentId == null) {
            LOGGER.info("Assessment not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Assessment not provided");
        }

        if (sessionId == null) {
            LOGGER.info("Assessment Session not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "Assessment Session not provided");
        }
    }

    public static class UserPerfAsmtSummaryCommandBean {
        private String classId;
        private String courseId;
        private String unitId;
        private String lessonId;
        private String assessmentId;
        private String sessionId;
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

        public String getLessonId() {
            return lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
        }

        public String getAssessmentId() {
            return assessmentId;
        }

        public void setAssessmentId(String assessmentId) {
            this.assessmentId = assessmentId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }

    static class CommandAttributes {
        private static final String CLASS_ID = "classId";
        private static final String COURSE_ID = "courseId";
        private static final String UNIT_ID = "unitId";
        private static final String LESSON_ID = "lessonId";
        private static final String ASSESSMENT_ID = "assessmentId";
        private static final String SESSION_ID = "sessionId";
        private static final String USER_ID = "user";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
