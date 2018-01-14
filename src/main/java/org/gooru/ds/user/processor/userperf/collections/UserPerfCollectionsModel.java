package org.gooru.ds.user.processor.userperf.collections;


/**
 * @author mukul@gooru
 */
public class UserPerfCollectionsModel {
	
    private String id;    
    private String title;
    private String sessionId;
    private Integer timeSpent;
    private Integer reaction;
    private Double score;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(Integer timeSpent) {
		this.timeSpent = timeSpent;
	}
	public Integer getReaction() {
		return reaction;
	}
	public void setReaction(Integer reaction) {
		this.reaction = reaction;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}


}