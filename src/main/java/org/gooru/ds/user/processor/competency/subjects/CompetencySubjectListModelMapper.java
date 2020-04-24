package org.gooru.ds.user.processor.competency.subjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author mukul@gooru
 * 
 */
public class CompetencySubjectListModelMapper
    implements ResultSetMapper<CompetencySubjectListModel> {

  @Override
  public CompetencySubjectListModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    CompetencySubjectListModel model = new CompetencySubjectListModel();
    model.setId(r.getString(MapperFields.ID));
    model.setTitle(r.getString(MapperFields.TITLE));
    model.setDescription(r.getString(MapperFields.DESCRIPTION));
    model.setFrameworkId(r.getString(MapperFields.FRAMEWORK_ID));
    model.setCode(r.getString(MapperFields.CODE));
    model.setSequenceId(r.getInt(MapperFields.SEQUENCE_ID));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String FRAMEWORK_ID = "standard_framework_id";
    private static final String CODE = "code";
    private static final String SEQUENCE_ID = "sequence_id";

  }



}
