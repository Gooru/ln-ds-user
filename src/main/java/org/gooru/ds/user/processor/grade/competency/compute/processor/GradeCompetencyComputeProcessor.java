package org.gooru.ds.user.processor.grade.competency.compute.processor;

import java.util.List;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
public class GradeCompetencyComputeProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(GradeCompetencyComputeProcessor.class);
  // TODO: Optimize the code following this, we have a chance to eliminate pseudo UserId
  // We need to get the Competency Map right from the earthline, and hence the pseudo userId
  private static final String userUUIDStr = "00000000-0000-0000-0000-000000000000";
  private GradeCompetencyComputeService gradeCompetencyService =
      new GradeCompetencyComputeService(DBICreator.getDbiForDefaultDS(), DBICreator.getDbiForCoreDS());

  @SuppressWarnings({"rawtypes", "unchecked"})
  public List<String> getGradeCompetency(Integer studGradeId, String subjectCode) {

    GradeCompetencyComputeCommand command =
        GradeCompetencyComputeCommand.builder(userUUIDStr, studGradeId, subjectCode);
    List competencyList = gradeCompetencyService.calculateGradeCompetencies(command);
    return (competencyList != null) ? competencyList : null;
  }
}
