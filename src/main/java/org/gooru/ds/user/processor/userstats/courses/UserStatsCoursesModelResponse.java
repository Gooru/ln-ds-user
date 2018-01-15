package org.gooru.ds.user.processor.userstats.courses;

import java.util.List;

/**
 * @author ashish on 13/1/18.
 */
class UserStatsCoursesModelResponse {

    private List<UserStatsCoursesModel> courses;

    public List<UserStatsCoursesModel> getCourses() {
        return courses;
    }

    public void setCourses(List<UserStatsCoursesModel> courses) {
        this.courses = courses;
    }
}
