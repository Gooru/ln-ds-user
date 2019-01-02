package org.gooru.ds.user.processor.competencymatrixcoordinates;

/**
 * @author ashish on 17/1/18.
 */
class CompetencyMatrixCoordinatesCourseModel {
  private String courseCode;
  private String courseName;
  private Integer courseSeq;

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public Integer getCourseSeq() {
    return courseSeq;
  }

  public void setCourseSeq(Integer courseSeq) {
    this.courseSeq = courseSeq;
  }
}
