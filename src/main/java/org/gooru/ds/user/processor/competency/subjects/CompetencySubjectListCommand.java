package org.gooru.ds.user.processor.competency.subjects;

import org.gooru.ds.user.app.components.AppConfiguration;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 * 
 */
public class CompetencySubjectListCommand {	
	
    private String classificationType;
    private Integer offset;
    private Integer limit;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CompetencySubjectListCommand.class);

    public String getClassificationType() {
        return classificationType;
    }
   

    static CompetencySubjectListCommand builder(JsonObject requestBody) {
        CompetencySubjectListCommand result = CompetencySubjectListCommand.buildFromJsonObject(requestBody);
        result.validate();
        return result;
    }

    public CompetencySubjectListCommandBean asBean() {
        CompetencySubjectListCommandBean bean = new CompetencySubjectListCommandBean();
        bean.classificationType = classificationType;
        bean.offset = offset;
        bean.limit = limit;
        
        return bean;
    }

    private static CompetencySubjectListCommand buildFromJsonObject(JsonObject requestBody) {
        CompetencySubjectListCommand result = new CompetencySubjectListCommand();
        result.classificationType = requestBody.getString(CommandAttributes.CLASSIFICATION_TYPE);        
        Integer offset = getAsInt(requestBody, CommandAttributes.OFFSET);
        Integer limit = getAsInt(requestBody, CommandAttributes.LIMIT);
        setOffsetAndLimit(offset, limit, result);

        return result;
    }

    private void validate() {
        
        if (classificationType == null) {
            LOGGER.info("Classification Type not provided with the request");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid classficationType");
        }
    }

    private static void setOffsetAndLimit(Integer offset, Integer limit, CompetencySubjectListCommand command) {
        if (offset == null) {
            command.offset = AppConfiguration.getInstance().getDefaultOffset();
        } else {        	
            if (offset >= 0) {
                command.offset = offset;
            } else {
                command.offset = null;
            }
        }
        if (limit == null) {
            command.limit = AppConfiguration.getInstance().getDefaultLimit();
        } else {
            Integer maxLimit = AppConfiguration.getInstance().getDefaultMaxLimit();
            if (limit > 0 && limit <= maxLimit) {            	
                command.limit = limit;
            } else if (limit > maxLimit) {
                command.limit = maxLimit;
            } else {
                command.limit = null;
            }
        }
    }

    private static Integer getAsInt(JsonObject requestBody, String key) {
        String value = requestBody.getString(key);
        Integer result = null;
        if (key != null) {
            try {
                result = Integer.valueOf(value);
            } catch (NumberFormatException e) {
                LOGGER.info("Invalid number format for {}", key);
                result = null;
            }
        }
        return result;
    }

    
    public static class CompetencySubjectListCommandBean {
        private String classificationType;        
		private Integer offset;
        private Integer limit;

        public String getClassificationType() {
            return classificationType;
        }

        public void setClassificationType(String classificationType) {
            this.classificationType = classificationType;
        }
        
        public Integer getOffset() {
			return offset;
		}

		public void setOffset(Integer offset) {
			this.offset = offset;
		}

		public Integer getLimit() {
			return limit;
		}

		public void setLimit(Integer limit) {
			this.limit = limit;
		}

    }

    static class CommandAttributes {
        private static final String CLASSIFICATION_TYPE = "classificationType";
        private static final String OFFSET = "offset";
        private static final String LIMIT = "limit";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }


}
