package org.gooru.ds.user.processor.learnervectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class LearnerVectorsModelMapper implements ResultSetMapper<LearnerVectorsModel> {


  @Override
  public LearnerVectorsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    LearnerVectorsModel model = new LearnerVectorsModel();
    model.setAuthority(r.getFloat(MapperFields.AUTHORITY));
    model.setCitizenship(r.getFloat(MapperFields.CITIZENSHIP));
    model.setGrit(r.getFloat(MapperFields.GRIT));
    model.setMotivation(r.getFloat(MapperFields.MOTIVATION));
    model.setPerseverance(r.getFloat(MapperFields.PERSEVERANCE));
    model.setSelfConfidence(r.getFloat(MapperFields.SELF_CONFIDENCE));
    model.setReputation(r.getFloat(MapperFields.REPUTATION));
    return model;
  }



  private static final class MapperFields {

    private MapperFields() {
      throw new AssertionError();
    }

    private static final String AUTHORITY = "authority";
    private static final String CITIZENSHIP = "citizenship";
    private static final String REPUTATION = "reputation";
    private static final String GRIT = "grit";
    private static final String PERSEVERANCE = "perseverance";
    private static final String MOTIVATION = "motivation";
    private static final String SELF_CONFIDENCE = "self_confidence";
  }

}
