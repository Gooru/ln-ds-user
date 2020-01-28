package org.gooru.ds.user.processor.userdomaincompetencymatrix;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.gooru.ds.user.processor.userdomaincompetencymatrix.UserDomainCompetencyMatrixModelResponse.UserCompetencyMatrixCompetencyModelResponse;
import org.gooru.ds.user.processor.userdomaincompetencymatrix.UserDomainCompetencyMatrixModelResponse.UserCompetencyMatrixDomainModelResponse;

/**
 * @author ashish on 13/2/18.
 */
final class UserDomainCompetencyMatrixModelResponseBuilder {

  /*
   * Does the response creation from models. Note that models are supposed to be ordered by domains.
   * This logic works off change of domain and/or course
   */
  public static UserDomainCompetencyMatrixModelResponse build(
      List<UserDomainCompetencyMatrixModel> models, Timestamp lastUpdated) {
    UserDomainCompetencyMatrixModelResponse response =
        new UserDomainCompetencyMatrixModelResponse();
    response.setUserCompetencyMatrix(new ArrayList<>());
    String previousDomainCode = null;

    UserCompetencyMatrixDomainModelResponse domainModelResponse = null;

    for (UserDomainCompetencyMatrixModel model : models) {
      if (domainModelResponse == null) {
        domainModelResponse = createDomainModelInResponse(response, model);
      }
      if (previousDomainCode != null) {
        if (!Objects.equals(previousDomainCode, model.getDomainCode())) {
          domainModelResponse = createDomainModelInResponse(response, model);
        }
      }
      createCompetencyModelInResponse(domainModelResponse, model);
      previousDomainCode = model.getDomainCode();
    }

    if (lastUpdated != null) {
      response.setLastUpdated(lastUpdated.getTime());
    }
    return response;
  }

  private static void createCompetencyModelInResponse(
      UserCompetencyMatrixDomainModelResponse courseModelResponse,
      UserDomainCompetencyMatrixModel model) {
    UserCompetencyMatrixCompetencyModelResponse competencyModelResponse =
        createCompetencyModelResponseFromModel(model);
    courseModelResponse.getCompetencies().add(competencyModelResponse);
  }

  private static UserCompetencyMatrixDomainModelResponse createDomainModelInResponse(
      UserDomainCompetencyMatrixModelResponse response, UserDomainCompetencyMatrixModel model) {
    UserCompetencyMatrixDomainModelResponse domainModelResponse;
    domainModelResponse = new UserCompetencyMatrixDomainModelResponse();
    domainModelResponse.setDomainCode(model.getDomainCode());
    domainModelResponse.setCompetencies(new ArrayList<>());
    response.getUserCompetencyMatrix().add(domainModelResponse);
    return domainModelResponse;
  }

  private static UserCompetencyMatrixCompetencyModelResponse createCompetencyModelResponseFromModel(
      UserDomainCompetencyMatrixModel model) {
    UserCompetencyMatrixCompetencyModelResponse competencyModelResponse =
        new UserCompetencyMatrixCompetencyModelResponse();
    competencyModelResponse.setCompetencyCode(model.getCompetencyCode());
    competencyModelResponse.setCompetencyName(model.getCompetencyName());
    competencyModelResponse.setCompetencyDesc(model.getCompetencyDesc());
    competencyModelResponse.setCompetencyStudentDesc(model.getCompetencyStudentDesc());
    competencyModelResponse.setCompetencySeq(model.getCompetencySeq());
    competencyModelResponse.setStatus(model.getStatus());
    competencyModelResponse.setSource(model.getSource());
    return competencyModelResponse;
  }

  private UserDomainCompetencyMatrixModelResponseBuilder() {
    throw new AssertionError();
  }
}
