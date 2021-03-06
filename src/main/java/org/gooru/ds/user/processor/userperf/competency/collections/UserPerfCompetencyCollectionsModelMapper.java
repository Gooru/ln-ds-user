package org.gooru.ds.user.processor.userperf.competency.collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserPerfCompetencyCollectionsModelMapper
    implements ResultSetMapper<UserPerfCompetencyCollectionsModel> {

  @Override
  public UserPerfCompetencyCollectionsModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserPerfCompetencyCollectionsModel model = new UserPerfCompetencyCollectionsModel();
    model.setId(r.getString(MapperFields.COLLECTION_ID));
    model.setSessionId(r.getString(MapperFields.COLLECTION_SESSION_ID));
    model.setCollectionType(r.getString(MapperFields.COLLECTION_TYPE));
    model.setScore(r.getDouble(MapperFields.COLLECTION_SCORE));
    model.setClassId(r.getString(MapperFields.CLASS_ID));
    model.setCourseId(r.getString(MapperFields.COURSE_ID));
    model.setUnitId(r.getString(MapperFields.UNIT_ID));
    model.setLessonId(r.getString(MapperFields.LESSON_ID));
    model.setContentSource(r.getString(MapperFields.CONTENT_SOURCE));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    // generically referred to as collection_id in analytics DB
    private static final String COLLECTION_ID = "collection_id";
    private static final String COLLECTION_SESSION_ID = "latest_session_id";
    private static final String COLLECTION_TYPE = "collection_type";
    // private static final String COLLECTION_TIMESPENT = "collection_time_spent";
    private static final String COLLECTION_SCORE = "collection_score";
    private static final String CLASS_ID = "class_id";
    private static final String COURSE_ID = "course_id";
    private static final String UNIT_ID = "unit_id";
    private static final String LESSON_ID = "lesson_id";
    private static final String CONTENT_SOURCE = "content_source";

  }

}
