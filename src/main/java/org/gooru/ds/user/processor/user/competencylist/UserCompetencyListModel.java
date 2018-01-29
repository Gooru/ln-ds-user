package org.gooru.ds.user.processor.user.competencylist;

public class UserCompetencyListModel {
	
    private String competencyCode;
	private String displayCode;
    private String title;
    private String frameworkCode;
    private String status;
    
    public String getCompetencyCode() {
		return competencyCode;
	}
	public void setCompetencyCode(String competencyCode) {
		this.competencyCode = competencyCode;
	}
	public String getDisplayCode() {
		return displayCode;
	}
	public void setDisplayCode(String displayCode) {
		this.displayCode = displayCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFrameworkCode() {
		return frameworkCode;
	}
	public void setFrameworkCode(String frameworkCode) {
		this.frameworkCode = frameworkCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
