package org.gooru.ds.user.processor.userprefs.providers;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsProviderCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPrefsProviderCommand.class);
    private String user;

    public String getUser() {
        return user;
    }

    public UserPrefsProviderCommandBean asBean() {
        UserPrefsProviderCommandBean bean = new UserPrefsProviderCommandBean();
        bean.user = user;
        return bean;
    }

    static UserPrefsProviderCommand builder(JsonObject requestBody) {
        UserPrefsProviderCommand result = UserPrefsProviderCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    private static UserPrefsProviderCommand buildFromJsonObject(JsonObject requestBody) {
        UserPrefsProviderCommand result = new UserPrefsProviderCommand();
        result.user = requestBody.getString(CommandAttributes.USER);
        return result;
    }

    private void validate() {
        if (user == null) {
            LOGGER.info("User not provided for request");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "User not provided for request");
        }
    }

    public static class UserPrefsProviderCommandBean {
        private String user;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }

    static class CommandAttributes {
        private static final String USER = "user";
    }

}
