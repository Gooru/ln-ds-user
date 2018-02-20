package org.gooru.ds.user.processor.userstats.courses;

/**
 * @author ashish on 13/1/18.
 */

class UserStatsCoursesModel {
    private String courseId;
    private String classId;
	private String courseTitle;
    private String classTitle;
    private Float completion;
    private Float performance;
    private Long timespent;
    private Boolean startedInDuration;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
    
    public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getClassTitle() {
		return classTitle;
	}

	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}

    public Float getCompletion() {
        return completion;
    }

    public void setCompletion(Float completion) {
        this.completion = completion;
    }

    public Float getPerformance() {
        return performance;
    }

    public void setPerformance(Float performance) {
        this.performance = performance;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public Boolean getStartedInDuration() {
        return startedInDuration;
    }

    public void setStartedInDuration(Boolean startedInDuration) {
        this.startedInDuration = startedInDuration;
    }
}
