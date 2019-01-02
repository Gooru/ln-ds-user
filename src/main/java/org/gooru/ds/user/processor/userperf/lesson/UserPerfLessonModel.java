package org.gooru.ds.user.processor.userperf.lesson;

/**
 * @author mukul@gooru
 */
public class UserPerfLessonModel {

  private String lessonId;
  private String lessonTitle;
  private Long lessonAsmtTimeSpent;
  private Double lessonAsmtScore;
  private Long lessonCollTimeSpent;
  private Integer lessonSequenceId;

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

  public Long getLessonAsmtTimeSpent() {
    return lessonAsmtTimeSpent;
  }

  public void setLessonAsmtTimeSpent(Long lessonAsmtTimeSpent) {
    this.lessonAsmtTimeSpent = lessonAsmtTimeSpent;
  }

  public Double getLessonAsmtScore() {
    return lessonAsmtScore;
  }

  public void setLessonAsmtScore(Double lessonAsmtScore) {
    this.lessonAsmtScore = lessonAsmtScore;
  }

  public Long getLessonCollTimeSpent() {
    return lessonCollTimeSpent;
  }

  public void setLessonCollTimeSpent(Long lessonCollTimeSpent) {
    this.lessonCollTimeSpent = lessonCollTimeSpent;
  }

  public Integer getLessonSequenceId() {
    return lessonSequenceId;
  }

  public void setLessonSequenceId(Integer lessonSequenceId) {
    this.lessonSequenceId = lessonSequenceId;
  }


}
