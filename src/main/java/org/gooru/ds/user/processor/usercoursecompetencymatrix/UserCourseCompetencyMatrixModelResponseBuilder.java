package org.gooru.ds.user.processor.usercoursecompetencymatrix;

import static org.gooru.ds.user.processor.usercoursecompetencymatrix.UserCourseCompetencyMatrixModelResponse
    .UserCompetencyMatrixCompetencyModelResponse;
import static org.gooru.ds.user.processor.usercoursecompetencymatrix.UserCourseCompetencyMatrixModelResponse
    .UserCompetencyMatrixCourseModelResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ashish on 13/2/18.
 */
final class UserCourseCompetencyMatrixModelResponseBuilder {

    /*
     * Does the response creation from models.
     * Note that models are supposed to be ordered by course. This logic works off change of
     * domain and/or course
     */
    public static UserCourseCompetencyMatrixModelResponse build(List<UserCourseCompetencyMatrixModel> models, Timestamp lastUpdated) {
        UserCourseCompetencyMatrixModelResponse response = new UserCourseCompetencyMatrixModelResponse();
        response.setUserCompetencyMatrix(new ArrayList<>());
        String previousCourseCode = null;

        UserCompetencyMatrixCourseModelResponse courseModelResponse = null;

        for (UserCourseCompetencyMatrixModel model : models) {
            if (courseModelResponse == null) {
                courseModelResponse = createCourseModelInResponse(response, model);
            }
            if (previousCourseCode != null) {
                if (!Objects.equals(previousCourseCode, model.getCourseCode())) {
                    courseModelResponse = createCourseModelInResponse(response, model);
                }
            }
            createCompetencyModelInResponse(courseModelResponse, model);
            previousCourseCode = model.getCourseCode();
        }

        if (lastUpdated != null) {
        	response.setLastUpdated(lastUpdated.getTime());
        }
        return response;
    }

    private static void createCompetencyModelInResponse(UserCompetencyMatrixCourseModelResponse courseModelResponse,
        UserCourseCompetencyMatrixModel model) {
        UserCompetencyMatrixCompetencyModelResponse competencyModelResponse =
            createCompetencyModelResponseFromModel(model);
        courseModelResponse.getCompetencies().add(competencyModelResponse);
    }

    private static UserCompetencyMatrixCourseModelResponse createCourseModelInResponse(
        UserCourseCompetencyMatrixModelResponse response, UserCourseCompetencyMatrixModel model) {
        UserCompetencyMatrixCourseModelResponse courseModelResponse;
        courseModelResponse = new UserCompetencyMatrixCourseModelResponse();
        courseModelResponse.setCourseCode(model.getCourseCode());
        courseModelResponse.setCompetencies(new ArrayList<>());
        response.getUserCompetencyMatrix().add(courseModelResponse);
        return courseModelResponse;
    }

    private static UserCompetencyMatrixCompetencyModelResponse createCompetencyModelResponseFromModel(
        UserCourseCompetencyMatrixModel model) {
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

    private UserCourseCompetencyMatrixModelResponseBuilder() {
        throw new AssertionError();
    }
}
