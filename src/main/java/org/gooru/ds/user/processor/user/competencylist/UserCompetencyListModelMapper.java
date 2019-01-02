package org.gooru.ds.user.processor.user.competencylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserCompetencyListModelMapper implements ResultSetMapper<UserCompetencyListModel> {

  @Override
  public UserCompetencyListModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserCompetencyListModel model = new UserCompetencyListModel();
    model.setCompetencyCode(r.getString(MapperFields.COMPETENCY_CODE));
    model.setDisplayCode(r.getString(MapperFields.DISPLAY_CODE));
    model.setTitle(r.getString(MapperFields.COMPETENCY_TITLE));
    model.setFrameworkCode(r.getString(MapperFields.FRAMEWORK_CODE));
    model.setStatus(r.getString(MapperFields.STATUS));
    return model;
  }

  private static final class MapperFields {
    private MapperFields() {
      throw new AssertionError();
    }

    private static final String COMPETENCY_CODE = "competency_code";
    private static final String DISPLAY_CODE = "competency_display_code";
    private static final String COMPETENCY_TITLE = "competency_title";
    private static final String FRAMEWORK_CODE = "framework_code";
    private static final String STATUS = "status";

  }


}
