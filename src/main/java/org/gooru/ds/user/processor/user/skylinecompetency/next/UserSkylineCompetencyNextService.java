package org.gooru.ds.user.processor.user.skylinecompetency.next;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gooru.ds.user.constants.StatusConstants;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserSkylineCompetencyNextService {

	private final UserSkylineCompetencyNextDao userCompetencySkylineDao;
	private DomainCompetencyMap domainCompetencyMap;
	private DomainCompetencyMap orderedDomainCompetencyMatrixMap;
	private List<CompetencyModel> completedCompetencies;
    private List<String> domains;
    private List<String> allDomains;
    //Map<String = domain, CompetencyModel>
    private Map<String, CompetencyModel> competencySkylineMap;    
    //private Map<String, CompetencyModel> domainCompetencyNextMap;
    private Map<String, String> domainCompetencyNextMap;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSkylineCompetencyNextService.class);
    		
	UserSkylineCompetencyNextService(DBI dbi) {
		this.userCompetencySkylineDao = dbi.onDemand(UserSkylineCompetencyNextDao.class);
	}
	
    private void createDestinationCompetencyMap() {
        domainCompetencyMap = DomainCompetencyMap.create(completedCompetencies);
    }

    private void computeSkyline() {
        Objects.requireNonNull(domainCompetencyMap);

        domains = Collections.unmodifiableList(new ArrayList<>(domainCompetencyMap.getDomains()));
        competencySkylineMap = new HashMap<>();

        for (String domainCode : domains) {
            List<CompetencyModel> competencies = domainCompetencyMap.getCompetenciesForDomain(domainCode);
            competencySkylineMap.put(domainCode, competencies.get(competencies.size() - 1));
        }
    }
    
    private void orderDomainCompetencyMatrix(List<CompetencyModel> dcmList) {
        orderedDomainCompetencyMatrixMap = DomainCompetencyMap.create(dcmList);
    }

	Map<String, String> fetchUserSkyline(UserSkylineCompetencyNextCommand command) {
		
		final List<CompetencyModel> userCompetencyStatusModels = userCompetencySkylineDao
				.fetchUserCompetencyStatus(command.asBean());
		
		if (userCompetencyStatusModels.isEmpty()) {
			return new HashMap<>();
		} else {
			completedCompetencies = userCompetencyStatusModels.stream()
					.filter(model -> model.getStatus() >= StatusConstants.COMPLETED).collect(Collectors.toList());
			LOGGER.info("Creating Destination Competency Map");
			createDestinationCompetencyMap();
			LOGGER.info("Creating Completed Competency Skyline");
			computeSkyline();
			LOGGER.info("Ordering DCM");
			//Order the competencies by progression for each Domain
			orderDomainCompetencyMatrix(userCompetencyStatusModels);
			
			domainCompetencyNextMap = new HashMap<>();
			//Map<String, UserDomainCompetencyMatrixModel> competencySkylineMap
			for (Map.Entry<String, CompetencyModel> entry : competencySkylineMap.entrySet()) {
				String completedDomainCode = entry.getKey();
				LOGGER.info("Get Domain Code from CompetencySkyline Map" + entry.getKey());
				CompetencyModel compModel = entry.getValue();
				LOGGER.info("Get Competency Code from CompetencySkylineMap" + compModel.getCompetencyCode());
				
				String completedCompetencyCode = compModel.getCompetencyCode();
				
				List<CompetencyModel> orderedCompetencies = 
						orderedDomainCompetencyMatrixMap.getCompetenciesForDomain(completedDomainCode);
				
				List <String>competencyCodeList = new ArrayList<>();
				for (CompetencyModel oc : orderedCompetencies) {
					LOGGER.info("Get Ordered Competency Codes from DCM " + oc.getCompetencyCode());
					competencyCodeList.add(oc.getCompetencyCode());					
				}
				
				int indexofCompetency = competencyCodeList.indexOf(completedCompetencyCode);
				LOGGER.info("Index of the completed code " + indexofCompetency);
				

				if(indexofCompetency < (competencyCodeList.size() - 1)) {
					String competencyNextCode = competencyCodeList.get(indexofCompetency + 1);					
		            if (!domainCompetencyNextMap.containsKey(completedDomainCode)) {	            	
		            	domainCompetencyNextMap.put(completedDomainCode, competencyNextCode);
		            }					
				} else if (indexofCompetency == competencyCodeList.size()) {
					//If this is the topmost competency then the response should be null
		            if (!domainCompetencyNextMap.containsKey(completedDomainCode)) {	            	
		            	domainCompetencyNextMap.put(completedDomainCode, null);
		            }
				}
	        }
			//Stuff remaining domains with the Starting Competency
			allDomains = Collections.unmodifiableList(new ArrayList<>(orderedDomainCompetencyMatrixMap.getDomains()));
			for (String dom : allDomains) {
	            if (!domainCompetencyNextMap.containsKey(dom)) {	            	
					List<CompetencyModel> orderedCompetencies = 
							orderedDomainCompetencyMatrixMap.getCompetenciesForDomain(dom);
					String compCode = orderedCompetencies.get(0).getCompetencyCode();
					domainCompetencyNextMap.put(dom, compCode);
	            }				
			}
			
			return domainCompetencyNextMap;
		}
	}
}
