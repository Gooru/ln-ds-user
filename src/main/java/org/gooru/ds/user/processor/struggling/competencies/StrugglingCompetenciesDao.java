
package org.gooru.ds.user.processor.struggling.competencies;

import java.sql.Timestamp;
import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 27-Sep-2019
 */
public interface StrugglingCompetenciesDao {

  @Mapper(GradeModelMapper.class)
  @SqlQuery("SELECT id, grade, grade_seq, description, fw_code, tx_subject_code FROM grade_master WHERE id = ANY(:gradeIds::bigint[])")
  public List<GradeModel> fetchGrades(@Bind("gradeIds") String gradeIds);

  @Mapper(GradeCompetencyMapModelMapper.class)
  @SqlQuery("SELECT grade_id, tx_domain_id, tx_domain_code, tx_domain_seq, tx_comp_code, tx_comp_name, tx_comp_student_desc, tx_comp_seq,"
      + " tx_domain_name FROM grade_competency_map WHERE grade_id = :gradeId::bigint order by tx_domain_seq, tx_comp_seq")
  public List<GradeCompetencyMapModel> fetchCompetencyMapByGrade(@Bind("gradeId") Long gradeId);

  @Mapper(StrugglingCompetencyModelMapper.class)
  @SqlQuery("SELECT tx_comp_code, user_id FROM struggling_competencies_details WHERE tx_comp_code = ANY(:compCodes::text[]) AND"
      + " user_id = ANY(:userIds::text[]) AND updated_at < :toDate")
  public List<StrugglingCompetencyModel> fetchStrugglingCompetencies(
      @Bind("compCodes") PGArray<String> compCodes, @Bind("userIds") PGArray<String> userIds,
      @Bind("toDate") Timestamp toDate);

  @Mapper(CompletedCompetencyModelMapper.class)
  @SqlQuery("SELECT user_id, gut_code FROM learner_profile_competency_status WHERE tx_subject_code = :subject AND gut_code = ANY(:compCodes::text[])"
      + " AND user_id = ANY(:userIds::text[]) AND status >= 4 AND updated_at < :toDate")
  public List<CompletedCompetencyModel> fetchCompetencyCompletionStatus(
      @Bind("subject") String subject, @Bind("compCodes") PGArray<String> compCodes,
      @Bind("userIds") PGArray<String> userIds, @Bind("toDate") Timestamp toDate);

  @Mapper(CompetencyModelMapper.class)
  @SqlQuery("SELECT tx_comp_display_code, tx_comp_code FROM domain_competency_matrix WHERE tx_comp_code = ANY(:compCodes::text[])"
      + " AND tx_subject_code = :subject")
  public List<CompetencyModel> fetchCompetencyDisplayCode(@Bind("subject") String subject,
      @Bind("compCodes") PGArray<String> compCodes);

}
