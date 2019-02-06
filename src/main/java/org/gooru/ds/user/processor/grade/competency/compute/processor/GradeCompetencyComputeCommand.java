package org.gooru.ds.user.processor.grade.competency.compute.processor;

import java.util.UUID;
import org.gooru.ds.user.processor.grade.competency.compute.GradeCompetencyCalculatorModel;

/**
 * @author mukul@gooru
 */
public class GradeCompetencyComputeCommand {

  private Integer gradeId;
  private UUID userId;
  private String subjectCode;

  public Integer getGradeId() {
    return gradeId;
  }

  public UUID getUserId() {
    return userId;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public static GradeCompetencyComputeCommand builder(String userId, Integer gradeId, String subjectCode) {
    GradeCompetencyComputeCommand command = new GradeCompetencyComputeCommand();

    command.userId = UUID.fromString(userId);
    command.gradeId = gradeId;
    command.subjectCode = subjectCode;

    return command;
  }

  GradeCompetencyCalculatorModel gradeCompetencyCalculatorModel() {
    return new GradeCompetencyCalculatorModel(userId, gradeId, subjectCode);
  }

  public static final class CommandAttributes {

    static final String GRADE_ID = "gradeId";
    static final String USER_ID = "userId";
    static final String SUBJECT_CODE = "subjectCode";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }
}
