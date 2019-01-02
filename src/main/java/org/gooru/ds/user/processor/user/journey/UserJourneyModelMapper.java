package org.gooru.ds.user.processor.user.journey;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author mukul@gooru
 */
public class UserJourneyModelMapper implements ResultSetMapper<UserJourneyModel> {

  @Override
  public UserJourneyModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    UserJourneyModel model = new UserJourneyModel();
    model.setClassId(r.getString(MapperFields.CLASS_ID));
    model.setClassCode(r.getString(MapperFields.CLASS_CODE));
    model.setClassTitle(r.getString(MapperFields.CLASS_TITLE));
    model.setCourseId(r.getString(MapperFields.COURSE_ID));
    model.setCourseTitle(r.getString(MapperFields.COURSE_TITLE));
    model.setAverageScore(r.getFloat(MapperFields.AVERAGE_SCORE));
    model.setTimeSpent(r.getLong(MapperFields.TIMESPENT));
    model.setAssessmentsCompleted(r.getInt(MapperFields.ASSESSMENTS_COMPLETED));
    model.setTotalAssessments(r.getInt(MapperFields.TOTAL_ASSESSMENTS));

    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String CLASS_ID = "class_id";
    private static final String CLASS_CODE = "class_code";
    private static final String CLASS_TITLE = "class_title";
    private static final String COURSE_ID = "course_id";
    private static final String COURSE_TITLE = "course_title";
    private static final String TIMESPENT = "time_spent";
    private static final String AVERAGE_SCORE = "average_score";
    private static final String ASSESSMENTS_COMPLETED = "assessments_completed";
    private static final String TOTAL_ASSESSMENTS = "total_assessments";

  }

}
