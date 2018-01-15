package org.gooru.ds.user.processor.userperf.lesson;

/**
 * @author mukul@gooru
 */
public class UserPerfLessonModel {

    private String lessonId;
    private String lessonTitle;
    private Integer lessonAsmtTimeSpent;
    private Double lessonAsmtScore;
    private Integer lessonCollTimeSpent;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public Integer getLessonAsmtTimeSpent() {
        return lessonAsmtTimeSpent;
    }

    public void setLessonAsmtTimeSpent(Integer lessonAsmtTimeSpent) {
        this.lessonAsmtTimeSpent = lessonAsmtTimeSpent;
    }

    public Double getLessonAsmtScore() {
        return lessonAsmtScore;
    }

    public void setLessonAsmtScore(Double lessonAsmtScore) {
        this.lessonAsmtScore = lessonAsmtScore;
    }

    public Integer getLessonCollTimeSpent() {
        return lessonCollTimeSpent;
    }

    public void setLessonCollTimeSpent(Integer lessonCollTimeSpent) {
        this.lessonCollTimeSpent = lessonCollTimeSpent;
    }

}
