package org.gooru.ds.user.processor.atc.pvc.course.competency.model;

import java.util.UUID;

/**
 * @author ashish.
 */
public class RouteCalculatorModel {
  private UUID userId;
  private UUID courseId;
  private UUID classId;

  public UUID getUserId() {
    return userId;
  }

  public UUID getCourseId() {
    return courseId;
  }

  public UUID getClassId() {
    return classId;
  }

  public RouteCalculatorModel(UUID userId, UUID courseId, UUID classId) {
    this.userId = userId;
    this.courseId = courseId;
    this.classId = classId;
  }

  public static RouteCalculatorModel fromRoute0QueueModel(Route0QueueModel model) {
    return new RouteCalculatorModel(model.getUserId(), model.getCourseId(), model.getClassId());
  }
}
