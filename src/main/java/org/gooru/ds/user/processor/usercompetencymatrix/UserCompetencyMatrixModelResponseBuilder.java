package org.gooru.ds.user.processor.usercompetencymatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.gooru.ds.user.processor.usercompetencymatrix.UserCompetencyMatrixModelResponse
    .UserCompetencyMatrixCompetencyModelResponse;
import org.gooru.ds.user.processor.usercompetencymatrix.UserCompetencyMatrixModelResponse
    .UserCompetencyMatrixCourseModelResponse;
import org.gooru.ds.user.processor.usercompetencymatrix.UserCompetencyMatrixModelResponse
    .UserCompetencyMatrixDomainModelResponse;

/**
 * @author ashish on 17/1/18.
 */
final class UserCompetencyMatrixModelResponseBuilder {

    /*
     * Does the response creation from models.
     * Note that models are supposed to be ordered by course and then domains. This logic works off change of
     * domain and/or course
     */
    public static UserCompetencyMatrixModelResponse build(List<UserCompetencyMatrixModel> models) {
        UserCompetencyMatrixModelResponse response = new UserCompetencyMatrixModelResponse();
        response.setUserCompetencyMatrix(new ArrayList<>());
        String previousCourseCode = null, previousDomainCode = null;

        UserCompetencyMatrixCourseModelResponse courseModelResponse = null;
        UserCompetencyMatrixDomainModelResponse domainModelResponse = null;

        for (UserCompetencyMatrixModel model : models) {
            if (courseModelResponse == null) {
                courseModelResponse = createCourseModelInResponse(response, model);
            }
            if (domainModelResponse == null) {
                domainModelResponse = createDomainModelInResponse(courseModelResponse, model);
            }
            if (previousCourseCode != null && previousDomainCode != null) {
                if (!Objects.equals(previousCourseCode, model.getCourseCode())) {
                    courseModelResponse = createCourseModelInResponse(response, model);
                    domainModelResponse = createDomainModelInResponse(courseModelResponse, model);
                } else if (!Objects.equals(previousDomainCode, model.getDomainCode())) {
                    domainModelResponse = createDomainModelInResponse(courseModelResponse, model);
                }
            }
            createCompetencyModelInResponse(domainModelResponse, model);
            previousCourseCode = model.getCourseCode();
            previousDomainCode = model.getDomainCode();
        }

        return response;
    }

    private static void createCompetencyModelInResponse(UserCompetencyMatrixDomainModelResponse domainModelResponse,
        UserCompetencyMatrixModel model) {
        UserCompetencyMatrixCompetencyModelResponse competencyModelResponse =
            createCompetencyModelResponseFromModel(model);
        domainModelResponse.getCompetencies().add(competencyModelResponse);
    }

    private static UserCompetencyMatrixDomainModelResponse createDomainModelInResponse(
        UserCompetencyMatrixCourseModelResponse courseModelResponse, UserCompetencyMatrixModel model) {
        UserCompetencyMatrixDomainModelResponse domainModelResponse;
        domainModelResponse = new UserCompetencyMatrixDomainModelResponse();
        domainModelResponse.setDomainCode(model.getDomainCode());
        domainModelResponse.setCompetencies(new ArrayList<>());
        courseModelResponse.getDomains().add(domainModelResponse);
        return domainModelResponse;
    }

    private static UserCompetencyMatrixCourseModelResponse createCourseModelInResponse(
        UserCompetencyMatrixModelResponse response, UserCompetencyMatrixModel model) {
        UserCompetencyMatrixCourseModelResponse courseModelResponse;
        courseModelResponse = new UserCompetencyMatrixCourseModelResponse();
        courseModelResponse.setCourseCode(model.getCourseCode());
        courseModelResponse.setDomains(new ArrayList<>());
        response.getUserCompetencyMatrix().add(courseModelResponse);
        return courseModelResponse;
    }

    private static UserCompetencyMatrixCompetencyModelResponse createCompetencyModelResponseFromModel(
        UserCompetencyMatrixModel model) {
        UserCompetencyMatrixCompetencyModelResponse competencyModelResponse =
            new UserCompetencyMatrixCompetencyModelResponse();
        competencyModelResponse.setCompetencyCode(model.getCompetencyCode());
        competencyModelResponse.setCompetencyName(model.getCompetencyName());
        competencyModelResponse.setCompetencyDesc(model.getCompetencyDesc());
        competencyModelResponse.setCompetencyStudentDesc(model.getCompetencyStudentDesc());
        competencyModelResponse.setCompetencySeq(model.getCompetencySeq());
        competencyModelResponse.setStatus(model.getStatus());
        return competencyModelResponse;
    }

    private UserCompetencyMatrixModelResponseBuilder() {
        throw new AssertionError();
    }
}
