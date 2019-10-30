
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 17-Oct-2019
 */
public class UserModelMapper implements ResultSetMapper<UserModel> {

  @Override
  public UserModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    UserModel model = new UserModel();
    model.setId(r.getString("id"));
    model.setFirstName(r.getString("first_name"));
    model.setLastName(r.getString("last_name"));
    model.setUsername(r.getString("username"));
    model.setDisplayName(r.getString("display_name"));
    model.setThumbnail(r.getString("thumbnail"));
    return model;
  }

}
