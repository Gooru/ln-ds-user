package org.gooru.ds.user.processor.baselearnerprofile;


/**
 * @author mukul@gooru
 * 
 */
public class LearnerProfileReadModel {

	private String subjectCode;
	private Integer status;
    private String userId;
    private String competencyCode;

	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompetencyCode() {
		return competencyCode;
	}
	public void setCompetencyCode(String competencyCode) {
		this.competencyCode = competencyCode;
	}
    
}
