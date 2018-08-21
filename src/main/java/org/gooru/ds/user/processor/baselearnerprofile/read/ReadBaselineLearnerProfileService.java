package org.gooru.ds.user.processor.baselearnerprofile.read;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gooru.ds.user.constants.StatusConstants;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author szgooru on 20-Jul-2018
 */
public class ReadBaselineLearnerProfileService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ReadBaselineLearnerProfileService.class);

	private final ReadBaselineLearnerProfileDao dao;

	public ReadBaselineLearnerProfileService(DBI dbi) {
		this.dao = dbi.onDemand(ReadBaselineLearnerProfileDao.class);
	}

	public ReadBaselineLearnerProfileModelResponse fetchBaselineLearnerProfile(
			ReadBaselineLearnerProfileCommand command) {

		List<ReadBaselineLearnerProfileModel> models = null;
		
		Timestamp lastCreated = null;
		ReadBaselineLearnerProfileCommand.ReadBaselineLearnerProfileCommandBean bean = command.asBean(command);
		if (bean.getSubject() == null) {
			LOGGER.debug("no subject found, returning empty response");
			return new ReadBaselineLearnerProfileModelResponse();
		}
		
		if (command.getClassId() != null && !command.getClassId().isEmpty()) {
			models = this.dao.readBaselineLearnerProfile(bean);
			lastCreated = this.dao.fetchLastCreatedTimeInClass(bean);
		} else {
			models = this.dao.readBaselineLearnerProfileIL(bean);
			lastCreated = this.dao.fetchLastCreatedTimeIL(bean);
		}

		if (models == null || models.isEmpty()) {
			LOGGER.debug("no records returned, returning empty");
			return new ReadBaselineLearnerProfileModelResponse();
		}

		List<ReadBaselineLearnerProfileModel> completed = models.stream()
				.filter(model -> model.getStatus() >= StatusConstants.COMPLETED).collect(Collectors.toList());

		Map<String, Map<String, ReadBaselineLearnerProfileModel>> completedCompMatrixMap = new HashMap<>();
		completed.forEach(model -> {
			String domain = model.getDomainCode();
			String compCode = model.getCompetencyCode();

			if (completedCompMatrixMap.containsKey(domain)) {
				Map<String, ReadBaselineLearnerProfileModel> competencies = completedCompMatrixMap.get(domain);
				competencies.put(compCode, model);
				completedCompMatrixMap.put(domain, competencies);
			} else {
				Map<String, ReadBaselineLearnerProfileModel> competencies = new HashMap<>();
				competencies.put(compCode, model);
				completedCompMatrixMap.put(domain, competencies);
			}
		});

		models.forEach(model -> {
			String domainCode = model.getDomainCode();
			int sequence = model.getCompetencySeq();
			int status = model.getStatus();

			if (completedCompMatrixMap.containsKey(domainCode)) {
				Map<String, ReadBaselineLearnerProfileModel> competencies = completedCompMatrixMap.get(domainCode);
				for (Map.Entry<String, ReadBaselineLearnerProfileModel> entry : competencies.entrySet()) {
					ReadBaselineLearnerProfileModel compModel = entry.getValue();
					int compSeq = compModel.getCompetencySeq();

					if (sequence < compSeq && status < StatusConstants.ASSERTED) {
						model.setStatus(StatusConstants.INFERRED);
					}
				}
			}
		});

		return ReadBaselineLearnerProfileModelResponseBuilder.build(models, lastCreated);

	}
}
