package org.gooru.ds.user.processor.competencycompletion.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author mukul@gooru
 *  
 */
public class CompetencyCompletionStatsModelMapper  implements ResultSetMapper<CompetencyCompletionStatsModel> {

  @Override
  public CompetencyCompletionStatsModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    CompetencyCompletionStatsModel model = new CompetencyCompletionStatsModel();

    model.setClassId(r.getString(MapperFields.CLASS_ID));
    model.setCompletedCompetencies(r.getInt(MapperFields.COMPLETED_COMPETENCIES));
    model.setTotalCompetencies(r.getInt(MapperFields.TOTAL_COMPETENCIES));

    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String CLASS_ID = "class_id";
    private static final String TOTAL_COMPETENCIES = "total";
    private static final String COMPLETED_COMPETENCIES = "completed";
  }

}