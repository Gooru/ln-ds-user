package org.gooru.ds.user.processor.usercoursecompetencymatrix;

import java.util.List;

/**
 * @author ashish on 13/2/18.
 */
class UserCourseCompetencyMatrixModelResponse {
    List<UserCompetencyMatrixCourseModelResponse> userCompetencyMatrix;
    long lastUpdated;

    public List<UserCompetencyMatrixCourseModelResponse> getUserCompetencyMatrix() {
        return userCompetencyMatrix;
    }

    public void setUserCompetencyMatrix(List<UserCompetencyMatrixCourseModelResponse> userCompetencyMatrix) {
        this.userCompetencyMatrix = userCompetencyMatrix;
    }
    
    public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

    static class UserCompetencyMatrixCourseModelResponse {
        private String courseCode;
        private List<UserCompetencyMatrixCompetencyModelResponse> competencies;

        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public List<UserCompetencyMatrixCompetencyModelResponse> getCompetencies() {
            return competencies;
        }

        public void setCompetencies(List<UserCompetencyMatrixCompetencyModelResponse> competencies) {
            this.competencies = competencies;
        }
    }

    static class UserCompetencyMatrixCompetencyModelResponse {
        private String competencyCode;
        private String competencyName;
        private String competencyDesc;
        private String competencyStudentDesc;
        private Integer competencySeq;
        private Integer status;

        public String getCompetencyCode() {
            return competencyCode;
        }

        public void setCompetencyCode(String competencyCode) {
            this.competencyCode = competencyCode;
        }

        public String getCompetencyName() {
            return competencyName;
        }

        public void setCompetencyName(String competencyName) {
            this.competencyName = competencyName;
        }

        public String getCompetencyDesc() {
            return competencyDesc;
        }

        public void setCompetencyDesc(String competencyDesc) {
            this.competencyDesc = competencyDesc;
        }

        public String getCompetencyStudentDesc() {
            return competencyStudentDesc;
        }

        public void setCompetencyStudentDesc(String competencyStudentDesc) {
            this.competencyStudentDesc = competencyStudentDesc;
        }

        public Integer getCompetencySeq() {
            return competencySeq;
        }

        public void setCompetencySeq(Integer competencySeq) {
            this.competencySeq = competencySeq;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }
}
