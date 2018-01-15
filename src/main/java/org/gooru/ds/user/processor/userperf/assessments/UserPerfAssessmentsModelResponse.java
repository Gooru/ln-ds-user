package org.gooru.ds.user.processor.userperf.assessments;

import java.util.List;

/**
 * @author mukul@gooru
 */
public class UserPerfAssessmentsModelResponse {
	
	
    List<UserPerfAssessmentsModel> assessments;

    public List<UserPerfAssessmentsModel> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<UserPerfAssessmentsModel> assessments) {
        this.assessments = assessments;
}

}
