
package org.gooru.ds.user.processor.domain.report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 30-Jan-2019
 */
public class DomainCompetencyCompletionModelMapper
    implements ResultSetMapper<DomainCompetencyCompletionModel> {

  @Override
  public DomainCompetencyCompletionModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    DomainCompetencyCompletionModel dccm = new DomainCompetencyCompletionModel();
    dccm.setAvgCompletion(0);
    dccm.setCompetencies(new HashMap<>());
    
    DomainModel dm = new DomainModel();
    dm.setDomainCode(r.getString("tx_domain_code"));
    dm.setDomainName(r.getString("tx_domain_name"));
    dm.setDomainSeq(r.getInt("tx_domain_seq"));
    
    dccm.setDomain(dm);
    return dccm;
  }

}
