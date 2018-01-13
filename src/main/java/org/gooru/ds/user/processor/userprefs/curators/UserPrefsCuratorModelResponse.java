package org.gooru.ds.user.processor.userprefs.curators;

import java.util.List;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsCuratorModelResponse {
    List<UserPrefsCuratorModel> curators;

    public List<UserPrefsCuratorModel> getCurators() {
        return curators;
    }

    public void setCurators(List<UserPrefsCuratorModel> curators) {
        this.curators = curators;
    }
}
