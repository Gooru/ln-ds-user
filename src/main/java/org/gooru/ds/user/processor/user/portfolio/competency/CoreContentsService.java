package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;

/**
 * @author renuka
 */
public class CoreContentsService {

  private final CoreCollectionsDao dao;

  public CoreContentsService(DBI dbi) {
    this.dao = dbi.onDemand(CoreCollectionsDao.class);
  }

  public Map<String, CoreCollectionsModel> fetchCollectionMeta(List<String> contentIds) {
    Map<String, CoreCollectionsModel> contents = new HashMap<>();
    List<CoreCollectionsModel> contentModels =
        this.dao.fetchCollectionMeta(toPostgresArrayString(contentIds));
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
        this.dao.fetchCollectionItemCounts(toPostgresArrayString(collectionIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model);
      });
    }
    return contents;
  }
  
  public Map<String, Integer> fetchOATaskCount(List<String> collectionIds) {
    Map<String, Integer> contents = new HashMap<>();
    List<CoreOATaskCountModel> contentModels =
        this.dao.fetchOATaskCounts(toPostgresArrayString(collectionIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model.getTaskCount());
      });
    }
    return contents;
  }

  public static String toPostgresArrayString(List<String> input) {
    Iterator<String> it = input.iterator();
    if (!it.hasNext()) {
      return "{}";
    }

    StringBuilder sb = new StringBuilder();
    sb.append('{');
    for (;;) {
      String s = it.next();
      sb.append('"').append(s).append('"');
      if (!it.hasNext()) {
        return sb.append('}').toString();
      }
      sb.append(',');
    }
  }
}
