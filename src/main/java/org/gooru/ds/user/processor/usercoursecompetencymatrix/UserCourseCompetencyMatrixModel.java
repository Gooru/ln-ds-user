package org.gooru.ds.user.processor.usercoursecompetencymatrix;

/**
 * @author ashish on 13/2/18.
 */
class UserCourseCompetencyMatrixModel {
    private String courseCode;
    private String competencyCode;
    private String competencyName;
    private String competencyDesc;
    private String competencyStudentDesc;
    private Integer competencySeq;
    private Integer status;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

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
