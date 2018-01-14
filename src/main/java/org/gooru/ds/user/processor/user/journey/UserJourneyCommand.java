package org.gooru.ds.user.processor.user.journey;


import java.util.ArrayList;
import java.util.List;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserJourneyCommand {
	
    private List<String> classId;
    private List<String> courseId;
    private String user;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserJourneyCommand.class);

    public List<String> getClassId() {
        return classId;
    }

    public List<String> getCourseId() {
        return courseId;
    }

    public String getUser() {
        return user;
    }

    static UserJourneyCommand builder(JsonObject requestBody) {
        UserJourneyCommand result = UserJourneyCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserJourneyCommandBean asBean() {
        UserJourneyCommandBean bean = new UserJourneyCommandBean();
        bean.user = user;
        bean.classId = classId;
        bean.courseId = courseId;
        return bean;
    }

    private static UserJourneyCommand buildFromJsonObject(JsonObject requestBody) {
        UserJourneyCommand result = new UserJourneyCommand();

        //result.classId = requestBody.getString(CommandAttributes.CLASS_ID);
        JsonArray classIds = requestBody.getJsonArray(CommandAttributes.CLASS_IDS);        
        List<String> clsIds = new ArrayList<>(classIds.size());
        for (Object s : classIds) {
          clsIds.add(s.toString());
        }
         
        JsonArray courseIds = requestBody.getJsonArray(CommandAttributes.COURSE_IDS);        
        List<String> couIds = new ArrayList<>(courseIds.size());
        for (Object s : courseIds) {
          couIds.add(s.toString());
        }
        
        result.classId = clsIds;
        result.courseId = couIds;
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

    public static class UserJourneyCommandBean {
        private List<String> classId;
        private List<String> courseId;
        private String user;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
        
        public List<String> getClassId() {
            return classId;
        }

        public void setClassId(List<String> classId) {
            this.classId = classId;
        }
        
        public List<String> getCourseId() {
            return courseId;
        }

        public void setCourseId(List<String> courseId) {
            this.courseId = courseId;
        }
    }

    static class CommandAttributes {
        private static final String CLASS_IDS = "classIds";
        private static final String COURSE_IDS = "courseIds";
        private static final String USER = "user";
    }

}
