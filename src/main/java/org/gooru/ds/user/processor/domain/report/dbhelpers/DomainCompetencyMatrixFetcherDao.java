
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import java.sql.Timestamp;
import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.gooru.ds.user.processor.domain.report.DomainCompetencyCompletionModel;
import org.gooru.ds.user.processor.domain.report.DomainCompetencyCompletionModelMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 29-Jan-2019
 */
public interface DomainCompetencyMatrixFetcherDao {

  @Mapper(DomainCompetencyMatrixModelMapper.class)
  @SqlQuery("select distinct(cm.tx_comp_code), cm.tx_domain_code, cm.tx_comp_name, cm.tx_comp_desc, cm.tx_comp_student_desc, cm.tx_comp_seq,"
      + " (SELECT DISTINCT ON (lpcs.gut_code) FIRST_VALUE(lpcs.status) OVER (PARTITION BY lpcs.gut_code ORDER BY lpcs.status desc) as"
      + " status FROM learner_profile_competency_status_ts lpcs where lpcs.user_id = :userId and lpcs.gut_code = ucm.gut_code and "
      + " lpcs.updated_at < :toDate) as status from domain_competency_matrix cm left"
      + " join learner_profile_competency_status_ts ucm on cm.tx_subject_code = ucm.tx_subject_code and cm.tx_comp_code = ucm.gut_code and"
      + " ucm.user_id = :userId and ucm.updated_at < :toDate where"
      + " cm.tx_subject_code = :subject order by cm.tx_domain_code, cm.tx_comp_seq asc")
  List<DomainCompetencyMatrixModel> fetchUserDomainCompetencyMatrix(@Bind("userId") String userId,
      @Bind("subject") String subject, @Bind("toDate") Timestamp toDate);

  @Mapper(DomainCompetencyMatrixModelMapper.class)
  @SqlQuery("SELECT dcm.tx_comp_code, dcm.tx_domain_code, dcm.tx_comp_name, dcm.tx_comp_desc, dcm.tx_comp_student_desc, dcm.tx_comp_seq,"
      + " d.tx_domain_name, d.tx_domain_seq FROM domain_competency_matrix dcm, tx_domains d WHERE dcm.tx_subject_code = :subjectCode AND"
      + " d.tx_subject_code = dcm.tx_subject_code AND d.tx_domain_code = dcm.tx_domain_code ORDER BY d.tx_domain_seq, dcm.tx_comp_seq")
  List<DomainCompetencyMatrixModel> fetchAllDomainCompetencies(
      @Bind("subjectCode") String subjectCode);

  @Mapper(DomainCompetencyCompletionModelMapper.class)
  @SqlQuery("SELECT tx_domain_code, tx_domain_name, tx_domain_seq FROM tx_domains WHERE tx_subject_code = :subjectCode"
      + " AND tx_domain_code = ANY(:domainCodes) order by tx_domain_seq")
  List<DomainCompetencyCompletionModel> fetchDomains(@Bind("subjectCode") String subjectCode,
      @Bind("domainCodes") PGArray<String> domainCodes);
}
