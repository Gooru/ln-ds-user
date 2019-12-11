package org.gooru.ds.user.processor.learnervectors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class LearnerVectorsModelMapper implements ResultSetMapper<LearnerVectorsModel> {


  @Override
  public LearnerVectorsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    LearnerVectorsModel model = new LearnerVectorsModel();
    model.setAuthority(numberPrecision(r.getDouble(MapperFields.AUTHORITY)));
    model.setCitizenship(numberPrecision(r.getDouble(MapperFields.CITIZENSHIP)));
    model.setGrit(numberPrecision(r.getDouble(MapperFields.GRIT)));
    model.setMotivation(numberPrecision(r.getDouble(MapperFields.MOTIVATION)));
    model.setPerseverance(numberPrecision(r.getDouble(MapperFields.PERSEVERANCE)));
    model.setSelfConfidence(numberPrecision(r.getDouble(MapperFields.SELF_CONFIDENCE)));
    model.setReputation(numberPrecision(r.getDouble(MapperFields.REPUTATION)));
    return model;
  }

  private Float numberPrecision(Double value) {
    BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    return bd.floatValue();
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
