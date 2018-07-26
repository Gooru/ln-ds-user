package org.gooru.ds.user.processor.initiallearnerprofile;

import java.sql.Timestamp;


/**
 * @author mukul@gooru
 * 
 */
public class InitialLearnerProfileReadModel {

	private String subjectCode;
	private String grade;
	private Integer status;

    private String competencyCode;
    private String profileSource;
    
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCompetencyCode() {
		return competencyCode;
	}
	public void setCompetencyCode(String competencyCode) {
		this.competencyCode = competencyCode;
	}
	public String getProfileSource() {
		return profileSource;
	}
	public void setProfileSource(String profileSource) {
		this.profileSource = profileSource;
	}

}
