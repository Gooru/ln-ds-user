package org.gooru.ds.user.processor.grade.boundary;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author szgooru on 19-Sep-2018
 */
public class GradeBoundaryListCommand {

	private int grade;

	private static final Logger LOGGER = LoggerFactory.getLogger(GradeBoundaryListCommand.class);

	static GradeBoundaryListCommand builder(JsonObject requestBody) {
		GradeBoundaryListCommand command = buildFromJson(requestBody);
		command.validate();
		return command;
	}

	private void validate() {
		if (grade <= 0) {
			LOGGER.debug("invalid grade sent in request");
			throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid grade");
		}

		LOGGER.info("GRADE:={}", grade);
	}

	private static GradeBoundaryListCommand buildFromJson(JsonObject requestBody) {
		GradeBoundaryListCommand command = new GradeBoundaryListCommand();
		String strGrade = requestBody.getString(CommandAttributes.GRADE);
		try {
		command.grade = Integer.parseInt(strGrade);
		} catch (NumberFormatException nfe) {
			LOGGER.debug("invalid format of the grade");
			throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid grade format");
		}
		
		return command;
	}
	
	public GradeBoundaryListCommandBean asBean() {
		GradeBoundaryListCommandBean bean = new GradeBoundaryListCommandBean();
		bean.grade = grade;
		return bean;
	}

	public static class GradeBoundaryListCommandBean {
		private int grade;

		public int getGrade() {
			return grade;
		}

		public void setGrade(int grade) {
			this.grade = grade;
		}
	}

	static class CommandAttributes {
		private static final String GRADE = "gradeId";

		private CommandAttributes() {
			throw new AssertionError();
		}
	}

}
