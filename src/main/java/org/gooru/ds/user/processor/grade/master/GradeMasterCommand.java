package org.gooru.ds.user.processor.grade.master;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author szgooru on 21-Sep-2018
 */
public class GradeMasterCommand {
	private String subject;

	private static final Logger LOGGER = LoggerFactory.getLogger(GradeMasterCommand.class);

	static GradeMasterCommand builder(JsonObject requestBody) {
		GradeMasterCommand command = buildFromJson(requestBody);
		command.validate();
		return command;
	}

	private void validate() {
		if (ValidatorUtils.isNullOrEmpty(subject)) {
			LOGGER.debug("invalid subject sent in request");
			throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid subject");
		}

		LOGGER.info("SUBJECT:={}", subject);
	}

	private static GradeMasterCommand buildFromJson(JsonObject requestBody) {
		GradeMasterCommand command = new GradeMasterCommand();
		command.subject = requestBody.getString(CommandAttributes.SUBJECT);
		return command;
	}

	public GradeMasterCommandBean asBean() {
		GradeMasterCommandBean bean = new GradeMasterCommandBean();
		bean.subject = subject;
		return bean;
	}

	public static class GradeMasterCommandBean {
		private String subject;

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}
	}

	static class CommandAttributes {
		private static final String SUBJECT = "subject";

		private CommandAttributes() {
			throw new AssertionError();
		}
	}

}
