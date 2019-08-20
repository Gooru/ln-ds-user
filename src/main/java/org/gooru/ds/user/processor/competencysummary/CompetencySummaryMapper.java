package org.gooru.ds.user.processor.competencysummary;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 */
public class CompetencySummaryMapper implements ResultSetMapper<CompetencySummaryModel> {

  @Override
  public CompetencySummaryModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    CompetencySummaryModel model = new CompetencySummaryModel();

    model.setGradeId(r.getInt(MapperFields.GRADE_ID));
    model.setCompletedCompetencies(r.getInt(MapperFields.COMPLETED_COMPETENCIES));
    model.setInprogressCompetencies(r.getInt(MapperFields.IN_PROGRESS_COMPETENCIES));
    model.setTotalCompetencies(r.getInt(MapperFields.TOTAL_COMPETENCIES));
    model.setPercentCompletion(r.getDouble(MapperFields.PERCENT_COMPLETED));
    model.setPercentScore(r.getDouble(MapperFields.SCORE));
    model.setGrade(r.getString(MapperFields.GRADE));

    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String GRADE_ID = "grade_id";
    private static final String TOTAL_COMPETENCIES = "total";
    private static final String SCORE = "score";
    private static final String IN_PROGRESS_COMPETENCIES = "in_progress";
    private static final String COMPLETED_COMPETENCIES = "completed";
    private static final String PERCENT_COMPLETED = "percent_completed";
    private static final String GRADE = "grade";
  }


}
