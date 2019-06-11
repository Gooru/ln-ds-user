package org.gooru.ds.user.processor.competencysummary;

import java.util.List;
import org.gooru.ds.user.app.jdbi.DBICreator;


/**
 * @author mukul@gooru
 */
public class UserCompetencySummaryService {

  public CompetencySummaryResponse fetchUserGradeCompetencyStats(
      UserCompetencySummaryCommand command) {
    UserCompetencySummaryFetcher userCompetencySummary =
        new UserCompetencySummaryFetcher(DBICreator.getDbiForDefaultDS(),
            DBICreator.getDbiForCoreDS());
    CompetencySummaryModel competencyStatsModel =
        userCompetencySummary.fetchUserCompetencySummary(command);
    CompetencySummaryResponse stats = new CompetencySummaryResponse();
    stats.setCompetencyStats(competencyStatsModel);
    return stats;
  }

}
