package org.gooru.ds.user.processor.userperf.summary.collection;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserPerfCollSummaryCommand {
	
	private String classId;
    private String courseId;
    private String unitId;
    private String lessonId;
    private String collectionId;
    private String sessionId;
    private String user;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCollSummaryCommand.class);

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

    public String getCollectionId() {
        return collectionId;
    }

    public String getUserId() {
        return user;
    }

    public String getSessionId() {
        return sessionId;
    }

    static UserPerfCollSummaryCommand builder(JsonObject requestBody) {
    	UserPerfCollSummaryCommand result = UserPerfCollSummaryCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserPerfCollSummaryCommandBean asBean() {
    	UserPerfCollSummaryCommandBean bean = new UserPerfCollSummaryCommandBean();
        bean.user = user;
        bean.classId = classId;
        bean.courseId = courseId;
        bean.unitId = unitId;
        bean.lessonId = lessonId;
        bean.collectionId = collectionId;
        bean.sessionId = sessionId;
        
        return bean;
    }

    private static UserPerfCollSummaryCommand buildFromJsonObject(JsonObject requestBody) {
    	UserPerfCollSummaryCommand result = new UserPerfCollSummaryCommand();

        result.classId = requestBody.getString(CommandAttributes.CLASS_ID);
        result.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
        result.unitId = requestBody.getString(CommandAttributes.UNIT_ID);
        result.lessonId = requestBody.getString(CommandAttributes.LESSON_ID);
        result.collectionId = requestBody.getString(CommandAttributes.COLLECTION_ID);
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
            LOGGER.info("Class not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "Class not provided");
        }
        
        if (courseId == null) {
            LOGGER.info("Course not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "Course not provided");
        }
        
        if (unitId == null) {
            LOGGER.info("Unit not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "Unit not provided");
        }
        
        if (lessonId == null) {
            LOGGER.info("Lesson not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "Lesson not provided");
        }
        
        if (collectionId == null) {
            LOGGER.info("Collection not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "Collection not provided");
        }
        
    }

    public static class UserPerfCollSummaryCommandBean {
        private String classId;
        private String courseId;
        private String unitId;
        private String lessonId;
        private String collectionId;
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
        
        public String getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(String collectionId) {
            this.collectionId = collectionId;
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
        private static final String COLLECTION_ID = "collectionId";
        private static final String SESSION_ID = "sessionId";
        private static final String USER_ID = "user";
    }


}
