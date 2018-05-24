package org.gooru.ds.user.processor.userdomaincompetencymatrix;

import static org.gooru.ds.user.processor.userdomaincompetencymatrix.UserDomainCompetencyMatrixModelResponse
    .UserCompetencyMatrixCompetencyModelResponse;
import static org.gooru.ds.user.processor.userdomaincompetencymatrix.UserDomainCompetencyMatrixModelResponse
    .UserCompetencyMatrixDomainModelResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ashish on 13/2/18.
 */
final class UserDomainCompetencyMatrixModelResponseBuilder {

    /*
     * Does the response creation from models.
     * Note that models are supposed to be ordered by domains. This logic works off change of
     * domain and/or course
     */
    public static UserDomainCompetencyMatrixModelResponse build(List<UserDomainCompetencyMatrixModel> models) {
        UserDomainCompetencyMatrixModelResponse response = new UserDomainCompetencyMatrixModelResponse();
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

        return response;
    }
    
    public static UserDomainCompetencyMatrixModelResponse build(Map<String, Map<String, UserDomainCompetencyMatrixModel>> models) {
    	UserDomainCompetencyMatrixModelResponse response = new UserDomainCompetencyMatrixModelResponse();
    	response.setUserCompetencyMatrix(new ArrayList<>());
    	
    	for (String domainCode : models.keySet()) {
    		UserCompetencyMatrixDomainModelResponse domainModelResponse = createDomainModelInResponse(response, domainCode);
    		
    		Map<String, UserDomainCompetencyMatrixModel> competencies = models.get(domainCode);
    		for (String compCode : competencies.keySet()) {
    			createCompetencyModelInResponse(domainModelResponse, competencies.get(compCode));
    		}
    	}
    	return response;
    }

    private static void createCompetencyModelInResponse(UserCompetencyMatrixDomainModelResponse courseModelResponse,
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
    
    private static UserCompetencyMatrixDomainModelResponse createDomainModelInResponse(
            UserDomainCompetencyMatrixModelResponse response, String domainCode) {
            UserCompetencyMatrixDomainModelResponse domainModelResponse;
            domainModelResponse = new UserCompetencyMatrixDomainModelResponse();
            domainModelResponse.setDomainCode(domainCode);
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
        return competencyModelResponse;
    }

    private UserDomainCompetencyMatrixModelResponseBuilder() {
        throw new AssertionError();
    }
}
