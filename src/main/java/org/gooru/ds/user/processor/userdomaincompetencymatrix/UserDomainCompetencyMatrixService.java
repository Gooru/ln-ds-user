package org.gooru.ds.user.processor.userdomaincompetencymatrix;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.skife.jdbi.v2.DBI;

/**
 * @author ashish on 13/2/18.
 */
class UserDomainCompetencyMatrixService {

	private final UserDomainCompetencyMatrixDao userCompetencyMatrixDao;

	private static final int INFERRED = 2;
	private static final int ASSERTED = 3;
	private static final int COMPLETED = 4;

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
					.filter(model -> model.getStatus() >= COMPLETED).collect(Collectors.toList());

			Map<String, Map<String, UserDomainCompetencyMatrixModel>> completedCompMatrixMap = new HashMap<>();
			completed.forEach(model -> {
				String domain = model.getDomainCode();
				String compCode = model.getCompetencyCode();

				if (completedCompMatrixMap.containsKey(domain)) {
					Map<String, UserDomainCompetencyMatrixModel> competencies = completedCompMatrixMap.get(domain);
					competencies.put(compCode, model);
					completedCompMatrixMap.put(domain, competencies);
				} else {
					Map<String, UserDomainCompetencyMatrixModel> competencies = new HashMap<>();
					competencies.put(compCode, model);
					completedCompMatrixMap.put(domain, competencies);
				}
			});

			userCompetencyMatrixModels.forEach(model -> {
				String domainCode = model.getDomainCode();
				int sequence = model.getCompetencySeq();
				int status = model.getStatus();

				if (completedCompMatrixMap.containsKey(domainCode)) {
					Map<String, UserDomainCompetencyMatrixModel> competencies = completedCompMatrixMap.get(domainCode);
					for (Map.Entry<String, UserDomainCompetencyMatrixModel> entry : competencies.entrySet()) {
						UserDomainCompetencyMatrixModel compModel = entry.getValue();
						int compSeq = compModel.getCompetencySeq();

						if (sequence < compSeq && status < ASSERTED) {
							model.setStatus(INFERRED);
						}
					}
				}
			});

			Timestamp lastUpdated = userCompetencyMatrixDao.fetchLastUpdatedTime(command.getUser(), command.getSubject());
			return UserDomainCompetencyMatrixModelResponseBuilder.build(userCompetencyMatrixModels, lastUpdated.getTime());
		}
	}
}
