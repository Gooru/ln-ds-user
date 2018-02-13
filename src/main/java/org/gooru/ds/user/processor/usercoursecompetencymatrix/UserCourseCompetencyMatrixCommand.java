package org.gooru.ds.user.processor.usercoursecompetencymatrix;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 13/2/18.
 */
class UserCourseCompetencyMatrixCommand {
    private String subject;
    private String user;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCourseCompetencyMatrixCommand.class);

    public String getSubject() {
        return subject;
    }

    public String getUser() {
        return user;
    }

    static UserCourseCompetencyMatrixCommand builder(JsonObject requestBody) {
        UserCourseCompetencyMatrixCommand result = buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserCourseCompetencyMatrixCommandBean asBean() {
        UserCourseCompetencyMatrixCommandBean bean = new UserCourseCompetencyMatrixCommandBean();
        bean.user = user;
        bean.subject = subject;
        return bean;
    }

    private void validate() {
        if (subject == null) {
            LOGGER.debug("Provided null subject");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid subject");
        } else if (user == null) {
            LOGGER.debug("Provided null user");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid user");
        }
    }

    private static UserCourseCompetencyMatrixCommand buildFromJsonObject(JsonObject requestBody) {
        UserCourseCompetencyMatrixCommand command = new UserCourseCompetencyMatrixCommand();
        command.user = requestBody.getString(CommandAttributes.USER);
        command.subject = requestBody.getString(CommandAttributes.SUBJECT);
        return command;
    }

    public static class UserCourseCompetencyMatrixCommandBean {
        private String subject;
        private String user;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }

    static class CommandAttributes {
        private static final String USER = "user";
        private static final String SUBJECT = "subject";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
