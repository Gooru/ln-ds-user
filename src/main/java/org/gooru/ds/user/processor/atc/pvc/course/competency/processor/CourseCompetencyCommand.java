package org.gooru.ds.user.processor.atc.pvc.course.competency.processor;

import java.util.UUID;
import org.gooru.ds.user.processor.atc.pvc.course.competency.model.RouteCalculatorModel;
import io.vertx.core.json.JsonObject;

public class CourseCompetencyCommand {


  private UUID courseId;
  private UUID userId;

  public UUID getCourseId() {
    return courseId;
  }

  public UUID getUserId() {
    return userId;
  }

  public static CourseCompetencyCommand builder(String userId, String courseId) {
    CourseCompetencyCommand command = new CourseCompetencyCommand();

    command.userId = UUID.fromString(userId);
    command.courseId = UUID.fromString(courseId);

    return command;
  }

  RouteCalculatorModel asRouteCalculatorModel() {
    return new RouteCalculatorModel(userId, courseId, null);
  }

  public static final class CommandAttributes {

    static final String COURSE_ID = "courseId";
    static final String USER_ID = "userId";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }


}
