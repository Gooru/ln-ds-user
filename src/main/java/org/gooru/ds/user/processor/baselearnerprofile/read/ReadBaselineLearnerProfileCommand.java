package org.gooru.ds.user.processor.baselearnerprofile.read;

import java.util.UUID;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.baselearnerprofile.SubjectInferer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author szgooru on 20-Jul-2018
 */
public class ReadBaselineLearnerProfileCommand {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReadBaselineLearnerProfileCommand.class);

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

	public ReadBaselineLearnerProfileCommandBean asBean(ReadBaselineLearnerProfileCommand command) {
		ReadBaselineLearnerProfileCommandBean bean = new ReadBaselineLearnerProfileCommandBean();
		bean.classId = classId;
		bean.courseId = courseId;
		bean.user = user;
		bean.subject = initializeSubjectCode(courseId);
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

	private static String initializeSubjectCode(String courseId) {
		String sc = SubjectInferer.build().inferSubjectForCourse(UUID.fromString(courseId));
		if (sc == null) {
			LOGGER.warn("Not able to find subject code for specified course '{}'", courseId);
			throw new IllegalStateException("Not able to find subject code for specified course " + courseId);
		}

		LOGGER.debug("The Subject Code is" + sc);
		return sc;
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
