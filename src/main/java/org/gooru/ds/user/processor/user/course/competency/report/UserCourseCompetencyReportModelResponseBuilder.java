package org.gooru.ds.user.processor.user.course.competency.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.ContextResponseModel;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.DomainCompetenciesResponseModel;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelResponse.DomainsResponseModel;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru on 17-Jul-2018
 */
public final class UserCourseCompetencyReportModelResponseBuilder {

  public static JsonObject buildStudentsResponseModel(List<UserCourseCompetencyReportModel> models,
      String studentId) {
    JsonObject result = new JsonObject();
    result.put(UserCourseCompetencyReportModelResponseConstants.ID, studentId);
    result.put(UserCourseCompetencyReportModelResponseConstants.JSON_RESP_KEY_USERCOMPETENCY_MATRIX,
        new JsonArray());

    String previousDomainCode = null;
    JsonObject competencyMatrixReponse = null;

    for (UserCourseCompetencyReportModel model : models) {
      if (competencyMatrixReponse == null) {
        competencyMatrixReponse = createDomainModelInResponse(result, model);
      }

      if (previousDomainCode != null) {
        if (!Objects.equals(previousDomainCode, model.getDomainCode())) {
          competencyMatrixReponse = createDomainModelInResponse(result, model);
        }
      }

      createCompetencyModelInResponse(competencyMatrixReponse, model);
      previousDomainCode = model.getDomainCode();
    }
    return result;
  }

  private static void createCompetencyModelInResponse(JsonObject competencyMatrixReponse,
      UserCourseCompetencyReportModel model) {
    competencyMatrixReponse
        .getJsonObject(UserCourseCompetencyReportModelResponseConstants.JSON_RESP_KEY_COMPETENCIES)
        .put(model.getCompetencyCode(), model.getStatus());
  }

  private static JsonObject createDomainModelInResponse(JsonObject response,
      UserCourseCompetencyReportModel model) {
    JsonObject result = new JsonObject();
    result.put(UserCourseCompetencyReportModelResponseConstants.JSON_RESP_KEY_DOMAIN_CODE,
        model.getDomainCode());
    result.put(UserCourseCompetencyReportModelResponseConstants.JSON_RESP_KEY_COMPETENCIES,
        new JsonObject());
    response
        .getJsonArray(
            UserCourseCompetencyReportModelResponseConstants.JSON_RESP_KEY_USERCOMPETENCY_MATRIX)
        .add(result);
    return result;
  }

  public static UserCourseCompetencyReportModelResponse buildDomainCompetenciesResponseModel(
      List<DomainCompetenciesModel> models) {
    UserCourseCompetencyReportModelResponse response =
        new UserCourseCompetencyReportModelResponse();
    response.setDomainCompetencies(new ArrayList<>());

    DomainsResponseModel classCompetencyModel = null;
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

      createCompetencyModelInResponse(classCompetencyModel, model);
      previousDomainCode = model.getDomainCode();
    }

    return response;
  }

  public static DomainsResponseModel createDomainModel(
      UserCourseCompetencyReportModelResponse response, DomainCompetenciesModel model) {
    DomainsResponseModel responseModel = new DomainsResponseModel();
    responseModel.setDomainCode(model.getDomainCode());
    responseModel.setDomainName(model.getDomainName());
    responseModel.setDomainSeq(model.getDomainSeq());
    responseModel.setCompetencies(new ArrayList<>());
    response.getDomainCompetencies().add(responseModel);
    return responseModel;
  }

  private static void createCompetencyModelInResponse(DomainsResponseModel classCompetencyModel,
      DomainCompetenciesModel model) {
    DomainCompetenciesResponseModel competencyResponseModel = createCompetencyResponseModel(model);
    classCompetencyModel.getCompetencies().add(competencyResponseModel);
  }

  private static DomainCompetenciesResponseModel createCompetencyResponseModel(
      DomainCompetenciesModel model) {
    DomainCompetenciesResponseModel responseModel = new DomainCompetenciesResponseModel();
    responseModel.setCompetencyCode(model.getCompetencyCode());
    responseModel.setCompetencyName(model.getCompetencyName());
    responseModel.setCompetencyDesc(model.getCompetencyDesc());
    responseModel.setCompetencyStudentDesc(model.getCompetencyStudentDesc());
    responseModel.setCompetencySeq(model.getCompetencySeq());
    return responseModel;
  }

  public static ContextResponseModel buildContextResponseModel(
      UserCourseCompetencyReportCommand command, String subjectCode) {
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
