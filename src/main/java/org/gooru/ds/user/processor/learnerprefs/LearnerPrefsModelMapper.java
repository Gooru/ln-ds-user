package org.gooru.ds.user.processor.learnerprefs;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class LearnerPrefsModelMapper implements ResultSetMapper<LearnerPrefsModel> {

  private static final Float MILLION = 1_000_000F;
  public static final float ROUNDING_PLACES = 10000f;

  @Override
  public LearnerPrefsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    LearnerPrefsModel model = new LearnerPrefsModel();
    model.setAudio(normalizeFromMillion(r.getLong(MapperFields.AUDIO)));
    model.setInteractive(normalizeFromMillion(r.getLong(MapperFields.INTERACTIVE)));
    model.setText(normalizeFromMillion(r.getLong(MapperFields.TEXT)));
    model.setVideo(normalizeFromMillion(r.getLong(MapperFields.VIDEO)));
    model.setWebpage(normalizeFromMillion(r.getLong(MapperFields.WEBPAGE)));
    model.setImage(normalizeFromMillion(r.getLong(MapperFields.IMAGE)));
    return model;
  }



  private Float normalizeFromMillion(long value) {
    if (value == 0) {
      return 0f;
    }
    float result = value / MILLION;
    return (float) Math.round(result * ROUNDING_PLACES) / ROUNDING_PLACES;
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
  }

}
