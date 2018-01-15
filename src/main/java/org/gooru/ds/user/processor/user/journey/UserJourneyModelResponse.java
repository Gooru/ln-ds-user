package org.gooru.ds.user.processor.user.journey;

import java.util.List;

/**
 * @author mukul@gooru
 */
public class UserJourneyModelResponse {

    private List<UserJourneyModel> journeys;

    public List<UserJourneyModel> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<UserJourneyModel> journeys) {
        this.journeys = journeys;
    }

}
