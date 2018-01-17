package org.gooru.ds.user.processor.competencymatrixcoordinates;

import java.util.List;

/**
 * @author ashish on 17/1/18.
 */
class CompetencyMatrixCoordinatesModelResponse {
    List<CompetencyMatrixCoordinatesCourseModel> courses;
    List<CompetencyMatrixCoordinatesDomainModel> domains;

    public List<CompetencyMatrixCoordinatesCourseModel> getCourses() {
        return courses;
    }

    public void setCourses(List<CompetencyMatrixCoordinatesCourseModel> courses) {
        this.courses = courses;
    }

    public List<CompetencyMatrixCoordinatesDomainModel> getDomains() {
        return domains;
    }

    public void setDomains(List<CompetencyMatrixCoordinatesDomainModel> domains) {
        this.domains = domains;
    }
}
