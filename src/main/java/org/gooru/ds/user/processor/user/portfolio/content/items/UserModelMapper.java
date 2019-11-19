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
public class UserModelMapper implements ResultSetMapper<UserModel> {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserModelMapper.class);

  private static final String USERNAME = "username";
  private static final String ID = "id";
  private static final String FIRST_NAME = "first_name";
  private static final String LAST_NAME = "last_name";
  private static final String DISPLAY_NAME = "display_name";
  private static final String THUMBNAIL = "thumbnail";

  @Override
  public UserModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    UserModel model = new UserModel();
    model.setId(r.getString(ID));
    model.setFirstName(r.getString(FIRST_NAME));
    model.setLastName(r.getString(LAST_NAME));
    model.setDisplayName(r.getString(DISPLAY_NAME));
    model.setUsername(r.getString(USERNAME));
    model.setThumbnail(r.getString(THUMBNAIL));
    return model; 
  }

}
