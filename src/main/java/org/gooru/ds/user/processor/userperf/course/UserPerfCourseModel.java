package org.gooru.ds.user.processor.userperf.course;

import java.util.List;

/**
 * @author mukul@gooru
 */
class UserPerfCourseModel {

  private String classId;
  private String classCode;
  private String classTitle;
  private String courseId;
  private String courseTitle;
  private Long courseAsmtTimeSpent;
  private Float courseAsmtScore;
  private Long courseCollTimeSpent;
  private Integer courseAssessmentsComplete;
  private Integer totalAssessments;
  private List<UserPerfUnitModel> units;

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

  public List<UserPerfUnitModel> getUnits() {
    return units;
  }

  public void setUnits(List<UserPerfUnitModel> units) {
    this.units = units;
  }

}
