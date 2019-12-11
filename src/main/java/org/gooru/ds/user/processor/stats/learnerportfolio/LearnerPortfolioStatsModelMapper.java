package org.gooru.ds.user.processor.stats.learnerportfolio;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class LearnerPortfolioStatsModelMapper
    implements ResultSetMapper<LearnerPortfolioStatsModel> {


  @Override
  public LearnerPortfolioStatsModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    LearnerPortfolioStatsModel model = new LearnerPortfolioStatsModel();
    model.setSubjectCode(r.getString(MapperFields.SUBJECT_CODE));
    model.setSubjectName(r.getString(MapperFields.SUBJECT_NAME));
    model.setClassificationCode(r.getString(MapperFields.CLASSIFICATION_CODE));
    model.setCollectionCount(r.getInt(MapperFields.COLLECTION_COUNT));
    model.setCollectionType(r.getString(MapperFields.COLLECTION_TYPE));
    model.setClassificationSeq(r.getInt(MapperFields.CLASSIFICATION_SEQ));
    model.setSubjectSeq(r.getInt(MapperFields.SUBJECT_SEQ));
    model.setClassificationName(r.getString(MapperFields.CLASSIFICATION_NAME));
    return model;
  }



  static final class MapperFields {

    private MapperFields() {
      throw new AssertionError();
    }

    static final String SUBJECT_CODE = "tx_subject_code";
    static final String SUBJECT_NAME = "tx_subject_name";
    static final String COLLECTION_COUNT = "collection_count";
    static final String ASSESSMENT_COUNT = "assessment_count";
    static final String OA_COUNT = "oa_count";
    static final String CLASSIFICATION_CODE = "tx_classification_code";
    static final String CLASSIFICATION_NAME = "tx_classification_name";
    static final String COLLECTION_TYPE = "collection_type";
    static final String CLASSIFICATION_SEQ = "classification_seq";
    static final String SUBJECT_SEQ = "subject_seq";


  }

}
