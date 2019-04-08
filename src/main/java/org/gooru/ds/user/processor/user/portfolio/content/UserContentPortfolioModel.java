package org.gooru.ds.user.processor.user.portfolio.content;

import java.sql.Timestamp;

public class UserContentPortfolioModel {

  private String collectionId;
  private String title;
  private String sessionId;
  private String collectionType;
  private String classId;
  private String courseId;
  private String unitId;
  private String lessonId;
  private String contentSource;
  
  private Boolean isGraded;  
  private Long timeSpent;
  private Integer reaction;
  private Integer views;
  private Double score;

  private Integer pathId;
  private String pathType;  
  private Timestamp activityTimestamp;
  
  public String getCollectionId() {
    return collectionId;
  }
  public void setCollectionId(String collectionId) {
    this.collectionId = collectionId;
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
  public String getCollectionType() {
    return collectionType;
  }
  public void setCollectionType(String collectionType) {
    this.collectionType = collectionType;
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
  public String getUnitId() {
    return unitId;
  }
  public void setUnitId(String unitId) {
    this.unitId = unitId;
  }
  public String getLessonId() {
    return lessonId;
  }
  public void setLessonId(String lessonId) {
    this.lessonId = lessonId;
  }
  public String getContentSource() {
    return contentSource;
  }
  public void setContentSource(String contentSource) {
    this.contentSource = contentSource;
  }
  public Boolean getIsGraded() {
    return isGraded;
  }
  public void setIsGraded(Boolean isGraded) {
    this.isGraded = isGraded;
  }
  public Long getTimeSpent() {
    return timeSpent;
  }
  public void setTimeSpent(Long timeSpent) {
    this.timeSpent = timeSpent;
  }
  public Integer getReaction() {
    return reaction;
  }
  public void setReaction(Integer reaction) {
    this.reaction = reaction;
  }
  public Integer getViews() {
    return views;
  }
  public void setViews(Integer views) {
    this.views = views;
  }
  public Double getScore() {
    return score;
  }
  public void setScore(Double score) {
    this.score = score;
  }
  public Integer getPathId() {
    return pathId;
  }
  public void setPathId(Integer pathId) {
    this.pathId = pathId;
  }
  public String getPathType() {
    return pathType;
  }
  public void setPathType(String pathType) {
    this.pathType = pathType;
  }
  public Timestamp getActivityTimestamp() {
    return activityTimestamp;
  }
  public void setActivityTimestamp(Timestamp activityTimestamp) {
    this.activityTimestamp = activityTimestamp;
  }

}
