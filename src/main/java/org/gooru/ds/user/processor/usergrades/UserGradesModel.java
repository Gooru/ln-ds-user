package org.gooru.ds.user.processor.usergrades;

/**
 * @author ashish on 17/1/18.
 */
class UserGradesModel {
  private String subjectCode;
  private String subjectName;
  private String grade;

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }
}
