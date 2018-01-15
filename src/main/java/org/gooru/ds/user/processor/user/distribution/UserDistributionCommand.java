package org.gooru.ds.user.processor.user.distribution;

import java.util.Arrays;
import java.util.List;

import org.gooru.ds.user.app.data.ActiveDuration;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 */
class UserDistributionCommand {
    private Integer zoom;
    private String geoLocation;
    private String activeDuration;
    private String subject;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDistributionCommand.class);

    private static final List<Integer> VALID_ZOOM = Arrays.asList(1);

    public Integer getZoom() {
        return zoom;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public String getActiveDuration() {
        return activeDuration;
    }

    public String getSubject() {
        return subject;
    }

    static UserDistributionCommand builder(JsonObject requestBody) {
        UserDistributionCommand result = UserDistributionCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public UserDistributionCommandBean asBean() {
        UserDistributionCommandBean bean = new UserDistributionCommandBean();
        bean.subject = subject;
        bean.activeDuration = activeDuration;
        bean.geoLocation = geoLocation;
        bean.zoom = zoom;
        return bean;
    }

    private static UserDistributionCommand buildFromJsonObject(JsonObject requestBody) {
        UserDistributionCommand result = new UserDistributionCommand();
        String zoomValue = requestBody.getString(CommandAttributes.ZOOM);
        if (zoomValue != null) {
            try {
                result.zoom = Integer.valueOf(zoomValue);
            } catch (NumberFormatException e) {
                LOGGER.info("Invalid number format for zoom");
                result.zoom = null;
            }
        }
        result.activeDuration = requestBody.getString(CommandAttributes.ACTIVE_DURATION);
        result.geoLocation = requestBody.getString(CommandAttributes.GEOLOCATION);
        result.subject = requestBody.getString(CommandAttributes.SUBJECT);
        return result;
    }

    private void validate() {
        if (zoom == null || !VALID_ZOOM.contains(zoom)) {
            LOGGER.info("Invalid zoom provided for request");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid zoom");
        }
        if (activeDuration != null && !ActiveDuration.isValidDuration(activeDuration)) {
            LOGGER.info("Invalid active duration provided for request");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid active duration");
        }
        if (geoLocation != null && subject != null) {
            LOGGER.info("Both geoLocation and subject filter should not be provided for request");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
                "Both geolocation and subject filters can't be applied");
        }
    }

    public static class UserDistributionCommandBean {
        private Integer zoom;
        private String geoLocation;
        private String activeDuration;
        private String subject;

        public Integer getZoom() {
            return zoom;
        }

        public void setZoom(Integer zoom) {
            this.zoom = zoom;
        }

        public String getGeoLocation() {
            return geoLocation;
        }

        public void setGeoLocation(String geoLocation) {
            this.geoLocation = geoLocation;
        }

        public String getActiveDuration() {
            return activeDuration;
        }

        public void setActiveDuration(String activeDuration) {
            this.activeDuration = activeDuration;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }

    static class CommandAttributes {
        private static final String ZOOM = "zoom";
        private static final String GEOLOCATION = "geoLocation";
        private static final String ACTIVE_DURATION = "activeDuration";
        private static final String SUBJECT = "subject";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
