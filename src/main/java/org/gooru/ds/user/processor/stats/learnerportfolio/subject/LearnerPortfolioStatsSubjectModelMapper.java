package org.gooru.ds.user.processor.stats.learnerportfolio.subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class LearnerPortfolioStatsSubjectModelMapper
    implements ResultSetMapper<LearnerPortfolioStatsSubjectModel> {


  @Override
  public LearnerPortfolioStatsSubjectModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    LearnerPortfolioStatsSubjectModel model = new LearnerPortfolioStatsSubjectModel();
    model.setDomainCode(r.getString(MapperFields.DOMAIN_CODE));
    model.setDomainName(r.getString(MapperFields.DOMAIN_NAME));
    model.setDomainSeq(r.getInt(MapperFields.DOMAIN_SEQ));
    model.setCollectionCount(r.getInt(MapperFields.COLLECTION_COUNT));
    model.setCollectionType(r.getString(MapperFields.COLLECTION_TYPE));
    return model;
  }



  static final class MapperFields {

    private MapperFields() {
      throw new AssertionError();
    }

    static final String DOMAIN_CODE = "tx_domain_code";
    static final String DOMAIN_NAME = "tx_domain_name";
    static final String DOMAIN_SEQ = "tx_domain_seq";
    static final String COLLECTION_TYPE = "collection_type";
    static final String COLLECTION_COUNT = "collection_count";

  }

}
