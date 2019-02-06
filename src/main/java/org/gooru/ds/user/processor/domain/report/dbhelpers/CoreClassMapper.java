
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 21-Jan-2019
 */
public class CoreClassMapper implements ResultSetMapper<CoreClass> {

  @Override
  public CoreClass map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    CoreClass cc = new CoreClass();
    cc.setGradeCurrent(r.getInt("grade_current"));
    cc.setPreference(new JsonObject(r.getString("preference")));
    return cc;
  }

}
