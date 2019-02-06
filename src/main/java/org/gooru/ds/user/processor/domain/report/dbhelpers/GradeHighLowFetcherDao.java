
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 18-Jan-2019
 */
public interface GradeHighLowFetcherDao {

  @SqlQuery("SELECT tx_comp_code FROM domain_competency_matrix WHERE tx_domain_code = :domainCode AND tx_subject_code = :subjectCode"
      + " ORDER BY tx_comp_seq asc limit 1")
  String fetchFirstCompetencyByDomainAndSubject(@Bind("domainCode") String domainCode,
      @Bind("subjectCode") String subjectCode);

  @Mapper(GradeCompetencyBoundMapper.class)
  @SqlQuery("SELECT tx_domain_code, lowline_tx_comp_code, highline_tx_comp_code FROM grade_competency_bound WHERE grade_id = :gradeId")
  List<GradeCompetencyBound> fetchHighLowLinesByGrade(@Bind("gradeId") Integer gradeId);
}
