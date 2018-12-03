package org.gooru.ds.user.processor.user.skylinecompetency.next;

import java.util.Comparator;

public class CompetencySorter implements Comparator<CompetencyModel> {
	
    @Override
    public int compare(CompetencyModel comp1, CompetencyModel comp2) {        
        return comp1.getCompetencySeq() - comp2.getCompetencySeq();
    }

}
