package org.gooru.ds.user.processor.atc.pvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class CompetencyCompletionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyCompletionService.class);
	private static final int INFERRED = 2;
	private static final int ASSERTED = 3;
	private static final int COMPLETED = 4;
	private static final int INPROGRESS = 1;
	private final CompetencyCompletionDao competencyCompletionDao;
	private Integer completionCount;

	CompetencyCompletionService(DBI dbi) {
		this.competencyCompletionDao = dbi.onDemand(CompetencyCompletionDao.class);
	}
	
	JsonObject fetchUserCompetencyStatus(String user, String subjectCode, List<String> competencyCodes, 
			Integer month, Integer year) {
		
		JsonObject counts = new JsonObject();
		completionCount = 0;
		List<CompetencyCompletionModel> userCompetencyCompletionModels = new ArrayList<>();
		
		if (competencyCodes != null && !competencyCodes.isEmpty() && (month == null || year == null)) {
			userCompetencyCompletionModels = competencyCompletionDao.
					fetchCompetencyCompletion(user, subjectCode, PGArrayUtils.convertFromListStringToSqlArrayOfString(competencyCodes));
		} else if (competencyCodes != null && !competencyCodes.isEmpty() && (month != null && year != null)){
			userCompetencyCompletionModels = competencyCompletionDao.fetchCompetencyCompletionMonthBased(user, subjectCode, 
					PGArrayUtils.convertFromListStringToSqlArrayOfString(competencyCodes), month, year);
		}

		if (userCompetencyCompletionModels.isEmpty()) {
			LOGGER.info("The User competency Status model is empty");
			return new JsonObject();
		} else {
			List<CompetencyCompletionModel> completed = userCompetencyCompletionModels.stream()
					.filter(model -> model.getStatus() >= COMPLETED).collect(Collectors.toList());
			completionCount = completed.size();
			LOGGER.debug("Completed/Mastered Competencies " + completionCount);
			
			Map<String, Map<String, CompetencyCompletionModel>> completedCompMap = new HashMap<>();
			completed.forEach(model -> {
				String domain = model.getDomainCode();
				String compCode = model.getCompetencyCode();
				LOGGER.debug("Completed/Mastered Competencies Code" + compCode);

				if (completedCompMap.containsKey(domain)) {
					Map<String, CompetencyCompletionModel> competencies = completedCompMap.get(domain);
					competencies.put(compCode, model);
					completedCompMap.put(domain, competencies);
				} else {
					Map<String, CompetencyCompletionModel> competencies = new HashMap<>();
					competencies.put(compCode, model);
					completedCompMap.put(domain, competencies);
				}
			});

			userCompetencyCompletionModels.forEach(model -> {
				String domainCode = model.getDomainCode();
				int sequence = model.getCompetencySeq();
				int status = model.getStatus();

				if (completedCompMap.containsKey(domainCode)) {
					Map<String, CompetencyCompletionModel> competencies = completedCompMap.get(domainCode);
					for (Map.Entry<String, CompetencyCompletionModel> entry : competencies.entrySet()) {
						CompetencyCompletionModel compModel = entry.getValue();
						int compSeq = compModel.getCompetencySeq();

						if (sequence < compSeq && status < ASSERTED && model.getStatus() != INFERRED) {							
							model.setStatus(INFERRED);
							completionCount++;
						}
					}
				}
			});
			
			counts.put("completionCount", completionCount);
			LOGGER.debug("Completed/Mastered/Inferred " + completionCount);
			
			List<CompetencyCompletionModel> inProgress = userCompetencyCompletionModels.stream()
			    .filter(model -> model.getStatus() == INPROGRESS).collect(Collectors.toList());
			counts.put("inprogressCount", inProgress.size());
			LOGGER.debug("InProgress Competencies " + inProgress.size());
			
			return counts;
		}
	}

}
