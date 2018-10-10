package org.gooru.ds.user.processor.baselearnerprofile;

import java.util.UUID;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 * 
 */
public class LearnerProfileBaselineUpdateCommand {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LearnerProfileBaselineUpdateCommand.class);
	
	private String courseId;
	private String classId;
    private String user;
    private String subjectCode;

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

    public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	static LearnerProfileBaselineUpdateCommand builder(JsonObject requestBody) {
        LearnerProfileBaselineUpdateCommand result = buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public LearnerProfileReadCommandBean asBean() {
        LearnerProfileReadCommandBean bean = new LearnerProfileReadCommandBean();
        bean.user = user;
        bean.subjectCode = subjectCode;
        return bean;
    }

    private void validate() {
        if (courseId == null) {
            LOGGER.debug("Invalid Course Id");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid courseId1");
        } else if (user == null) {
            LOGGER.debug("Invalid user");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid user");
        }

    }

    private static LearnerProfileBaselineUpdateCommand buildFromJsonObject(JsonObject requestBody) {
        LearnerProfileBaselineUpdateCommand command = new LearnerProfileBaselineUpdateCommand();
        command.user = requestBody.getString(CommandAttributes.USER_ID);
        command.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
        command.classId = requestBody.getString(CommandAttributes.CLASS_ID);        
        command.subjectCode = initializeSubjectCode(command.courseId);
        
        return command;
    }

    private static String initializeSubjectCode(String courseId) {    	
    	String sc = SubjectInferer.build().inferSubjectForCourse(UUID.fromString(courseId));
    	if (sc == null) {
    		LOGGER.warn("Not able to find subject code for specified course '{}'", courseId);
    		return null;
    	}
    	
    	LOGGER.debug("The Subject Code is" + sc);
    	return sc;
    }

    public static class LearnerProfileReadCommandBean {
		private String user;
		private String subjectCode;
		
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getSubjectCode() {
			return subjectCode;
		}
		public void setSubjectCode(String subjectCode) {
			this.subjectCode = subjectCode;
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
