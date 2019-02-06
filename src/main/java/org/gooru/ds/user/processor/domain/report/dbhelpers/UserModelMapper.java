
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class UserModelMapper implements ResultSetMapper<UserModel> {

  @Override
  public UserModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    UserModel user = new UserModel();
    user.setId(r.getString("id"));
    user.setFirstName(r.getString("first_name"));
    user.setLastName(r.getString("last_name"));
    user.setThumbnail(r.getString("thumbnail"));
    return user;
  }

}
