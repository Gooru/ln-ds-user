package org.gooru.ds.user.processor.competencymatrixcoordinates;

import java.util.Arrays;
import java.util.List;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 17/1/18.
 */
class CompetencyMatrixCoordinatesCommand {
    private static final String COURSES = "courses";
    private static final String DOMAINS = "domains";
    private String subject;
    private String filter;
    private List<String> VALID_FILTER = Arrays.asList(COURSES, DOMAINS);
    private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyMatrixCoordinatesCommand.class);

    public String getSubject() {
        return subject;
    }

    public String getFilter() {
        return filter;
    }

    public boolean isFilteredByCourses() {
        return COURSES.equals(filter);
    }

    public boolean isFilteredByDomains() {
        return DOMAINS.equals(filter);
    }

    public boolean isUnfiltered() {
        return filter == null;
    }

    static CompetencyMatrixCoordinatesCommand builder(JsonObject requestBody) {
        CompetencyMatrixCoordinatesCommand result = buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public CompetencyMatrixCoordinatesCommandBean asBean() {
        CompetencyMatrixCoordinatesCommandBean bean = new CompetencyMatrixCoordinatesCommandBean();
        bean.filter = filter;
        bean.subject = subject;
        return bean;
    }

    private void validate() {
        if (subject == null) {
            LOGGER.debug("Provided null subject");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid subject");
        } else if (filter != null) {
            if (!VALID_FILTER.contains(filter)) {
                LOGGER.debug("Provided invalid filter");
                throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid filter");
            }
        }
    }

    private static CompetencyMatrixCoordinatesCommand buildFromJsonObject(JsonObject requestBody) {
        CompetencyMatrixCoordinatesCommand command = new CompetencyMatrixCoordinatesCommand();
        command.filter = requestBody.getString(CommandAttributes.FILTER);
        command.subject = requestBody.getString(CommandAttributes.SUBJECT);
        return command;
    }

    public static class CompetencyMatrixCoordinatesCommandBean {
        private String subject;
        private String filter;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }
    }

    static class CommandAttributes {
        private static final String FILTER = "filter";
        private static final String SUBJECT = "subject";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }

}
