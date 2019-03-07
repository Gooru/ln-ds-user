
package org.gooru.ds.user.processor.domain.competency.perf.report;

import java.sql.Timestamp;
import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public interface DomainCompetencyCompletionReportDao {

  @Mapper(UserCompetencyCompletionModelMapper.class)
  @SqlQuery("SELECT DISTINCT ON (user_id, gut_code) gut_code, user_id, status FROM learner_profile_competency_status_ts where user_id ="
      + " ANY(:userIds) AND gut_code = :txCode AND updated_at < :toDate order by user_id, gut_code, status desc")
  List<UserCompetencyCompletionModel> fetchCompetencyCompletionByGut(
      @BindBean DomainCompetencyCompletionReportCommand.DomainCompetencyCompletionReportCommandBean bean,
      @Bind("userIds") PGArray<String> userIds);

  @SqlQuery("SELECT DISTINCT user_id FROM learner_profile_competency_status_ts WHERE user_id = ANY(:userIds) AND gut_code =  ANY(:gutCodes)"
      + " AND updated_at < :toDate AND status >= 4")
  List<String> fetchInferredCompletion(@Bind("userIds") PGArray<String> userIds,
      @Bind("gutCodes") PGArray<String> gutCodes, @Bind("toDate") Timestamp toDate);

  @SqlQuery("SELECT tx_comp_code FROM domain_competency_matrix WHERE tx_comp_seq > (SELECT tx_comp_seq FROM domain_competency_matrix"
      + " WHERE tx_comp_code = :compCode) AND tx_subject_code = :subject AND tx_domain_code = :domain")
  List<String> fetchHigherCompetencies(@Bind("compCode") String compCode,
      @Bind("subject") String subject, @Bind("domain") String domain);

}
