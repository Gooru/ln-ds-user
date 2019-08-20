package org.gooru.ds.user.processor.content.portfolio.items;

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
  private Long timespent;
  private Double score;
  private Double maxScore;
  private String createdAt;
  private String gradingStatus;
  private Map<String, Object> taxonomy;
  private Integer questionCount;
  private Integer resourceCount;
  private Integer taskCount;
  private String status;

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
    return "presentation";
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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
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

}
