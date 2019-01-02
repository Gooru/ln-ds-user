package org.gooru.ds.user.processor.atc.pvc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 */
public class UserSkylineModelMapper implements ResultSetMapper<UserSkylineModel> {

  @Override
  public UserSkylineModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    UserSkylineModel model = new UserSkylineModel();
    model.setDomainCode(r.getString(MapperFields.DOMAIN_CODE));
    model.setCompetencyCode(r.getString(MapperFields.COMPETENCY_CODE));
    model.setCompetencySeq(r.getInt(MapperFields.COMPETENCY_SEQ));
    model.setStatus(r.getInt(MapperFields.STATUS));
    return model;
  }

  static class MapperFields {
    static final String DOMAIN_CODE = "tx_domain_code";
    static final String COMPETENCY_CODE = "tx_comp_code";
    static final String COMPETENCY_SEQ = "tx_comp_seq";
    static final String STATUS = "status";

    private MapperFields() {
      throw new AssertionError();
    }
  }

}
