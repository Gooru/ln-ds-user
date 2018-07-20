package org.gooru.ds.user.processor.user.course.competency.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gooru.ds.user.constants.StatusConstants;
import org.gooru.ds.user.processor.dbhelpers.core.CoreService;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.ClassCompetenciesResponseModel;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.StudentsResponseModel;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	public UserCourseCompetencyReportModelResponse fetchCourseCompetencies(UserCourseCompetencyReportCommand command) {

		List<StudentsResponseModel> studentReport = new ArrayList<>();
		final String subjectCode = this.coreService.fetchCourseSubject(command.getCourseId());
		if (subjectCode == null || subjectCode.isEmpty()) {
			LOGGER.debug("Subject code is not present at course, returning empty");
			return new UserCourseCompetencyReportModelResponse();
		}

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

				StudentsResponseModel studentsResponseModel = UserCourseCompetencyReportModelResponseBuilder
						.buildStudentsResponseModel(competencyReportModels, studentId);
				studentReport.add(studentsResponseModel);
			}
		}

		UserCourseCompetencyReportModelResponse response = new UserCourseCompetencyReportModelResponse();
		response.setContext(
				UserCourseCompetencyReportModelResponseBuilder.buildContextResponseModel(command, subjectCode));

		// Fetch course competencies from core and populate details from domain
		// competencies matrix table
		List<String> courseCompetencies = this.coreService.fetchCourseCompetencies(command.getCourseId());
		List<DomainCompetenciesModel> domainCompetenciesModels = this.dao
				.fetchDomainCompetencies(toPostgresArrayString(courseCompetencies));
		Collection<ClassCompetenciesResponseModel> domainCompetencies = UserCourseCompetencyReportModelResponseBuilder
				.buildClassCompetenciesResponseModel(domainCompetenciesModels);
		List<ClassCompetenciesResponseModel> listdomainCompetencies = new ArrayList<>();
		domainCompetencies.forEach(entry -> {
			listdomainCompetencies.add(entry);
		});
		response.setClassCompetencies(listdomainCompetencies);

		response.setStudents(studentReport);
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
