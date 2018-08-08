package org.gooru.ds.user.processor.user.course.competency.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.gooru.ds.user.constants.StatusConstants;
import org.gooru.ds.user.processor.baselearnerprofile.SubjectInferer;
import org.gooru.ds.user.processor.dbhelpers.core.CoreService;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;

/**
 * @author szgooru on 17-Jul-2018
 */
public class UserCourseCompetencyReportService {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserCourseCompetencyReportService.class);

	private final UserCourseCompetencyReportDao dao;
	private final CoreService coreService;

	public UserCourseCompetencyReportService(DBI dbi, DBI coreDbi) {
		this.dao = dbi.onDemand(UserCourseCompetencyReportDao.class);
		this.coreService = new CoreService(coreDbi);
	}

	public String fetchSubjectCode(UserCourseCompetencyReportCommand command) {
		String courseId = command.getCourseId();
		String sc = SubjectInferer.build().inferSubjectForCourse(UUID.fromString(courseId));
		if (sc == null) {
			LOGGER.warn("Not able to find subject code for specified course '{}'", courseId);
			throw new IllegalStateException("Not able to find subject code for specified course " + courseId);
		}

		LOGGER.debug("The Subject Code is" + sc);
		return sc;
	}

	public JsonArray fetchUserCourseCompetencyMatrix(UserCourseCompetencyReportCommand command, String subjectCode) {
		JsonArray studentReport = new JsonArray();

		// If student id is not null then fetch the report for single student
		// else for all class
		List<String> studentIds = null;
		if (command.getStudentId() != null && !command.getStudentId().isEmpty()) {
			studentIds = new ArrayList<>();
			studentIds.add(command.getStudentId());
		} else {
			studentIds = this.coreService.fetchClassMembers(command.getClassId());
		}

		for (String studentId : studentIds) {

			UserCourseCompetencyReportCommand.UserCourseCompetencyReportCommandBean bean = command.asBean();
			bean.setStudentId(studentId);
			bean.setSubject(subjectCode);

			List<UserCourseCompetencyReportModel> competencyReportModels = null;
			if (command.getFilter().equalsIgnoreCase(UserCourseCompetencyReportCommand.FILTER_WINDOW)) {
				competencyReportModels = this.dao.fetchUserDomainCompetencyMatrixWindow(bean);
			} else {
				competencyReportModels = this.dao.fetchUserDomainCompetencyMatrixCumulative(bean);
			}
			LOGGER.debug("report for user:{} returned {} rows", studentId, competencyReportModels.size());

			if (!competencyReportModels.isEmpty()) {

				List<UserCourseCompetencyReportModel> completed = competencyReportModels.stream()
						.filter(model -> model.getStatus() >= StatusConstants.COMPLETED).collect(Collectors.toList());

				Map<String, Map<String, UserCourseCompetencyReportModel>> completedCompMatrixMap = new HashMap<>();
				completed.forEach(model -> {
					String domain = model.getDomainCode();
					String compCode = model.getCompetencyCode();

					if (completedCompMatrixMap.containsKey(domain)) {
						Map<String, UserCourseCompetencyReportModel> competencies = completedCompMatrixMap.get(domain);
						competencies.put(compCode, model);
						completedCompMatrixMap.put(domain, competencies);
					} else {
						Map<String, UserCourseCompetencyReportModel> competencies = new HashMap<>();
						competencies.put(compCode, model);
						completedCompMatrixMap.put(domain, competencies);
					}
				});

				competencyReportModels.forEach(model -> {
					String domainCode = model.getDomainCode();
					int sequence = model.getCompetencySeq();
					int status = model.getStatus();

					if (completedCompMatrixMap.containsKey(domainCode)) {
						Map<String, UserCourseCompetencyReportModel> competencies = completedCompMatrixMap
								.get(domainCode);
						for (Map.Entry<String, UserCourseCompetencyReportModel> entry : competencies.entrySet()) {
							UserCourseCompetencyReportModel compModel = entry.getValue();
							int compSeq = compModel.getCompetencySeq();

							if (sequence < compSeq && status < StatusConstants.ASSERTED) {
								model.setStatus(StatusConstants.INFERRED);
							}
						}
					}
				});

				studentReport.add(UserCourseCompetencyReportModelResponseBuilder
						.buildStudentsResponseModel(competencyReportModels, studentId));
			}
		}

		return studentReport;
	}

	public UserCourseCompetencyReportModelResponse fetchDomainCompetencies(UserCourseCompetencyReportCommand command,
			String subjectCode) {

		UserCourseCompetencyReportModelResponse response = new UserCourseCompetencyReportModelResponse();
		response.setContext(
				UserCourseCompetencyReportModelResponseBuilder.buildContextResponseModel(command, subjectCode));

		List<DomainCompetenciesModel> allDomainCompetenciesList = this.dao.fetchAllDomainCompetencies(subjectCode);
		response.setDomainCompetencies(UserCourseCompetencyReportModelResponseBuilder
				.buildDomainCompetenciesResponseModel(allDomainCompetenciesList).getDomainCompetencies());

		List<String> courseCompetencies = this.coreService.fetchCourseCompetencies(command.getCourseId());
		Map<String, DomainCompetenciesModel> allDomainCompetencyMap = new HashMap<>();

		allDomainCompetenciesList.forEach(competency -> {
			allDomainCompetencyMap.put(competency.getCompetencyCode(), competency);
		});

		List<DomainCompetenciesModel> courseCompetencyList = new ArrayList<>();
		courseCompetencies.forEach(comp -> {
			// Just in case where course aggregated tags may be of different subject. We
			// need the data to be filtered only for the subject asked for
			if (comp.startsWith(subjectCode)) {
				DomainCompetenciesModel model = allDomainCompetencyMap.get(comp);
				if (model != null) {
					courseCompetencyList.add(allDomainCompetencyMap.get(comp));
				}
			}
		});
		
		Collections.sort(courseCompetencyList, new Comparator<DomainCompetenciesModel>() {
			@Override
			public int compare(DomainCompetenciesModel o1, DomainCompetenciesModel o2) {
				return o1.getDomainCode().toUpperCase().compareTo(o2.getDomainCode().toUpperCase());
			}
		});
		
		response.setClassCompetencies(UserCourseCompetencyReportModelResponseBuilder
				.buildDomainCompetenciesResponseModel(courseCompetencyList).getDomainCompetencies());

		return response;
	}

	public static String toPostgresArrayString(List<String> input) {
		Iterator<String> it = input.iterator();
		if (!it.hasNext()) {
			return "{}";
		}

		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for (;;) {
			String s = it.next();
			sb.append('"').append(s).append('"');
			if (!it.hasNext()) {
				return sb.append('}').toString();
			}
			sb.append(',');
		}
	}

}
