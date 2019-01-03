package org.gooru.ds.user.processor.userperf.lesson;

import java.util.List;

/**
 * @author mukul@gooru
 */
public class UserPerfLessonModelResponse {

  private List<UserPerfLessonModel> lessons;

  public List<UserPerfLessonModel> getLessons() {
    return lessons;
  }

  public void setLessons(List<UserPerfLessonModel> lessons) {
    this.lessons = lessons;
  }

}
