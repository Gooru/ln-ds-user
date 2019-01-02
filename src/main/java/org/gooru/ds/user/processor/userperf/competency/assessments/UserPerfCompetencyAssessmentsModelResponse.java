package org.gooru.ds.user.processor.userperf.competency.assessments;

import java.util.List;
import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsModel;

/**
 * @author mukul@gooru
 */
public class UserPerfCompetencyAssessmentsModelResponse {

  private List<UserPerfCompetencyAssessmentsModel> assessments;

  public List<UserPerfCompetencyAssessmentsModel> getAssessments() {
    return assessments;
  }

  public void setAssessments(List<UserPerfCompetencyAssessmentsModel> assessments) {
    this.assessments = assessments;
  }


}
