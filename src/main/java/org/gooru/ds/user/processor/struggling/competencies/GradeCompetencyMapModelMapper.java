
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru Created On 17-Oct-2019
 */
public class GradeCompetencyMapModelMapper implements ResultSetMapper<GradeCompetencyMapModel> {

  @Override
  public GradeCompetencyMapModel map(int index, ResultSet r, StatementContext ctx)
      throws SQLException {
    GradeCompetencyMapModel model = new GradeCompetencyMapModel();
    model.setGradeId(r.getLong("grade_id"));
    model.setDomainId(r.getLong("tx_domain_id"));
    model.setDomainCode(r.getString("tx_domain_code"));
    model.setDomainName(r.getString("tx_domain_name"));
    model.setDomainSeq(r.getInt("tx_domain_seq"));
    model.setCompCode(r.getString("tx_comp_code"));
    model.setCompName(r.getString("tx_comp_name"));
    model.setCompStudentDesc(r.getString("tx_comp_student_desc"));
    model.setCompSeq(r.getInt("tx_comp_seq"));

    return model;
  }

}
