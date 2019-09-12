package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author renuka
 */
public class CoreCollectionsService {

  private final CoreCollectionsDao dao;

  public CoreCollectionsService(DBI dbi) {
    this.dao = dbi.onDemand(CoreCollectionsDao.class);
  }

  public Map<String, CoreCollectionsModel> fetchCollectionMeta(List<String> contentIds) {
    Map<String, CoreCollectionsModel> contents = new HashMap<>();
    List<CoreCollectionsModel> contentModels =
        this.dao.fetchCollectionMeta(PGArrayUtils.convertFromListStringToSqlArrayOfString(contentIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model);
      });
    }
    return contents;
  }
  
  public Map<String, CoreCollectionItemCountsModel> fetchCollectionItemCount(List<String> collectionIds) {
    Map<String, CoreCollectionItemCountsModel> contents = new HashMap<>();
    List<CoreCollectionItemCountsModel> contentModels =
        this.dao.fetchCollectionItemCounts(PGArrayUtils.convertFromListStringToSqlArrayOfString(collectionIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model);
      });
    }
    return contents;
  }
  
  public Map<String, Integer> fetchOATaskCount(List<String> collectionIds) {
    Map<String, Integer> contents = null;
    List<Map<String, Object>> contentModels =
        this.dao.fetchOATaskCounts(PGArrayUtils.convertFromListStringToSqlArrayOfString(collectionIds));
    if (contentModels != null) {
      contents = new HashMap<>();
      for (Map<String, Object> contentModel : contentModels) {
        contents.put((String) contentModel.get("id"), (Integer) contentModel.get("task_count"));
      }
    }
    return contents;
  }

}
