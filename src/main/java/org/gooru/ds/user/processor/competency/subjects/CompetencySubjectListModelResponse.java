package org.gooru.ds.user.processor.competency.subjects;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonRootName;


/**
 * @author mukul@gooru
 * 
 */

// @JsonRootName(value = "subjects")
public class CompetencySubjectListModelResponse {

  private List<CompetencySubjectListModel> subjects;

  public List<CompetencySubjectListModel> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<CompetencySubjectListModel> subjects) {
    this.subjects = subjects;
  }

}


