package org.gooru.ds.user.processor.user.portfolio.competency;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
public class CoreCollectionItemsModelMapper implements ResultSetMapper<CoreCollectionItemCountsModel> {
  private static final Logger LOGGER = LoggerFactory.getLogger(CoreCollectionItemsModelMapper.class);

  private static final String QUESTION_COUNT = "question_count";
  private static final String RESOURCE_COUNT = "resource_count";
  private static final String COLLECTION_ID = "collection_id";

  @Override
  public CoreCollectionItemCountsModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    CoreCollectionItemCountsModel model = new CoreCollectionItemCountsModel();
    model.setId(r.getString(COLLECTION_ID));
    model.setQuestionCount(r.getInt(QUESTION_COUNT));
    model.setResourceCount(r.getInt(RESOURCE_COUNT));
    return model; 
  }

}
