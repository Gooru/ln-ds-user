package org.gooru.ds.user.processor.usercompetencymatrix;

import java.util.List;

/**
 * @author ashish on 17/1/18.
 */
class UserCompetencyMatrixModelResponse {
    List<UserCompetencyMatrixCourseModelResponse> userCompetencyMatrix;

    public List<UserCompetencyMatrixCourseModelResponse> getUserCompetencyMatrix() {
        return userCompetencyMatrix;
    }

    public void setUserCompetencyMatrix(List<UserCompetencyMatrixCourseModelResponse> userCompetencyMatrix) {
        this.userCompetencyMatrix = userCompetencyMatrix;
    }

    static class UserCompetencyMatrixCourseModelResponse {
        private String courseCode;
        private List<UserCompetencyMatrixDomainModelResponse> domains;

        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public List<UserCompetencyMatrixDomainModelResponse> getDomains() {
            return domains;
        }

        public void setDomains(List<UserCompetencyMatrixDomainModelResponse> domains) {
            this.domains = domains;
        }
    }

    static class UserCompetencyMatrixDomainModelResponse {
        private String domainCode;
        private List<UserCompetencyMatrixCompetencyModelResponse> competencies;

        public String getDomainCode() {
            return domainCode;
        }

        public void setDomainCode(String domainCode) {
            this.domainCode = domainCode;
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
