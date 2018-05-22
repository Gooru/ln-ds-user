package org.gooru.ds.user.processor.userdomaincompetencymatrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/2/18.
 */
class UserDomainCompetencyMatrixService {

	private final UserDomainCompetencyMatrixDao userCompetencyMatrixDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDomainCompetencyMatrixService.class);

	private final static Pattern HYPHEN_PATTERN = Pattern.compile("-");
	
	private static final int INFERRED = 2;
	private static final int COMPLETED = 4;
	private static final int MASTERED = 5;

	UserDomainCompetencyMatrixService(DBI dbi) {
		this.userCompetencyMatrixDao = dbi.onDemand(UserDomainCompetencyMatrixDao.class);
	}

	UserDomainCompetencyMatrixModelResponse fetchUserDomainCompetencyMatrix(UserDomainCompetencyMatrixCommand command) {
		final List<UserDomainCompetencyMatrixModel> userCompetencyMatrixModels = userCompetencyMatrixDao
				.fetchUserDomainCompetencyMatrix(command.asBean());
		if (userCompetencyMatrixModels.isEmpty()) {
			return new UserDomainCompetencyMatrixModelResponse();
		} else {

			List<UserDomainCompetencyMatrixModel> completed = userCompetencyMatrixModels.stream()
					.filter(model -> model.getStatus() >= 4).collect(Collectors.toList());
			
			Map<String, Map<String, UserDomainCompetencyMatrixModel>> competencyMatrixMap = new HashMap<>();
			userCompetencyMatrixModels.forEach(model -> {
				String domain = model.getDomainCode();
				String compCode = model.getCompetencyCode();
				
				
				if (competencyMatrixMap.containsKey(domain)) {
					Map<String, UserDomainCompetencyMatrixModel> competencies = competencyMatrixMap.get(domain);
					competencies.put(compCode, model);
					competencyMatrixMap.put(domain, competencies);
				} else {
					Map<String, UserDomainCompetencyMatrixModel> competencies = new HashMap<>();
					competencies.put(compCode, model);
					competencyMatrixMap.put(domain, competencies);
				}
			});
			
			LOGGER.debug("completed or mastered #:{}", completed.size());
			for (UserDomainCompetencyMatrixModel obj : completed) {
				String domainCode = HYPHEN_PATTERN.split(obj.getCompetencyCode())[2];
				int sequence = obj.getCompetencySeq();
				
				LOGGER.debug(obj.getCompetencyCode());
				
				Map<String, UserDomainCompetencyMatrixModel> competencies = competencyMatrixMap.get(domainCode);
				competencies.entrySet().stream().filter(entry -> entry.getValue().getCompetencySeq() < sequence)
						.forEach(entry -> {
							int status = entry.getValue().getStatus();
							if (status < 4) {
								entry.getValue().setStatus(INFERRED);
							}
						});
				
				for (String key : competencies.keySet()) {
					LOGGER.debug(competencies.get(key).toString());
				}
				
				competencyMatrixMap.put(domainCode, competencies);
			}

			
			/*Set<String> keys = competencyMatrixMap.keySet();
			for (String key : keys) {
				Map<String, UserDomainCompetencyMatrixModel> competencies = competencyMatrixMap.get(key);
				for (Map.Entry<String, UserDomainCompetencyMatrixModel> entry : competencies.entrySet()) {
					UserDomainCompetencyMatrixModel model  = entry.getValue();
					int status = model.getStatus();
					LOGGER.debug("processing competency '{}' having seq '{}'", entry.getKey(), model.getCompetencySeq());
					if (status == COMPLETED || status == MASTERED) {
						LOGGER.debug("has status completed or mastered");
					}
				}
			}*/
			
			//return UserDomainCompetencyMatrixModelResponseBuilder.build(userCompetencyMatrixModels);
			return UserDomainCompetencyMatrixModelResponseBuilder.build(competencyMatrixMap);
		}
	}
}
