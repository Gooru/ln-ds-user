package org.gooru.ds.user.processor.atc.recompute;

import java.util.List;


/**
 * @author mukul@gooru
 */
public class CompetencyStatsResponse {	

    private List<CompetencyStatsModel> competencyStats;

    public List<CompetencyStatsModel> getCompetencyStats() {
        return competencyStats;
    }

    public void setCompetencyStats(List<CompetencyStatsModel> competencyStats) {
        this.competencyStats = competencyStats;
    }

}
