package org.gooru.ds.user.processor.userstats.journeys;

/**
 * @author ashish on 12/1/18.
 */
class UserStatsJourneysModel {
    private Integer independentJourneys;
    private Integer classJourneys;

    public Integer getIndependentJourneys() {
        return independentJourneys;
    }

    public void setIndependentJourneys(Integer independentJourneys) {
        this.independentJourneys = independentJourneys;
    }

    public Integer getClassJourneys() {
        return classJourneys;
    }

    public void setClassJourneys(Integer classJourneys) {
        this.classJourneys = classJourneys;
    }
}
