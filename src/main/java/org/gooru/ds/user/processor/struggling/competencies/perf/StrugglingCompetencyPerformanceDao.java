
package org.gooru.ds.user.processor.struggling.competencies.perf;

import java.sql.Timestamp;
import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 12-Nov-2019
 */
public interface StrugglingCompetencyPerformanceDao {

  @Mapper(StrugglingCompetencyPerformanceModelMapper.class)
  @SqlQuery("SELECT cs.score, cs.user_id, cs.collection_id, cs.updated_at FROM (SELECT DISTINCT ON (user_id) FIRST_VALUE(collection_score)"
      + " OVER (PARTITION BY user_id ORDER BY updated_at desc) as score, user_id, collection_id, updated_at FROM"
      + " learner_profile_competency_evidence_ts WHERE gut_code = :competency AND user_id = ANY(:userIds) AND collection_type = 'assessment'"
      + " AND status = 1 ORDER BY user_id, updated_at DESC) as cs")
  public List<StrugglingCompetencyPerformanceModel> fetchCompetencyPerformance(
      @Bind("userIds") PGArray<String> userIds, @Bind("competency") String competency);

  @SqlQuery("SELECT user_id FROM struggling_competencies_details WHERE tx_comp_code = :competency AND user_id = ANY(:userIds::text[]) AND"
      + " updated_at < :toDate")
  public List<String> fetchUsersOfStrugglingCompetency(@Bind("userIds") PGArray<String> userIds,
      @Bind("competency") String competency, @Bind("toDate") Timestamp toDate);
}
