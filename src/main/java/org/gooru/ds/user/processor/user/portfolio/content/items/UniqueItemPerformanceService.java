package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author renuka
 */
public class UniqueItemPerformanceService {

  private final UserPortfolioUniqueItemPerfDao dao;

  public UniqueItemPerformanceService(DBI dbi) {
    this.dao = dbi.onDemand(UserPortfolioUniqueItemPerfDao.class);
  }

  public Map<String, Long> fetchCumulativeTimespentForCollection(String userId, List<String> collectionIds) {
    Map<String, Long> collections = new HashMap<>();
    List<TimespentModel> collectionModels = this.dao.fetchCumulativeTimespentForCollection(userId,
        PGArrayUtils.convertFromListStringToSqlArrayOfString(collectionIds));
    if (collectionModels != null) {
      collectionModels.forEach(model -> {
        collections.put(model.getId(), model.getTimespent());
      });
    }
    return collections;
  }
}
