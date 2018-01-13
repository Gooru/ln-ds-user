package org.gooru.ds.user.processor.userstats.curator;

import java.util.List;

/**
 * @author ashish on 13/1/18.
 */
class UserStatsCuratorModelResponse {
    List<UserStatsCuratorModel> curators;

    public List<UserStatsCuratorModel> getCurators() {
        return curators;
    }

    public void setCurators(List<UserStatsCuratorModel> curators) {
        this.curators = curators;
    }
}
