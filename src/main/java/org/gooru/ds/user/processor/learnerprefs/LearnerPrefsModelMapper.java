package org.gooru.ds.user.processor.learnerprefs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class LearnerPrefsModelMapper implements ResultSetMapper<LearnerPrefsModel> {


  @Override
  public LearnerPrefsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    LearnerPrefsModel model = new LearnerPrefsModel();
    model.setAudio(numberPrecision(r.getDouble(MapperFields.AUDIO)));
    model.setInteractive(numberPrecision(r.getDouble(MapperFields.INTERACTIVE)));
    model.setText(numberPrecision(r.getDouble(MapperFields.TEXT)));
    model.setVideo(numberPrecision(r.getDouble(MapperFields.VIDEO)));
    model.setWebpage(numberPrecision(r.getDouble(MapperFields.WEBPAGE)));
    model.setImage(numberPrecision(r.getDouble(MapperFields.IMAGE)));
    model.setProject(numberPrecision(r.getDouble(MapperFields.PROJECT)));
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

    private static final String INTERACTIVE = "interactive_pref";
    private static final String AUDIO = "audio_pref";
    private static final String WEBPAGE = "webpage_pref";
    private static final String VIDEO = "video_pref";
    private static final String TEXT = "text_pref";
    private static final String IMAGE = "image_pref";
    private static final String PROJECT = "project_pref";
  }

}
