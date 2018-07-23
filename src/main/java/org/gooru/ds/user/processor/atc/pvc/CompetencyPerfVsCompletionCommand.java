package org.gooru.ds.user.processor.atc.pvc;

import java.util.UUID;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class CompetencyPerfVsCompletionCommand {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyPerfVsCompletionCommand.class);

	private String subjectCode;
	private String grade;
	private String classId;
	private String courseId;
	
    public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}


	static CompetencyPerfVsCompletionCommand builder(JsonObject requestBody) {
        CompetencyPerfVsCompletionCommand command = buildFromJsonObject(requestBody);
        command.courseId = requestBody.getString(CommandAttributes.COURSE_ID);
        command.classId = requestBody.getString(CommandAttributes.CLASS_ID);        
        command.subjectCode = requestBody.getString(CommandAttributes.SUBJECT_CODE);
        command.grade = requestBody.getString(CommandAttributes.GRADE);

        command.validate();
        return command;
    }

    public LearnerProfileReadCommandBean asBean() {
        LearnerProfileReadCommandBean bean = new LearnerProfileReadCommandBean();        
        bean.subjectCode = subjectCode;
        bean.grade = grade;
        bean.classId = classId;
        bean.courseId = courseId;

        return bean;
    }

    private void validate() {
        if (classId == null) {
            LOGGER.debug("Invalid ClassId");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid classId");
        } else if (courseId == null) {
            LOGGER.debug("Invalid courseId");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid courseId");
        } else if (subjectCode == null) {
            LOGGER.debug("Invalid subjectCode");
            throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST, "Invalid subjectCode");
        }
    }

    private static CompetencyPerfVsCompletionCommand buildFromJsonObject(JsonObject requestBody) {
        CompetencyPerfVsCompletionCommand command = new CompetencyPerfVsCompletionCommand();
        
        return command;
    }


    public static class LearnerProfileReadCommandBean {
		private String subjectCode;
		private String grade;
		private String classId;
		private String courseId;
		
		public String getSubjectCode() {
			return subjectCode;
		}
		public void setSubjectCode(String subjectCode) {
			this.subjectCode = subjectCode;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		
		public String getClassId() {
			return classId;
		}

		public void setClassId(String classId) {
			this.classId = classId;
		}

		public String getCourseId() {
			return courseId;
		}

		public void setCourseId(String courseId) {
			this.courseId = courseId;
		}


    }

    static class CommandAttributes {
        private static final String SUBJECT_CODE = "subjectCode";
        private static final String COURSE_ID = "courseId";
        private static final String CLASS_ID = "classId";
        private static final String GRADE = "grade";

        private CommandAttributes() {
            throw new AssertionError();
        }
    }





}
