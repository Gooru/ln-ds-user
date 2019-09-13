package org.gooru.ds.user.processor.user.portfolio.content.summary.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author renuka
 */
public class CoreContentsService {

  private final CoreContentsDao dao;

  public CoreContentsService(DBI dbi) {
    this.dao = dbi.onDemand(CoreContentsDao.class);
  }

  public Map<String, CoreContentsModel> fetchContentTitles(List<String> contentIds) {
    Map<String, CoreContentsModel> contents = new HashMap<>();
    List<CoreContentsModel> contentModels =
        this.dao.fetchContentTitles(PGArrayUtils.convertFromListStringToSqlArrayOfString(contentIds));
    contentModels.forEach(model -> {
      contents.put(model.getId(), model);
    });
    return contents;
  }

}
