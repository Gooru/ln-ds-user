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
public class CountInfoModelMapper implements ResultSetMapper<CountInfoModel> {
  private static final Logger LOGGER = LoggerFactory.getLogger(CountInfoModelMapper.class);

  private static final String COUNT = "count";
  private static final String ID = "id";

  @Override
  public CountInfoModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    CountInfoModel model = new CountInfoModel();
    model.setId(r.getString(ID));
    model.setCount(r.getInt(COUNT));
    return model; 
  }

}
