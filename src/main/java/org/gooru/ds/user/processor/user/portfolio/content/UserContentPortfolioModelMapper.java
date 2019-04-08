package org.gooru.ds.user.processor.user.portfolio.content;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserContentPortfolioModelMapper   
implements ResultSetMapper<UserContentPortfolioModel> {

  @Override
  public UserContentPortfolioModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserContentPortfolioModel model = new UserContentPortfolioModel();
    
    model.setCollectionId(r.getString(MapperFields.COLLECTION_ID));
    model.setSessionId(r.getString(MapperFields.SESSION_ID));
    model.setCollectionType(r.getString(MapperFields.COLLECTION_TYPE));
    
    model.setClassId(r.getString(MapperFields.CLASS_ID));
    model.setCourseId(r.getString(MapperFields.COURSE_ID));
    model.setUnitId(r.getString(MapperFields.UNIT_ID));
    model.setLessonId(r.getString(MapperFields.LESSON_ID));
    model.setContentSource(r.getString(MapperFields.CONTENT_SOURCE));
    model.setPathType(r.getString(MapperFields.PATH_TYPE));
    model.setPathId(r.getInt(MapperFields.PATH_ID));
 
    model.setIsGraded(r.getBoolean(MapperFields.IS_GRADED));
    model.setScore(r.getDouble(MapperFields.COLLECTION_SCORE));
    model.setReaction(r.getInt(MapperFields.REACTION));
    model.setViews(r.getInt(MapperFields.VIEWS));
    model.setActivityTimestamp(r.getTimestamp(MapperFields.UPDATED_AT));
 
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    // generically referred to as collection_id in analytics DB
    private static final String COLLECTION_ID = "collection_id";
    private static final String SESSION_ID = "session_id";
    private static final String COLLECTION_TYPE = "collection_type";

    private static final String CLASS_ID = "class_id";
    private static final String COURSE_ID = "course_id";
    private static final String UNIT_ID = "unit_id";
    private static final String LESSON_ID = "lesson_id";
    private static final String CONTENT_SOURCE = "content_source";
    private static final String PATH_TYPE = "path_type";
    private static final String PATH_ID = "path_id";
    
    private static final String IS_GRADED = "is_graded";
    private static final String REACTION = "reaction";
    private static final String VIEWS = "views";
    private static final String COLLECTION_TIMESPENT = "timespent";
    private static final String COLLECTION_SCORE = "score";
    private static final String UPDATED_AT = "updated_at";
    
  }
}
