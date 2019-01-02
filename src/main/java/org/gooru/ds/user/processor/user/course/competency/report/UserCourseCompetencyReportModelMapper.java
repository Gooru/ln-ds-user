package org.gooru.ds.user.processor.user.course.competency.report;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru on 17-Jul-2018
 */
public class UserCourseCompetencyReportModelMapper
    implements ResultSetMapper<UserCourseCompetencyReportModel> {

  @Override
  public UserCourseCompetencyReportModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    UserCourseCompetencyReportModel model = new UserCourseCompetencyReportModel();
    model.setDomainCode(r.getString(MapperFields.DOMAIN_CODE));
    model.setCompetencyCode(r.getString(MapperFields.COMPETENCY_CODE));
    model.setCompetencyName(r.getString(MapperFields.COMPETENCY_NAME));
    model.setCompetencyDesc(r.getString(MapperFields.COMPETENCY_DESC));
    model.setCompetencyStudentDesc(r.getString(MapperFields.COMPETENCY_STUDENT_DESC));
    model.setCompetencySeq(r.getInt(MapperFields.COMPETENCY_SEQ));
    model.setStatus(r.getInt(MapperFields.STATUS));
    return model;
  }

  static class MapperFields {
    static final String DOMAIN_CODE = "tx_domain_code";
    static final String COMPETENCY_CODE = "tx_comp_code";
    static final String COMPETENCY_NAME = "tx_comp_name";
    static final String COMPETENCY_DESC = "tx_comp_desc";
    static final String COMPETENCY_STUDENT_DESC = "tx_comp_student_desc";
    static final String COMPETENCY_SEQ = "tx_comp_seq";
    static final String STATUS = "status";
    static final String DOMAIN_NAME = "tx_domain_name";
    static final String DOMAIN_SEQ = "tx_domain_seq";

    private MapperFields() {
      throw new AssertionError();
    }
  }

}
