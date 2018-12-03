	package org.gooru.ds.user.processor.user.skylinecompetency.next;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.gooru.ds.user.processor.atc.pvc.competency.Competency;
import org.gooru.ds.user.processor.atc.pvc.competency.CompetencyMap;
import org.gooru.ds.user.processor.atc.pvc.competency.DomainCode;


/**
 * @author mukul@gooru
 */
public class DomainCompetencyMapImpl implements DomainCompetencyMap {
	
	private final List<String> domainsList;
	private List<String> competencyCodeList;
	//Map<String = Domain, List<CompetencyModel>>
	private final Map<String, List<CompetencyModel>> domainCodeCompetencyMap;
	
    DomainCompetencyMapImpl(List<CompetencyModel> competencies) {
        domainCodeCompetencyMap = new HashMap<>(competencies.size());

        for (CompetencyModel competency : competencies) {
            List<CompetencyModel> competenciesForDomain = domainCodeCompetencyMap.get(competency.getDomainCode());
            if (competenciesForDomain == null) {
                competenciesForDomain = new ArrayList<>();
                competenciesForDomain.add(competency);
                domainCodeCompetencyMap.put(competency.getDomainCode(), competenciesForDomain);
            } else {
                competenciesForDomain.add(competency);
            }
        }
        
        domainsList = Collections.unmodifiableList(new ArrayList<>(domainCodeCompetencyMap.keySet()));
        createDomainCompetencyMap();
    }

    private void createDomainCompetencyMap() {
        for (String domainCode : domainsList) {
            List<CompetencyModel> competencies = domainCodeCompetencyMap.get(domainCode);
            Set<CompetencyModel> competencySet = new HashSet<>(competencies);
            competencies = new ArrayList<>(competencySet);
            competencies.sort(new CompetencySorter());
            domainCodeCompetencyMap.put(domainCode, competencies);
        }
    }
    
    @Override
    public List<CompetencyModel> getCompetenciesForDomain(String domainCode) {
        return Collections.unmodifiableList(domainCodeCompetencyMap.get(domainCode));
    }
    
    @Override
    public List<String> getDomains() {
        return domainsList;
    }

    @Override
    public List<String> getDomainCompetencyCodes(String domainCode) {
    	List<CompetencyModel> DomainCompetencyList = Collections.unmodifiableList(domainCodeCompetencyMap.get(domainCode));
    	competencyCodeList = new ArrayList<>();
    	for (CompetencyModel dcList : DomainCompetencyList) {
    		competencyCodeList.add(dcList.getCompetencyCode());
    	}
    	return competencyCodeList;
    }


}
