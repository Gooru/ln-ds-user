package org.gooru.ds.user.processor.user.portfolio.competency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author renuka
 */
public class CoreCollectionsModelMapper implements ResultSetMapper<CoreCollectionsModel> {
  private static final Logger LOGGER = LoggerFactory.getLogger(CoreCollectionsModelMapper.class);

  private static final String ID = "id";
  private static final String TITLE = "title";
  private static final String SUB_FORMAT = "subformat";
  private static final String FORMAT = "format";
  private static final String TAXONOMY = "taxonomy";
  private static final String LEARNING_OBJECTIVE = "learning_objective";
  private static final String THUMBNAIL = "thumbnail";
  private static final String GUT_CODES = "gut_codes";

  @Override
  public CoreCollectionsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    CoreCollectionsModel model = new CoreCollectionsModel();
    model.setId(r.getString(ID));
    model.setTitle(r.getString(TITLE));
    model.setSubType(r.getString(SUB_FORMAT));
    model.setType(r.getString(FORMAT));
    model.setLearningObjective(r.getString(LEARNING_OBJECTIVE));
    model.setThumbnail(r.getString(THUMBNAIL));
    model.setTaxonomy(r.getString(TAXONOMY) != null ? new JsonObject(r.getString(TAXONOMY)) : null);
    model.setGutCodes(r.getArray(GUT_CODES) != null ? Arrays.asList((String[]) r.getArray(GUT_CODES).getArray()) : null);
    return model; 
  }

}
