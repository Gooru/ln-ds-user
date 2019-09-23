package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.HashMap;
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
    List<CoreCollectionsModel> contentModels = this.dao
        .fetchCollectionMeta(PGArrayUtils.convertFromListStringToSqlArrayOfString(contentIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model);
      });
    }
    return contents;
  }

  public Map<String, CoreCollectionItemCountsModel> fetchCollectionItemCount(
      List<String> collectionIds) {
    Map<String, CoreCollectionItemCountsModel> contents = new HashMap<>();
    List<CoreCollectionItemCountsModel> contentModels = this.dao.fetchCollectionItemCounts(
        PGArrayUtils.convertFromListStringToSqlArrayOfString(collectionIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model);
      });
    }
    return contents;
  }

  public Map<String, Integer> fetchOATaskCount(List<String> collectionIds) {
    Map<String, Integer> contents = new HashMap<>();
    List<CountInfoModel> contentModels = this.dao
        .fetchOATaskCounts(PGArrayUtils.convertFromListStringToSqlArrayOfString(collectionIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model.getCount());
      });
    }
    return contents;
  }

  public Map<String, REEfInfoModel> fetchREEfInfo(List<String> collectionIds) {
    Map<String, REEfInfoModel> contents = new HashMap<>();
    List<REEfInfoModel> contentModels = this.dao
        .fetchREEfInfo(PGArrayUtils.convertFromListStringToSqlArrayOfString(collectionIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model);
      });
    }
    return contents;
  }

}
