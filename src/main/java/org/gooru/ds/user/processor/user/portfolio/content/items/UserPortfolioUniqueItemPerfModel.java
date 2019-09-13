package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author renuka
 */
public class UserPortfolioUniqueItemPerfModel {
  
  private String NA = "NA";

  private String id;
  private String type;
  private String subType;
  private String title;
  private String learningObjective;
  private String thumbnail;
  private Map<String, Object> taxonomy;
  private List<String> gutCodes;
  private Integer questionCount;
  private Integer resourceCount;
  private Integer taskCount;
  
  private Long timespent;
  private Double score;
  private Double maxScore;
  private String sessionId;
  private String contentSource;
  private Timestamp activityTimestamp;
  private String gradingStatus;
  private String status;
  private Map<String, Object> masterySummary;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
      subType = NA;
    }
    this.subType = subType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title == null) {
      title = NA;
    }
    this.title = title;
  }

  public Long getTimespent() {
    return timespent;
  }

  public void setTimespent(Long timespent) {
    this.timespent = timespent;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public Double getMaxScore() {
    return maxScore;
  }

  public void setMaxScore(Double maxScore) {
    this.maxScore = maxScore;
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

  public String getGradingStatus() {
    return gradingStatus;
  }

  public void setGradingStatus(String gradingStatus) {
    this.gradingStatus = gradingStatus;
  }

  public Map<String, Object> getTaxonomy() {
    return taxonomy;
  }

  public void setTaxonomy(Map<String, Object> taxonomy) {
    this.taxonomy = taxonomy;
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

  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    if (questionCount == null) {
      questionCount = 0;
    }
    this.questionCount = questionCount;
  }

  public Integer getResourceCount() {
    return resourceCount;
  }

  public void setResourceCount(Integer resourceCount) {
    if (resourceCount == null) {
      resourceCount = 0;
    }
    this.resourceCount = resourceCount;
  }

  public Integer getTaskCount() {
    return taskCount;
  }

  public void setTaskCount(Integer taskCount) {
    this.taskCount = taskCount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

}
