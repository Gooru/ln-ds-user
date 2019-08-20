package org.gooru.ds.user.processor.content.portfolio.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
public class CoreOATaskCountModelMapper implements ResultSetMapper<CoreOATaskCountModel> {
  private static final Logger LOGGER = LoggerFactory.getLogger(CoreOATaskCountModelMapper.class);

  private static final String TASK_COUNT = "task_count";
  private static final String ID = "id";

  @Override
  public CoreOATaskCountModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    CoreOATaskCountModel model = new CoreOATaskCountModel();
    model.setId(r.getString(ID));
    model.setTaskCount(r.getInt(TASK_COUNT));
    return model; 
  }

}
