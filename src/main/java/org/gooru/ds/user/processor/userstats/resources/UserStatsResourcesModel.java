package org.gooru.ds.user.processor.userstats.resources;


/**
 * @author mukul@gooru
 */
public class UserStatsResourcesModel {

	private String resourceId;
    private String resourceTitle;
    private Long pathId;
	private Long resourceTimeSpent;
	    
    public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceTitle() {
		return resourceTitle;
	}
	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}
    public Long getPathId() {
		return pathId;
	}
	public void setPathId(Long pathId) {
		this.pathId = pathId;
	}
	public Long getResourceTimeSpent() {
		return resourceTimeSpent;
	}
	public void setResourceTimeSpent(Long resourceTimeSpent) {
		this.resourceTimeSpent = resourceTimeSpent;
	}

}
