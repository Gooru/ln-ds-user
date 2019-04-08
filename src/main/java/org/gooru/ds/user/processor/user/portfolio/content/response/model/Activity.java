
package org.gooru.ds.user.processor.user.portfolio.content.response.model;

import java.io.Serializable;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "collectionId",
    "title",
    "sessionId",
    "collectionType",
    "classId",
    "courseId",
    "unitId",
    "lessonId",
    "contentSource",
    "pathId",
    "pathType",
    "isGraded",
    "timeSpent",
    "reaction",
    "score",
    "activityTimestamp"
})
public class Activity implements Serializable
{

    @JsonProperty("collectionId")
    private String collectionId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("sessionId")
    private String sessionId;
    @JsonProperty("collectionType")
    private String collectionType;
    @JsonProperty("classId")
    private String classId;
    @JsonProperty("courseId")
    private String courseId;
    @JsonProperty("unitId")
    private String unitId;
    @JsonProperty("lessonId")
    private String lessonId;
    @JsonProperty("contentSource")
    private String contentSource;
    @JsonProperty("pathId")
    private Integer pathId;
    @JsonProperty("pathType")
    private String pathType;
    @JsonProperty("isGraded")
    private Boolean isGraded;
    @JsonProperty("timeSpent")
    private Integer timeSpent;
    @JsonProperty("reaction")
    private Integer reaction;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("activityTimestamp")
    private Timestamp activityTimestamp;
    private final static long serialVersionUID = -6855521826367465415L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Activity() {
    }

    /**
     * 
     * @param reaction
     * @param collectionType
     * @param collectionId
     * @param classId
     * @param pathId
     * @param activityTimestamp
     * @param score
     * @param courseId
     * @param timeSpent
     * @param pathType
     * @param title
     * @param lessonId
     * @param sessionId
     * @param isGraded
     * @param contentSource
     * @param unitId
     */
    public Activity(String collectionId, String title, String sessionId, String collectionType, 
        String classId, String courseId, String unitId, String lessonId, String contentSource, 
        Integer pathId, String pathType, Boolean isGraded, Integer timeSpent, Integer reaction, 
        Double score, Timestamp activityTimestamp) {
        super();
        this.collectionId = collectionId;
        this.title = title;
        this.sessionId = sessionId;
        this.collectionType = collectionType;
        this.classId = classId;
        this.courseId = courseId;
        this.unitId = unitId;
        this.lessonId = lessonId;
        this.contentSource = contentSource;
        this.pathId = pathId;
        this.pathType = pathType;
        this.isGraded = isGraded;
        this.timeSpent = timeSpent;
        this.reaction = reaction;
        this.score = score;
        this.activityTimestamp = activityTimestamp;
    }

    @JsonProperty("collectionId")
    public String getCollectionId() {
        return collectionId;
    }

    @JsonProperty("collectionId")
    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public Activity withCollectionId(String collectionId) {
        this.collectionId = collectionId;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Activity withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("sessionId")
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("sessionId")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Activity withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @JsonProperty("collectionType")
    public String getCollectionType() {
        return collectionType;
    }

    @JsonProperty("collectionType")
    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public Activity withCollectionType(String collectionType) {
        this.collectionType = collectionType;
        return this;
    }

    @JsonProperty("classId")
    public String getClassId() {
        return classId;
    }

    @JsonProperty("classId")
    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Activity withClassId(String classId) {
        this.classId = classId;
        return this;
    }

    @JsonProperty("courseId")
    public String getCourseId() {
        return courseId;
    }

    @JsonProperty("courseId")
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Activity withCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    @JsonProperty("unitId")
    public String getUnitId() {
        return unitId;
    }

    @JsonProperty("unitId")
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Activity withUnitId(String unitId) {
        this.unitId = unitId;
        return this;
    }

    @JsonProperty("lessonId")
    public String getLessonId() {
        return lessonId;
    }

    @JsonProperty("lessonId")
    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public Activity withLessonId(String lessonId) {
        this.lessonId = lessonId;
        return this;
    }

    @JsonProperty("contentSource")
    public String getContentSource() {
        return contentSource;
    }

    @JsonProperty("contentSource")
    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }

    public Activity withContentSource(String contentSource) {
        this.contentSource = contentSource;
        return this;
    }

    @JsonProperty("pathId")
    public Integer getPathId() {
        return pathId;
    }

    @JsonProperty("pathId")
    public void setPathId(Integer pathId) {
        this.pathId = pathId;
    }

    public Activity withPathId(Integer pathId) {
        this.pathId = pathId;
        return this;
    }

    @JsonProperty("pathType")
    public String getPathType() {
        return pathType;
    }

    @JsonProperty("pathType")
    public void setPathType(String pathType) {
        this.pathType = pathType;
    }

    public Activity withPathType(String pathType) {
        this.pathType = pathType;
        return this;
    }

    @JsonProperty("isGraded")
    public Boolean getIsGraded() {
        return isGraded;
    }

    @JsonProperty("isGraded")
    public void setIsGraded(Boolean isGraded) {
        this.isGraded = isGraded;
    }

    public Activity withIsGraded(Boolean isGraded) {
        this.isGraded = isGraded;
        return this;
    }

    @JsonProperty("timeSpent")
    public Integer getTimeSpent() {
        return timeSpent;
    }

    @JsonProperty("timeSpent")
    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Activity withTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

    @JsonProperty("reaction")
    public Integer getReaction() {
        return reaction;
    }

    @JsonProperty("reaction")
    public void setReaction(Integer reaction) {
        this.reaction = reaction;
    }

    public Activity withReaction(Integer reaction) {
        this.reaction = reaction;
        return this;
    }

    @JsonProperty("score")
    public Double getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Double score) {
        this.score = score;
    }

    public Activity withScore(Double score) {
        this.score = score;
        return this;
    }

    @JsonProperty("activityTimestamp")
    public Timestamp getActivityTimestamp() {
        return activityTimestamp;
    }

    @JsonProperty("activityTimestamp")
    public void setActivityTimestamp(Timestamp activityTimestamp) {
        this.activityTimestamp = activityTimestamp;
    }

    public Activity withActivityTimestamp(Timestamp activityTimestamp) {
        this.activityTimestamp = activityTimestamp;
        return this;
    }

}