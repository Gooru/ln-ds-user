package org.gooru.ds.user.processor.user.portfolio.subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 */
public class SubjectModelMapper    
implements ResultSetMapper<SubjectModel> {

  @Override
  public SubjectModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    SubjectModel model = new SubjectModel();
    model.setCompCode(r.getString(MapperFields.COMPETENCY_CODE));
    model.setDomainCode(r.getString(MapperFields.DOMAIN_CODE));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String COMPETENCY_CODE = "tx_comp_code";
    private static final String DOMAIN_CODE = "tx_domain_code";
  }

}
