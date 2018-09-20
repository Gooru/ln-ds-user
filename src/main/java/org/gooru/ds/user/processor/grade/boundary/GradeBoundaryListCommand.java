package org.gooru.ds.user.processor.grade.boundary;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author szgooru on 19-Sep-2018
 */
public class GradeBoundaryListCommand {

	private String grade;
	private String subject;

	private static final Logger LOGGER = LoggerFactory.getLogger(GradeBoundaryListCommand.class);

	static GradeBoundaryListCommand builder(JsonObject requestBody) {
		GradeBoundaryListCommand command = buildFromJson(requestBody);
		command.validate();
		return command;
	}

	private void validate() {
		if (ValidatorUtils.isNullOrEmpty(grade)) {
			LOGGER.debug("invalid grade sent in request");
			throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid grade");
		}

		if (ValidatorUtils.isNullOrEmpty(subject)) {
			LOGGER.debug("invalid subject sent in request");
			throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid subject");
		}
		
		LOGGER.info("GRADE:={} || SUBJECT:={}", grade, subject);
	}

	private static GradeBoundaryListCommand buildFromJson(JsonObject requestBody) {
		GradeBoundaryListCommand command = new GradeBoundaryListCommand();
		command.grade = requestBody.getString(CommandAttributes.GRADE);
		command.subject = requestBody.getString(CommandAttributes.SUBJECT);
		return command;
	}
	
	public GradeBoundaryListCommandBean asBean() {
		GradeBoundaryListCommandBean bean = new GradeBoundaryListCommandBean();
		bean.grade = grade;
		bean.subject = subject;
		return bean;
	}

	public static class GradeBoundaryListCommandBean {
		private String grade;
		private String subject;

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}
	}

	static class CommandAttributes {
		private static final String GRADE = "grade";
		private static final String SUBJECT = "subject";

		private CommandAttributes() {
			throw new AssertionError();
		}
	}

}
