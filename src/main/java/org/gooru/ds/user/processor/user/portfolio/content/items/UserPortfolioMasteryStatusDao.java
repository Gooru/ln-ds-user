package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
interface UserPortfolioMasteryStatusDao {

  @Mapper(UserPortfolioMasteryStatusModelMapper.class)
  @SqlQuery("select distinct on (gut_code, collection_id) cm.tx_comp_display_code, gut_code,collection_id,collection_type, first_value(status) OVER (PARTITION BY gut_code,collection_id ORDER BY updated_at desc) as status from domain_competency_matrix cm left join learner_profile_competency_evidence_ts lpces on  cm.tx_comp_code = lpces.gut_code WHERE user_id = :userId AND collection_id = ANY(:collectionIds)")
  List<UserPortfolioMasteryStatusModel> fetchMasteryStatus(
      @Bind("userId") String userId, @Bind("collectionIds") PGArray<String> pgArray);
  
}
