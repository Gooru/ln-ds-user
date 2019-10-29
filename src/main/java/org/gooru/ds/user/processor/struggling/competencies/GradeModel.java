
package org.gooru.ds.user.processor.struggling.competencies;

/**
 * @author szgooru Created On 18-Oct-2019
 */
public class GradeModel {

  private Long id;
  private String grade;
  private Integer gradeSeq;
  private String description;
  private String fwCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public Integer getGradeSeq() {
    return gradeSeq;
  }

  public void setGradeSeq(Integer gradeSeq) {
    this.gradeSeq = gradeSeq;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFwCode() {
    return fwCode;
  }

  public void setFwCode(String fwCode) {
    this.fwCode = fwCode;
  }

}
