package org.gooru.ds.user.processor.userperf.competency.collections;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

public class UserPerfCompetencyCollectionsCommand {

    private String competencyCode;
    private String gutCode;
    private String user;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCompetencyCollectionsCommand.class);

    public String getcompetencyCode() {
        return competencyCode;
    }
    
    public String getGutCode() {
    	return gutCode;
    }

    public String getUserId() {
        return user;
    }

    static UserPerfCompetencyCollectionsCommand builder(JsonObject requestBody) {
        UserPerfCompetencyCollectionsCommand result = UserPerfCompetencyCollectionsCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserPerfCompetencyCollectionsCommandBean asBean() {
        UserPerfCompetencyCollectionsCommandBean bean = new UserPerfCompetencyCollectionsCommandBean();
        bean.user = user;
        bean.competencyCode = competencyCode;
        bean.gutCode = gutCode;
        return bean;
    }

    private static UserPerfCompetencyCollectionsCommand buildFromJsonObject(JsonObject requestBody) {
        UserPerfCompetencyCollectionsCommand result = new UserPerfCompetencyCollectionsCommand();

        result.competencyCode = requestBody.getString(CommandAttributes.COMPETENCY_CODE);
        result.gutCode = requestBody.getString(CommandAttributes.GUT_CODE);
        result.user = requestBody.getString(CommandAttributes.USER_ID);
        return result;
    }

    private void validate() {

        if (user == null) {
            LOGGER.info("User not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "User not provided for request");
        }
        
        if (competencyCode == null && gutCode == null) {
        	LOGGER.info("Competency and Gut code not provided");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Either Competency or gut code should be provided");
        }

    }

    public static class UserPerfCompetencyCollectionsCommandBean {
        private String competencyCode;
        private String gutCode;
		private String user;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getCompetencyCode() {
            return competencyCode;
        }

        public void setCompetencyCode(String competencyCode) {
            this.competencyCode = competencyCode;
        }

		public String getGutCode() {
			return gutCode;
		}

		public void setGutCode(String gutCode) {
			this.gutCode = gutCode;
		}
    }

    static class CommandAttributes {
        private static final String COMPETENCY_CODE = "competencyCode";
        private static final String GUT_CODE = "gutCode";
        private static final String USER_ID = "user";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
