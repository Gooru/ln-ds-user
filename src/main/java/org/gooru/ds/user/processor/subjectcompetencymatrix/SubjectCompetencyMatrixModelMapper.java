package org.gooru.ds.user.processor.subjectcompetencymatrix;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class SubjectCompetencyMatrixModelMapper
    implements ResultSetMapper<SubjectCompetencyMatrixModel> {

  @Override
  public SubjectCompetencyMatrixModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    SubjectCompetencyMatrixModel model = new SubjectCompetencyMatrixModel();
    model.setCompetencyCount(r.getInt(MapperFields.COMPETENCY_COUNT));
    model.setCompetencyStatus(r.getInt(MapperFields.STATUS));
    model.setClassificationCode(r.getString(MapperFields.CLASSIFICATION_CODE));
    model.setSubjectCode(r.getString(MapperFields.SUBJECT_CODE));
    model.setSubjectName(r.getString(MapperFields.SUBJECT_NAME));
    return model;
  }

  static class MapperFields {
    static final String SUBJECT_CODE = "tx_subject_code";
    static final String CLASSIFICATION_CODE = "tx_classification_id";
    static final String STATUS = "status";
    static final String COMPETENCY_COUNT = "competency_count";
    static final String SUBJECT_NAME = "tx_subject_name";
    static final String COMPETENCY_STATS  = "competency_stats";

    private MapperFields() {
      throw new AssertionError();
    }
  }
}
