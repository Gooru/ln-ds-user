
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru
 * Created On 18-Jan-2019
 */
public class GradeCompetencyBoundMapper implements ResultSetMapper<GradeCompetencyBound> {

  @Override
  public GradeCompetencyBound map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    GradeCompetencyBound gcb = new GradeCompetencyBound();
    gcb.setDomainCode(r.getString("tx_domain_code"));
    gcb.setHighlineCode(r.getString("highline_tx_comp_code"));
    gcb.setLowlineCode(r.getString("lowline_tx_comp_code"));
    return gcb;
  }

}
