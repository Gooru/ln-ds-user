package org.gooru.ds.user.processor.user.journey;

/**
 * @author mukul@gooru
 */
public class UserJourneyModel {

    private String classId;
    private String classCode;
    private String classTitle;
    private String courseId;
    private String courseTitle;
    private Float averageScore;
    private Long timeSpent;
    private Integer assessmentsCompleted;
    private Integer totalAssessments;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Integer getAssessmentsCompleted() {
        return assessmentsCompleted;
    }

    public void setAssessmentsCompleted(Integer assessmentsCompleted) {
        this.assessmentsCompleted = assessmentsCompleted;
    }

    public Integer getTotalAssessments() {
        return totalAssessments;
    }

    public void setTotalAssessments(Integer totalAssessments) {
        this.totalAssessments = totalAssessments;
    }

}
