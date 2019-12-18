package org.gooru.ds.user.processor.stats.learnerportfolio.subjectdomain;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class LearnerPortfolioStatsSubjectDomainModelMapper
    implements ResultSetMapper<LearnerPortfolioStatsSubjectDomainModel> {


  @Override
  public LearnerPortfolioStatsSubjectDomainModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    LearnerPortfolioStatsSubjectDomainModel model = new LearnerPortfolioStatsSubjectDomainModel();
    model.setCompetencyCode(r.getString(MapperFields.COMPETENCY_CODE));
    model.setCompetencyName(r.getString(MapperFields.COMPETENCY_NAME));
    model.setCollectionType(r.getString(MapperFields.COLLECTION_TYPE));
    model.setCompetencySeq(r.getInt(MapperFields.COMPETENCY_SEQ));
    model.setCollectionCount(r.getInt(MapperFields.COLLECTION_COUNT));
    return model;
  }



  static final class MapperFields {

    private MapperFields() {
      throw new AssertionError();
    }

    static final String COMPETENCY_CODE = "tx_comp_code";
    static final String COMPETENCY_NAME = "tx_comp_name";
    static final String COLLECTION_TYPE = "collection_type";
    static final String COMPETENCY_SEQ = "tx_comp_seq";
    static final String COLLECTION_COUNT = "collection_count";

  }

}
