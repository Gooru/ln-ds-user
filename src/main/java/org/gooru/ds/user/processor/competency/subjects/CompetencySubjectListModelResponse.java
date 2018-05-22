package org.gooru.ds.user.processor.competency.subjects;

import java.util.List;


/**
 * @author mukul@gooru
 * 
 */
public class CompetencySubjectListModelResponse {
	
    private List<CompetencySubjectListModel> subjects;

    public List<CompetencySubjectListModel> getCompetencySubjectList() {
        return subjects;
    }

    public void setCompetencySubjectList(List<CompetencySubjectListModel> subjects) {
        this.subjects = subjects;
    }


}
