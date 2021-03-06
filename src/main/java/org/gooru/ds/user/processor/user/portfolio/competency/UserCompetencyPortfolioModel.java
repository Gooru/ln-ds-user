package org.gooru.ds.user.processor.user.portfolio.competency;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.processor.user.portfolio.content.items.UserModel;


/**
 * @author mukul@gooru
 */
public class UserCompetencyPortfolioModel implements Serializable {
  
  private final static long serialVersionUID = 3637780095721677754L;

  private String id;
  private String title;
  private String type;
  private String subType;
  private String learningObjective;
  private String thumbnail;
  private Map<String, Object> taxonomy;
  private List<String> gutCodes;
  private Integer questionCount;
  private Integer resourceCount;
  private Integer taskCount;
  private UserModel owner;
  private Float efficacy;
  private Float engagement;
  private Float relevance;

  private Long timeSpent;
  private Double score;

  private String sessionId;
  private String contentSource;
  private Timestamp activityTimestamp;
  private Timestamp updatedAt;
  private Map<String, Object> masterySummary;
  
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    if (subType == null) {
      subType = "NA";
    }
    this.subType = subType;
  }

  public String getLearningObjective() {
    return learningObjective;
  }

  public void setLearningObjective(String learningObjective) {
    this.learningObjective = learningObjective;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public Map<String, Object> getTaxonomy() {
    return taxonomy;
  }

  public void setTaxonomy(Map<String, Object> taxonomy) {
    this.taxonomy = taxonomy;
  }

  public Long getTimeSpent() {
    return timeSpent;
  }

  public void setTimeSpent(Long timeSpent) {
    this.timeSpent = timeSpent;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getContentSource() {
    return contentSource;
  }

  public void setContentSource(String contentSource) {
    this.contentSource = contentSource;
  }

  public Timestamp getActivityTimestamp() {
    return activityTimestamp;
  }

  public void setActivityTimestamp(Timestamp activityTimestamp) {
    this.activityTimestamp = activityTimestamp;
  }
  
  
  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }

  public Integer getResourceCount() {
    return resourceCount;
  }

  public void setResourceCount(Integer resourceCount) {
    this.resourceCount = resourceCount;
  }

  public Integer getTaskCount() {
    return taskCount;
  }

  public void setTaskCount(Integer taskCount) {
    this.taskCount = taskCount;
  }

  public Map<String, Object> getMasterySummary() {
    return masterySummary;
  }

  public void setMasterySummary(Map<String, Object> masterySummary) {
    this.masterySummary = masterySummary;
  }

  public List<String> getGutCodes() {
    return gutCodes;
  }

  public void setGutCodes(List<String> gutCodes) {
    this.gutCodes = gutCodes;
  }

  public UserModel getOwner() {
    return owner;
  }

  public void setOwner(UserModel owner) {
    this.owner = owner;
  }

  public Float getEfficacy() {
    return efficacy;
  }

  public void setEfficacy(Float efficacy) {
    this.efficacy = efficacy;
  }

  public Float getEngagement() {
    return engagement;
  }

  public void setEngagement(Float engagement) {
    this.engagement = engagement;
  }

  public Float getRelevance() {
    return relevance;
  }

  public void setRelevance(Float relevance) {
    this.relevance = relevance;
  }
}
