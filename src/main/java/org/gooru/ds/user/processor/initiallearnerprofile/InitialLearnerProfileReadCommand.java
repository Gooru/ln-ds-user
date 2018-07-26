package org.gooru.ds.user.processor.initiallearnerprofile;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 * 
 */
public class InitialLearnerProfileReadCommand {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitialLearnerProfileReadCommand.class);

	private String subjectCode;
    private String grade;
    private String user;
    
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
    

	static InitialLearnerProfileReadCommand builder(JsonObject requestBody) {
        InitialLearnerProfileReadCommand result = buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public BaseUserProfileCommandBean asBean() {
        BaseUserProfileCommandBean bean = new BaseUserProfileCommandBean();
        bean.user = user;
        bean.subjectCode = subjectCode;
        return bean;
    }

    private void validate() {
        if (subjectCode == null) {
            LOGGER.debug("Provided null subjectCode");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid subjectCode");
        } else if (user == null) {
            LOGGER.debug("Provided null user");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid user");
        }

    }

    private static InitialLearnerProfileReadCommand buildFromJsonObject(JsonObject requestBody) {
        InitialLearnerProfileReadCommand command = new InitialLearnerProfileReadCommand();
        command.user = requestBody.getString(CommandAttributes.USER);
        command.subjectCode = requestBody.getString(CommandAttributes.SUBJECT);
        command.grade = requestBody.getString(CommandAttributes.GRADE);
        
        LOGGER.info("User: " + command.user);
        LOGGER.info("SUBJECT: " + command.subjectCode);
        LOGGER.info("GRADE: " + command.grade);
        
        return command;
    }

    public static class BaseUserProfileCommandBean {
        private String subjectCode;
        private String user;
        private String grade;

		public String getSubjectCode() {
            return subjectCode;
        }

        public void setSubjectCode(String subjectCode) {
            this.subjectCode = subjectCode;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }        

        public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

    }

    static class CommandAttributes {
        private static final String USER = "user";
        private static final String SUBJECT = "subject";
        private static final String GRADE = "grade";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }


}
