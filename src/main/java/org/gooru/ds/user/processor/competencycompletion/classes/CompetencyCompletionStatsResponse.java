package org.gooru.ds.user.processor.competencycompletion.classes;

import java.util.List;

/**
 * @author mukul@gooru
 *  
 */
public class CompetencyCompletionStatsResponse {
  
  private List<CompetencyCompletionStatsModel> competencyStats;

  public List<CompetencyCompletionStatsModel> getCompetencyStats() {
    return competencyStats;  }

  public void setCompetencyStats(List<CompetencyCompletionStatsModel> competencyStats) {
    this.competencyStats = competencyStats;
  }

}
