package org.gooru.ds.user.processor.userprofile;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 17/1/18.
 */
class UserProfileCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileCommand.class);
    private String user;

    public String getUser() {
        return user;
    }

    public UserProfileCommandBean asBean() {
        UserProfileCommandBean bean = new UserProfileCommandBean();
        bean.user = user;
        return bean;
    }

    static UserProfileCommand builder(JsonObject requestBody) {
        UserProfileCommand result = UserProfileCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    private static UserProfileCommand buildFromJsonObject(JsonObject requestBody) {
        UserProfileCommand result = new UserProfileCommand();
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

    public static class UserProfileCommandBean {
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

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
