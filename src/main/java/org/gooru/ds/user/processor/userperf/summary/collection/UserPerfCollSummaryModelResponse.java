package org.gooru.ds.user.processor.userperf.summary.collection;

import java.util.List;

import org.gooru.ds.user.processor.userperf.summary.collection.UserPerfCollSummaryModel;


/**
 * @author mukul@gooru
 */
public class UserPerfCollSummaryModelResponse {
	
	
    List<UserPerfCollSummaryModel> resources;

    public List<UserPerfCollSummaryModel> getResources() {
        return resources;
    }

    public void setResources(List<UserPerfCollSummaryModel> resources) {
        this.resources = resources;
}



}
