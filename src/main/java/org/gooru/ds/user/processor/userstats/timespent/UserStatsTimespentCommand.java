package org.gooru.ds.user.processor.userstats.timespent;

import org.gooru.ds.user.app.data.ActiveDuration;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 12/1/18.
 */
class UserStatsTimespentCommand {
    private String activeDuration;
    private String user;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsTimespentCommand.class);

    public String getActiveDuration() {
        return activeDuration;
    }

    public String getUser() {
        return user;
    }

    static UserStatsTimespentCommand builder(JsonObject requestBody) {
        UserStatsTimespentCommand result = UserStatsTimespentCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserStatsTimespentCommandBean asBean() {
        UserStatsTimespentCommandBean bean = new UserStatsTimespentCommandBean();
        bean.user = user;
        bean.activeDuration = activeDuration;
        return bean;
    }

    private static UserStatsTimespentCommand buildFromJsonObject(JsonObject requestBody) {
        UserStatsTimespentCommand result = new UserStatsTimespentCommand();

        result.activeDuration = requestBody.getString(CommandAttributes.ACTIVE_DURATION);
        result.user = requestBody.getString(CommandAttributes.USER);
        return result;
    }

    private void validate() {
        if (activeDuration != null && !ActiveDuration.isValidDuration(activeDuration)) {
            LOGGER.info("Invalid active duration provided for request");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid active duration");
        }
        if (user == null) {
            LOGGER.info("User not provided for request");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "User not provided for request");
        }
    }

    public static class UserStatsTimespentCommandBean {
        private String activeDuration;
        private String user;

        public String getActiveDuration() {
            return activeDuration;
        }

        public void setActiveDuration(String activeDuration) {
            this.activeDuration = activeDuration;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }

    static class CommandAttributes {
        private static final String ACTIVE_DURATION = "activeDuration";
        private static final String USER = "user";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
