package org.gooru.ds.user.processor.baselearnerprofile;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 * 
 */
public class LearnerProfileReadModelMapper implements ResultSetMapper<LearnerProfileReadModel> {

  @Override
  public LearnerProfileReadModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    LearnerProfileReadModel model = new LearnerProfileReadModel();
    model.setSubjectCode(r.getString(MapperFields.TX_SUBJECT_CODE));
    model.setStatus(r.getInt(MapperFields.STATUS));
    model.setUserId(r.getString(MapperFields.USER_ID));
    model.setCompetencyCode(r.getString(MapperFields.GUT_CODE));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String TX_SUBJECT_CODE = "tx_subject_code";
    private static final String STATUS = "status";
    private static final String USER_ID = "user_id";
    private static final String GUT_CODE = "gut_code";

  }

}
