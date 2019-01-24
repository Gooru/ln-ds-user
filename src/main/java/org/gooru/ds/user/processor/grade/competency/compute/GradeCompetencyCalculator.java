package org.gooru.ds.user.processor.grade.competency.compute;

import org.gooru.ds.user.app.jdbi.DBICreator;

public interface GradeCompetencyCalculator {

  GradeCompetencyModel calculateGradeCompetency(GradeCompetencyCalculatorModel model);

  static GradeCompetencyCalculator build() {
    return new GradeCompetencyCalculatorService(DBICreator.getDbiForCoreDS(),
        DBICreator.getDbiForDefaultDS());
  }
}
