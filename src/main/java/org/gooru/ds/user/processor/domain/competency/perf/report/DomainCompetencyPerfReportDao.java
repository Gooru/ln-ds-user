
package org.gooru.ds.user.processor.domain.competency.perf.report;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public interface DomainCompetencyPerfReportDao {

  @Mapper(UserCompetencyPerformanceModelMapper.class)
  @SqlQuery("SELECT DISTINCT ON (user_id, gut_code) collection_score, user_id, status FROM learner_profile_competency_evidence_ts where user_id ="
      + " ANY(:userIds) AND class_id = :classId AND gut_code = :txCode AND updated_at < :toDate order by user_id, gut_code, status desc")
  List<UserCompetencyPerformanceModel> fetchCompetencyPerfByClassAndGut(
      @BindBean DomainCompetencyPerfReportCommand.DomainCompetencyPerfReportCommandBean bean,
      @Bind("userIds") PGArray<String> userIds);
}
