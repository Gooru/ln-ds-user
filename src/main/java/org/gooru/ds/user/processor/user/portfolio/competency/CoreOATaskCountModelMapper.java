package org.gooru.ds.user.processor.user.portfolio.competency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
public class CoreOATaskCountModelMapper implements ResultSetMapper<Map<String, Object>> {
  private static final Logger LOGGER = LoggerFactory.getLogger(CoreOATaskCountModelMapper.class);

  private static final String TASK_COUNT = "task_count";
  private static final String ID = "id";

  @Override
  public Map<String, Object> map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    Map<String, Object> model = new HashMap<>();
    model.put(ID, r.getString(ID));
    model.put(TASK_COUNT, r.getInt(TASK_COUNT));
    return model; 
  }

}
