package org.gooru.ds.user.processor.userperf.summary.assessment;

import java.util.List;

/**
 * @author mukul@gooru
 */
public class UserPerfAsmtSummaryModelResponse {

    private List<UserPerfAsmtSummaryModel> resources;

    public List<UserPerfAsmtSummaryModel> getResources() {
        return resources;
    }

    public void setResources(List<UserPerfAsmtSummaryModel> resources) {
        this.resources = resources;
    }

}
