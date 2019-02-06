package org.gooru.ds.user.processor.atc.recompute;

import java.util.List;
import org.gooru.ds.user.app.jdbi.DBICreator;


/**
 * @author mukul@gooru
 */
public class ATCCompetencyStatsService {

  public CompetencyStatsResponse fetchUserGradeCompetencyStats(
      ATCCompetencyStatsCommand command) {
    ATCCompetencyStatsFetcher gradeBasedPerfVsCompletionCalculator =
        new ATCCompetencyStatsFetcher(DBICreator.getDbiForDefaultDS(),
            DBICreator.getDbiForCoreDS());
    List<CompetencyStatsModel> competencyStatsModel =
        gradeBasedPerfVsCompletionCalculator.fetchGradeBasedUserPerformanceCompletion(command);
    CompetencyStatsResponse stats = new CompetencyStatsResponse();
    stats.setCompetencyStats(competencyStatsModel);
    return stats;
  }

}
