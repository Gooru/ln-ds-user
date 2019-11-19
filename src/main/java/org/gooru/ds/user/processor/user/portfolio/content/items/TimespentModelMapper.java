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
public class TimespentModelMapper implements ResultSetMapper<TimespentModel> {
  private static final Logger LOGGER = LoggerFactory.getLogger(TimespentModelMapper.class);

  private static final String TIMESPENT = "timespent";
  private static final String ID = "id";

  @Override
  public TimespentModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    TimespentModel model = new TimespentModel();
    model.setId(r.getString(ID));
    model.setTimespent(r.getLong(TIMESPENT));
    return model; 
  }

}
