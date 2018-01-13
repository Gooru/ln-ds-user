package org.gooru.ds.user.processor.userstats.provider;

import java.util.List;

/**
 * @author ashish on 13/1/18.
 */
class UserStatsProviderModelResponse {
    List<UserStatsProviderModel> providers;

    public List<UserStatsProviderModel> getProviders() {
        return providers;
    }

    public void setProviders(List<UserStatsProviderModel> providers) {
        this.providers = providers;
    }
}
