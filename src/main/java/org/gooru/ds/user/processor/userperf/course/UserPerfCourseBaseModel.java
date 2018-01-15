package org.gooru.ds.user.processor.userperf.course;

/**
 * @author mukul@gooru
 */
public class UserPerfCourseBaseModel {

    private String classId;
    private String classCode;
    private String classTitle;
    private String courseId;
    private String courseTitle;
    private Long courseAsmtTimeSpent;
    private Float courseAsmtScore;
    private Long courseCollTimeSpent;
    private String unitId;
    private String unitTitle;
    private Long unitAsmtTimeSpent;
    private Float unitAsmtScore;
    private Long unitCollTimeSpent;
    private Integer courseAssessmentsComplete;
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

    public Long getCourseAsmtTimeSpent() {
        return courseAsmtTimeSpent;
    }

    public void setCourseAsmtTimeSpent(Long courseAsmtTimeSpent) {
        this.courseAsmtTimeSpent = courseAsmtTimeSpent;
    }

    public Float getCourseAsmtScore() {
        return courseAsmtScore;
    }

    public void setCourseAsmtScore(Float courseAsmtScore) {
        this.courseAsmtScore = courseAsmtScore;
    }

    public Long getCourseCollTimeSpent() {
        return courseCollTimeSpent;
    }

    public void setCourseCollTimeSpent(Long courseCollTimeSpent) {
        this.courseCollTimeSpent = courseCollTimeSpent;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public Long getUnitAsmtTimeSpent() {
        return unitAsmtTimeSpent;
    }

    public void setUnitAsmtTimeSpent(Long unitAsmtTimeSpent) {
        this.unitAsmtTimeSpent = unitAsmtTimeSpent;
    }

    public Float getUnitAsmtScore() {
        return unitAsmtScore;
    }

    public void setUnitAsmtScore(Float unitAsmtScore) {
        this.unitAsmtScore = unitAsmtScore;
    }

    public Long getUnitCollTimeSpent() {
        return unitCollTimeSpent;
    }

    public void setUnitCollTimeSpent(Long unitCollTimeSpent) {
        this.unitCollTimeSpent = unitCollTimeSpent;
    }

    public Integer getCourseAssessmentsComplete() {
        return courseAssessmentsComplete;
    }

    public void setCourseAssessmentsComplete(Integer courseAssessmentsComplete) {
        this.courseAssessmentsComplete = courseAssessmentsComplete;
    }

    public Integer getTotalAssessments() {
        return totalAssessments;
    }

    public void setTotalAssessments(Integer totalAssessments) {
        this.totalAssessments = totalAssessments;
    }

}
