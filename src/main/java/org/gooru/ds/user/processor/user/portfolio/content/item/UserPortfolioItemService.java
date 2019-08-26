package org.gooru.ds.user.processor.user.portfolio.content.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author renuka
 */
public class UserPortfolioItemService {

  private final UserPortfolioItemPerfDao dao;

  public UserPortfolioItemService(DBI dbi) {
    this.dao = dbi.onDemand(UserPortfolioItemPerfDao.class);
  }

  public Map<String, Long> fetchDcaContentIdOfSpecifiedSession(List<String> contentIds) {
    Map<String, Long> contents = null;
    List<Map<String, Object>> contentModels = this.dao.fetchDcaContentIdOfSpecifiedSession(
        PGArrayUtils.convertFromListStringToSqlArrayOfString(contentIds));
    if (contentModels != null) {
      contents = new HashMap<>();
      for (Map<String, Object> contentModel : contentModels) {
        contents.put((String) contentModel.get("session_id"),
            (Long) contentModel.get("dca_content_id"));
      }
    }
    return contents;
  }

}
