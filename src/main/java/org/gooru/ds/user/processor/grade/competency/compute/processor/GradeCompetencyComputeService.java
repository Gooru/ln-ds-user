package org.gooru.ds.user.processor.grade.competency.compute.processor;

import java.util.List;
import org.gooru.ds.user.processor.grade.competency.compute.CompetencyExtractor;
import org.gooru.ds.user.processor.grade.competency.compute.GradeCompetencyCalculator;
import org.gooru.ds.user.processor.grade.competency.compute.GradeCompetencyModel;
import org.skife.jdbi.v2.DBI;

/**
 * @author mukul@gooru
 */
public class GradeCompetencyComputeService {

  private final DBI dbiForDefaultDS;
  private final DBI dbiForCoreDS;

  GradeCompetencyComputeService(DBI dbiForCoreDS, DBI dbiForDefaultDS) {
    this.dbiForDefaultDS = dbiForDefaultDS;
    this.dbiForCoreDS = dbiForCoreDS;
  }

  List<String> calculateGradeCompetencies(GradeCompetencyComputeCommand command) {
    GradeCompetencyCalculator gradeCompetencyCalculator = GradeCompetencyCalculator.build();
    GradeCompetencyModel gradeCompetencyModel = gradeCompetencyCalculator
        .calculateGradeCompetency(command.gradeCompetencyCalculatorModel());    
    if (gradeCompetencyModel != null) {
      List<String> competencyList = createCompetencyList(gradeCompetencyModel);
      return competencyList;
    } else {
      return null;
    }
  }

  private List<String> createCompetencyList(GradeCompetencyModel gradeCompetencyModel) {
    List<String> competencyList = new CompetencyExtractor().getComptencyList(gradeCompetencyModel);
    return competencyList;
  }
}
