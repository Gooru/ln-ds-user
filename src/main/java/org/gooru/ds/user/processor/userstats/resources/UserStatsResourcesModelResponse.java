package org.gooru.ds.user.processor.userstats.resources;

import java.util.List;

import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesModel;


/**
 * @author mukul@gooru
 */
public class UserStatsResourcesModelResponse {

    private List<UserStatsResourcesModel> resources;

    public List<UserStatsResourcesModel> getResources() {
        return resources;
    }

    public void setLessons(List<UserStatsResourcesModel> resources) {
        this.resources = resources;
    }

}
