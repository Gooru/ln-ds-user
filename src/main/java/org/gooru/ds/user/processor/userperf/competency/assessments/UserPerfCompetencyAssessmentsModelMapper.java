package org.gooru.ds.user.processor.userperf.competency.assessments;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author mukul@gooru
 */
public class UserPerfCompetencyAssessmentsModelMapper
    implements ResultSetMapper<UserPerfCompetencyAssessmentsModel> {

  @Override
  public UserPerfCompetencyAssessmentsModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPerfCompetencyAssessmentsModel model = new UserPerfCompetencyAssessmentsModel();
    model.setId(r.getString(MapperFields.ASSESSMENT_ID));
    model.setTitle(r.getString(MapperFields.ASSESSMENT_TITLE));
    model.setSessionId(r.getString(MapperFields.ASSESSMENT_SESSION_ID));
    model.setTimeSpent(r.getLong(MapperFields.ASSESSMENT_TIMESPENT));
    model.setScore(r.getDouble(MapperFields.ASSESSMENT_SCORE));
    model.setReaction(r.getInt(MapperFields.ASSESSMENT_REACTION));

    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    // generically referred to as collection_id in analytics DB
    private static final String ASSESSMENT_ID = "collection_id";
    private static final String ASSESSMENT_TITLE = "collection_title";
    private static final String ASSESSMENT_SESSION_ID = "latest_session_id";
    private static final String ASSESSMENT_TIMESPENT = "collection_time_spent";
    private static final String ASSESSMENT_SCORE = "collection_score";
    private static final String ASSESSMENT_REACTION = "collection_average_reaction";

  }


}
