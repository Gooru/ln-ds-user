package org.gooru.ds.user.processor.competencymatrixcoordinates;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 17/1/18.
 */
public class CompetencyMatrixCoordinatesDomainModelMapper
    implements ResultSetMapper<CompetencyMatrixCoordinatesDomainModel> {

  @Override
  public CompetencyMatrixCoordinatesDomainModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    CompetencyMatrixCoordinatesDomainModel model = new CompetencyMatrixCoordinatesDomainModel();
    model.setDomainCode(r.getString(MapperFields.DOMAIN_CODE));
    model.setDomainName(r.getString(MapperFields.DOMAIN_NAME));
    model.setDomainSeq(r.getInt(MapperFields.DOMAIN_SEQ));
    return model;
  }

  static class MapperFields {
    public static final String DOMAIN_CODE = "tx_domain_code";
    public static final String DOMAIN_NAME = "tx_domain_name";
    public static final String DOMAIN_SEQ = "tx_domain_seq";

    private MapperFields() {
      throw new AssertionError();
    }
  }
}
