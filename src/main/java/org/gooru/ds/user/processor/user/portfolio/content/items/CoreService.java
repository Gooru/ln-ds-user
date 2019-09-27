package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
public class CoreService {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(CoreService.class);
  private final CoreDao dao;

  public CoreService(DBI dbi) {
    this.dao = dbi.onDemand(CoreDao.class);
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
    Map<String, Integer> contents = new HashMap<>();
    List<CountInfoModel> contentModels =
        this.dao.fetchOATaskCounts(PGArrayUtils.convertFromListStringToSqlArrayOfString(collectionIds));
    if (contentModels != null) {
      contentModels.forEach(model -> {
        contents.put(model.getId(), model.getCount());
      });
    }
    return contents;
  }
  
  public Map<String, UserModel> fetchUserMeta(Set<String> userIds) {
    Map<String, UserModel> users = new HashMap<>();
    LOGGER.info("userIds : {}", PGArrayUtils.convertFromSetStringToSqlArrayOfString(userIds));
    List<UserModel> userModels =
        this.dao.fetchUsers(PGArrayUtils.convertFromSetStringToSqlArrayOfString(userIds));
    if (userModels != null) {
      userModels.forEach(model -> {
        users.put(model.getId(), model);
      });
    }
    LOGGER.info("users : {}", users.toString());
    return users;
  }

}
