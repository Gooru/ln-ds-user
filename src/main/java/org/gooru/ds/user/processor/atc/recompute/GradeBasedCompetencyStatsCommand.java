package org.gooru.ds.user.processor.atc.recompute;

import java.util.UUID;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class GradeBasedCompetencyStatsCommand {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GradeBasedCompetencyStatsCommand.class);

	private String subjectCode;
	private String classId;
	private Integer month;
    private Integer year;
	
    public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
    public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}


	static GradeBasedCompetencyStatsCommand builder(JsonObject requestBody) {
        GradeBasedCompetencyStatsCommand command = buildFromJsonObject(requestBody);
        command.classId = requestBody.getString(CommandAttributes.CLASS_ID);        
        command.subjectCode = requestBody.getString(CommandAttributes.SUBJECT_CODE);
        command.year = requestBody.getString(CommandAttributes.YEAR) != null ? 
        		Integer.valueOf(requestBody.getString(CommandAttributes.YEAR)) : null;
        command.month = requestBody.getString(CommandAttributes.MONTH) != null ? 
        		Integer.valueOf(requestBody.getString(CommandAttributes.MONTH)) : null;

        command.validate();
        return command;
    }

	private void validate() {
		if (classId == null) {
			LOGGER.debug("Invalid ClassId");
			throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid classId");
		}
		else if (subjectCode == null) {
			LOGGER.debug("Invalid subjectCode");
			throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid subjectCode");
		}
		}

    private static GradeBasedCompetencyStatsCommand buildFromJsonObject(JsonObject requestBody) {
        GradeBasedCompetencyStatsCommand command = new GradeBasedCompetencyStatsCommand();        
        return command;
    }

    static class CommandAttributes {
        private static final String SUBJECT_CODE = "subjectCode";
        private static final String CLASS_ID = "classId";
        private static final String YEAR = "year";
        private static final String MONTH = "month";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }





}
