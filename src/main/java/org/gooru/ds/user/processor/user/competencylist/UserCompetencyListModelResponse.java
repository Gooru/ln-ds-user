package org.gooru.ds.user.processor.user.competencylist;

import java.util.List;

public class UserCompetencyListModelResponse {
	
    private List<UserCompetencyListModel> competencylist;

    public List<UserCompetencyListModel> getCompetencyList() {
        return competencylist;
    }

    public void setCompetencyList(List<UserCompetencyListModel> competencylist) {
        this.competencylist = competencylist;
    }

}
