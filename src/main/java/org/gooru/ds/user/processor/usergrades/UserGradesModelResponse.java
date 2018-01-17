package org.gooru.ds.user.processor.usergrades;

import java.util.List;

/**
 * @author ashish on 17/1/18.
 */
class UserGradesModelResponse {
    private List<UserGradesModel> grades;

    public List<UserGradesModel> getGrades() {
        return grades;
    }

    public void setGrades(List<UserGradesModel> grades) {
        this.grades = grades;
    }
}
