package org.gooru.ds.user.processor.userprefs.curators;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsCuratorCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPrefsCuratorCommand.class);
    private String user;

    public String getUser() {
        return user;
    }

    public UserPrefsCuratorCommandBean asBean() {
        UserPrefsCuratorCommandBean bean = new UserPrefsCuratorCommandBean();
        bean.user = user;
        return bean;
    }

    static UserPrefsCuratorCommand builder(JsonObject requestBody) {
        UserPrefsCuratorCommand result = UserPrefsCuratorCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    private static UserPrefsCuratorCommand buildFromJsonObject(JsonObject requestBody) {
        UserPrefsCuratorCommand result = new UserPrefsCuratorCommand();
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

    public static class UserPrefsCuratorCommandBean {
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
