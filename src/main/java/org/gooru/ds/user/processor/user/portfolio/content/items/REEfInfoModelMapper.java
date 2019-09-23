package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
public class REEfInfoModelMapper implements ResultSetMapper<REEfInfoModel> {
  private static final Logger LOGGER = LoggerFactory.getLogger(REEfInfoModelMapper.class);

  private static final String RELEVANCE = "relevance";
  private static final String ENGAGEMENT = "engagement";
  private static final String EFFICACY = "efficacy";
  private static final String ID = "item_id";

  @Override
  public REEfInfoModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    REEfInfoModel model = new REEfInfoModel();
    model.setId(r.getString(ID));
    model.setEngagement(r.getFloat(ENGAGEMENT));
    model.setEfficacy(r.getFloat(EFFICACY));
    model.setRelevance(r.getFloat(RELEVANCE));
    return model;
  }

}
