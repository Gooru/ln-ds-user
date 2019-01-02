package org.gooru.ds.user.processor.atc.pvc.course.competency;

import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.processor.atc.pvc.course.competency.model.RouteCalculatorModel;

/**
 * @author ashish.
 */
public interface CompetencyRouteCalculator {

  CompetencyRouteModel calculateCompetencyRoute(RouteCalculatorModel model);


  static CompetencyRouteCalculator build() {
    return new CompetencyRouteCalculatorService(DBICreator.getDbiForCoreDS(),
        DBICreator.getDbiForDefaultDS());
  }
}
