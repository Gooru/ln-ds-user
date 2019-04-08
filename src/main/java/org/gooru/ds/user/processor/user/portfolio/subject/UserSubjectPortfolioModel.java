package org.gooru.ds.user.processor.user.portfolio.subject;

import java.sql.Timestamp;


/**
 * @author mukul@gooru
 */
public class UserSubjectPortfolioModel {

  private String id;
  private String title;
  private String sessionId;
  private String collectionType;
  private String classId;
  private String courseId;
  private String unitId;
  private String lessonId;
  private String contentSource;

  private Long timeSpent;
  private Integer reaction;
  private Double score;
  
  private Timestamp activityTimestamp;
  private String competencyCode;
  private String domainCode;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSessionId() {
    return sessionId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
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

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getCollectionType() {
    return collectionType;
  }

  public void setCollectionType(String collectionType) {
    this.collectionType = collectionType;
  }  

  public Timestamp getActivityTimestamp() {
    return activityTimestamp;
  }

  public void setActivityTimestamp(Timestamp activityTimestamp) {
    this.activityTimestamp = activityTimestamp;
  }

  public String getCompetencyCode() {
    return competencyCode;
  }

  public void setCompetencyCode(String competencyCode) {
    this.competencyCode = competencyCode;
  }
  
  public String getDomainCode() {
    return domainCode;
  }

  public void setDomainCode(String domainCode) {
    this.domainCode = domainCode;
  }

  
}
