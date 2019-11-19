package org.gooru.ds.user.processor.user.portfolio.competency;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 */
public class UserCompetencyPortfolioModelMapper 
implements ResultSetMapper<UserCompetencyPortfolioModel> {

  @Override
  public UserCompetencyPortfolioModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserCompetencyPortfolioModel model = new UserCompetencyPortfolioModel();
    model.setId(r.getString(MapperFields.COLLECTION_ID));
    model.setScore(r.getDouble(MapperFields.COLLECTION_SCORE));
    model.setContentSource(r.getString(MapperFields.CONTENT_SOURCE));
    model.setSessionId(r.getString(MapperFields.COLLECTION_SESSION_ID));
    model.setActivityTimestamp(r.getTimestamp(MapperFields.CREATED_AT));
    model.setUpdatedAt(r.getTimestamp(MapperFields.UPDATED_AT));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    // generically referred to as collection_id in analytics DB
    private static final String COLLECTION_ID = "collection_id";

    // private static final String COLLECTION_TIMESPENT = "collection_time_spent";
    private static final String COLLECTION_SCORE = "collection_score";
    private static final String CONTENT_SOURCE = "content_source";
    private static final String COLLECTION_SESSION_ID = "latest_session_id";
    private static final String UPDATED_AT = "updated_at";
    private static final String CREATED_AT = "created_at";

  }

}
