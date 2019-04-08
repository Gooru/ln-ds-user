
package org.gooru.ds.user.processor.user.portfolio.domain.response.model;


/**
 * @author mukul@gooru
 */
import java.io.Serializable;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "sessionId",
    "collectionType",
    "classId",
    "courseId",
    "unitId",
    "lessonId",
    "contentSource",
    "timeSpent",
    "reaction",
    "score",
    "activityTimestamp"
})
public class Collection implements Serializable
{

    @JsonProperty("id")
    private String id;
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
    @JsonProperty("timeSpent")
    private Object timeSpent;
    @JsonProperty("reaction")
    private Object reaction;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("activityTimestamp")
    private Timestamp activityTimestamp;
    private final static long serialVersionUID = 1816969044838868621L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Collection() {
    }

    /**
     * 
     * @param reaction
     * @param collectionType
     * @param classId
     * @param activityTimestamp
     * @param score
     * @param courseId
     * @param timeSpent
     * @param id
     * @param title
     * @param lessonId
     * @param sessionId
     * @param contentSource
     * @param unitId
     */
    public Collection(String id, String title, String sessionId, String collectionType, String classId, String courseId, String unitId, String lessonId, String contentSource, Object timeSpent, Object reaction, Double score, Timestamp activityTimestamp) {
        super();
        this.id = id;
        this.title = title;
        this.sessionId = sessionId;
        this.collectionType = collectionType;
        this.classId = classId;
        this.courseId = courseId;
        this.unitId = unitId;
        this.lessonId = lessonId;
        this.contentSource = contentSource;
        this.timeSpent = timeSpent;
        this.reaction = reaction;
        this.score = score;
        this.activityTimestamp = activityTimestamp;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Collection withId(String id) {
        this.id = id;
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

    public Collection withTitle(String title) {
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

    public Collection withSessionId(String sessionId) {
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

    public Collection withCollectionType(String collectionType) {
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

    public Collection withClassId(String classId) {
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

    public Collection withCourseId(String courseId) {
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

    public Collection withUnitId(String unitId) {
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

    public Collection withLessonId(String lessonId) {
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

    public Collection withContentSource(String contentSource) {
        this.contentSource = contentSource;
        return this;
    }

    @JsonProperty("timeSpent")
    public Object getTimeSpent() {
        return timeSpent;
    }

    @JsonProperty("timeSpent")
    public void setTimeSpent(Object timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Collection withTimeSpent(Object timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

    @JsonProperty("reaction")
    public Object getReaction() {
        return reaction;
    }

    @JsonProperty("reaction")
    public void setReaction(Object reaction) {
        this.reaction = reaction;
    }

    public Collection withReaction(Object reaction) {
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

    public Collection withScore(Double score) {
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

    public Collection withActivityTimestamp(Timestamp activityTimestamp) {
        this.activityTimestamp = activityTimestamp;
        return this;
    }

}