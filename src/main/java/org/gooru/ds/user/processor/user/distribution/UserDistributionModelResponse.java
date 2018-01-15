package org.gooru.ds.user.processor.user.distribution;

import java.util.List;

/**
 * @author ashish on 10/1/18.
 */
class UserDistributionModelResponse {
    private List<UserDistributionModel> subjects;
    private List<UserDistributionModel> geoLocations;

    public List<UserDistributionModel> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<UserDistributionModel> subjects) {
        this.subjects = subjects;
    }

    public List<UserDistributionModel> getGeoLocations() {
        return geoLocations;
    }

    public void setGeoLocations(List<UserDistributionModel> geoLocations) {
        this.geoLocations = geoLocations;
    }
}
