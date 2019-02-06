package org.gooru.ds.user.processor.grade.competency.compute;

import java.util.UUID;

/**
 * @author mukul@gooru
 */
public class GradeCompetencyCalculatorModel {
  private UUID userId;
  private Integer gradeId;  
  private String subjectCode;

  public UUID getUserId() {
    return userId;
  }

  public Integer getGradeId() {
    return gradeId;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public GradeCompetencyCalculatorModel(UUID userId, Integer gradeId, String subjectCode) {
    this.userId = userId;
    this.gradeId = gradeId;
    this.subjectCode = subjectCode;
  }

}
