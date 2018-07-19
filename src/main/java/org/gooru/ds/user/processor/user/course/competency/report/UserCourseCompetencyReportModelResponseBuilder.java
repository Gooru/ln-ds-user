package org.gooru.ds.user.processor.user.course.competency.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.ClassCompetenciesResponseModel;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.CompetenciesResponseModel;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.ContextResponseModel;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.DomainCompetenciesResponseModel;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.StudentsResponseModel;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.UserCompetencyMatrixResponseModel;

/**
 * @author szgooru on 17-Jul-2018
 */
public final class UserCourseCompetencyReportModelResponseBuilder {

	public static StudentsResponseModel buildStudentsResponseModel(List<UserCourseCompetencyReportModel> models,
			String studentId) {
		StudentsResponseModel response = new StudentsResponseModel();
		response.setUserCompetencyMatrix(new ArrayList<>());

		String previousDomainCode = null;
		UserCompetencyMatrixResponseModel competencyMatrixReponse = null;

		for (UserCourseCompetencyReportModel model : models) {
			if (competencyMatrixReponse == null) {
				competencyMatrixReponse = createDomainModelInResponse(response, model);
			}

			if (previousDomainCode != null) {
				if (!Objects.equals(previousDomainCode, model.getDomainCode())) {
					competencyMatrixReponse = createDomainModelInResponse(response, model);
				}
			}

			createCompetencyModelInResponse(competencyMatrixReponse, model);
			response.setId(studentId);
			previousDomainCode = model.getDomainCode();
		}
		return response;
	}

	private static void createCompetencyModelInResponse(UserCompetencyMatrixResponseModel competencyMatrixReponse,
			UserCourseCompetencyReportModel model) {
		CompetenciesResponseModel competencyResponseModel = createCompetencyResponseModel(model);
		competencyMatrixReponse.getCompetencies().add(competencyResponseModel);
	}

	private static UserCompetencyMatrixResponseModel createDomainModelInResponse(StudentsResponseModel response,
			UserCourseCompetencyReportModel model) {
		UserCompetencyMatrixResponseModel responseModel = new UserCompetencyMatrixResponseModel();
		responseModel.setDomainCode(model.getDomainCode());
		responseModel.setCompetencies(new ArrayList<>());
		response.getUserCompetencyMatrix().add(responseModel);
		return responseModel;
	}

	private static CompetenciesResponseModel createCompetencyResponseModel(UserCourseCompetencyReportModel model) {
		CompetenciesResponseModel responseModel = new CompetenciesResponseModel();
		responseModel.setCompetencyCode(model.getCompetencyCode());
		responseModel.setCompetencyName(model.getCompetencyName());
		responseModel.setCompetencyDesc(model.getCompetencyDesc());
		responseModel.setCompetencyStudentDesc(model.getCompetencyStudentDesc());
		responseModel.setCompetencySeq(model.getCompetencySeq());
		responseModel.setStatus(model.getStatus());
		return responseModel;
	}

	public static Collection<ClassCompetenciesResponseModel> buildClassCompetenciesResponseModel(
			List<DomainCompetenciesModel> models) {
		Map<String, ClassCompetenciesResponseModel> response = new HashMap<>();

		ClassCompetenciesResponseModel classCompetencyModel  = null;
		String previousDomainCode = null;
		
		for (DomainCompetenciesModel model : models) {
			if (classCompetencyModel == null) {
				classCompetencyModel = createDomainModel(response, model);
			}
			
			if (previousDomainCode != null) {
				if (!Objects.equals(previousDomainCode, model.getDomainCode())) {
					classCompetencyModel = createDomainModel(response, model);
				}
			}
			
			createCompetencyModelInResponse(response, model);
			previousDomainCode = model.getDomainCode();
		}
		return response.values();
	}
	
	public static ClassCompetenciesResponseModel createDomainModel(Map<String, ClassCompetenciesResponseModel> response, DomainCompetenciesModel model) {
		ClassCompetenciesResponseModel responseModel = new ClassCompetenciesResponseModel();
		responseModel.setDomainCode(model.getDomainCode());
		responseModel.setDomainName(model.getDomainName());
		responseModel.setDomainSeq(model.getDomainSeq());
		responseModel.setCompetencies(new ArrayList<>());
		response.put(model.getDomainCode(), responseModel);
		return responseModel;
	}
	
	private static void createCompetencyModelInResponse(Map<String, ClassCompetenciesResponseModel> response, DomainCompetenciesModel model) {
		DomainCompetenciesResponseModel competencyResponseModel = createCompetencyResponseModel(model);
		response.get(model.getDomainCode()).getCompetencies().add(competencyResponseModel);
	}
	
	private static DomainCompetenciesResponseModel createCompetencyResponseModel(DomainCompetenciesModel model) {
		DomainCompetenciesResponseModel responseModel = new DomainCompetenciesResponseModel();
		responseModel.setCompetencyCode(model.getCompetencyCode());
		responseModel.setCompetencyName(model.getCompetencyName());
		responseModel.setCompetencyDesc(model.getCompetencyDesc());
		responseModel.setCompetencyStudentDesc(model.getCompetencyStudentDesc());
		responseModel.setCompetencySeq(model.getCompetencySeq());
		return responseModel;
	}
	

	public static ContextResponseModel buildContextResponseModel(UserCourseCompetencyReportCommand command,
			String subjectCode) {
		ContextResponseModel contextResponse = new ContextResponseModel();
		contextResponse.setClassId(command.getClassId());
		contextResponse.setCourseId(command.getCourseId());
		contextResponse.setFilter(command.getFilter());
		contextResponse.setFromMonth(command.getFromMonth());
		contextResponse.setFromYear(command.getFromYear());
		contextResponse.setToMonth(command.getToMonth());
		contextResponse.setToYear(command.getToYear());
		contextResponse.setSubjectCode(subjectCode);
		return contextResponse;
	}

	private UserCourseCompetencyReportModelResponseBuilder() {
		throw new AssertionError();
	}
}
