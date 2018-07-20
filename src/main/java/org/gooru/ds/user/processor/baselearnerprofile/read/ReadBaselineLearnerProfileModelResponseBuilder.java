package org.gooru.ds.user.processor.baselearnerprofile.read;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.gooru.ds.user.processor.baselearnerprofile.read.ReadBaselineLearnerProfileModelResponse.UserCompetencyMatrixCompetencyModelResponse;
import org.gooru.ds.user.processor.baselearnerprofile.read.ReadBaselineLearnerProfileModelResponse.UserCompetencyMatrixDomainModelResponse;

/**
 * @author szgooru on 20-Jul-2018
 */
public final class ReadBaselineLearnerProfileModelResponseBuilder {

	public static ReadBaselineLearnerProfileModelResponse build(List<ReadBaselineLearnerProfileModel> models) {
		ReadBaselineLearnerProfileModelResponse response = new ReadBaselineLearnerProfileModelResponse();
		response.setUserCompetencyMatrix(new ArrayList<>());
        String previousDomainCode = null;

        UserCompetencyMatrixDomainModelResponse domainModelResponse = null;

        for (ReadBaselineLearnerProfileModel model : models) {
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
	
	private static void createCompetencyModelInResponse(UserCompetencyMatrixDomainModelResponse baselineModelResponse,
			ReadBaselineLearnerProfileModel model) {
		UserCompetencyMatrixCompetencyModelResponse competencyModelResponse =
	            createCompetencyModelResponseFromModel(model);
		baselineModelResponse.getCompetencies().add(competencyModelResponse);
	    }

	    private static UserCompetencyMatrixDomainModelResponse createDomainModelInResponse(
	    		ReadBaselineLearnerProfileModelResponse response, ReadBaselineLearnerProfileModel model) {
	        UserCompetencyMatrixDomainModelResponse domainModelResponse;
	        domainModelResponse = new UserCompetencyMatrixDomainModelResponse();
	        domainModelResponse.setDomainCode(model.getDomainCode());
	        domainModelResponse.setCompetencies(new ArrayList<>());
	        response.getUserCompetencyMatrix().add(domainModelResponse);
	        return domainModelResponse;
	    }
	    
	    private static UserCompetencyMatrixCompetencyModelResponse createCompetencyModelResponseFromModel(
	    		ReadBaselineLearnerProfileModel model) {
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
}
