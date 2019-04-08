package org.gooru.ds.user.processor.user.portfolio.content.perf.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
class UserPortfolioUniqueItemPerfService {

  private final UserPortfolioUniqueItemPerfDao userPortfolioUniqueItemPerfDao;
  private final CoreContentsService coreContentsService;
  private UserPortfolioUniqueItemPerfCommand command;
  private List<String> userIds;
  private Boolean showSuggestedContent;
  private Boolean aggregateByContentSource;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserPortfolioUniqueItemPerfService.class);
  private static final String COLLECTION = "collection";
  private static final String COMPLETE = "complete";
  private static final String USER_ID = "userId";
  private static final String USAGE_DATA = "usageData";

  UserPortfolioUniqueItemPerfService(DBI dbi, DBI coreDbi) {
    this.userPortfolioUniqueItemPerfDao = dbi.onDemand(UserPortfolioUniqueItemPerfDao.class);
    this.coreContentsService = new CoreContentsService(coreDbi);
  }

  public UserPortfolioUniqueItemPerfModelResponse fetchUserPortfolioUniqueItemPerf(
      UserPortfolioUniqueItemPerfCommand command) {

    this.command = command;
    userIds = this.command.getUserIds();
    showSuggestedContent = this.command.getShowSuggestedContent();
    aggregateByContentSource = this.command.getAggregateByContentSource();

    List<UserPortfolioUniqueItemPerfModel> models = new ArrayList<>();

    if (showSuggestedContent) {
      models = fetchUniqueSuggestedItemsPerformance(this.command, userIds);
    } else {
      models = fetchUniqueItemsPerformance(this.command, userIds);
    }

    List<Map<String, Object>> userItems = generateResponse(models);

    UserPortfolioUniqueItemPerfModelResponse result =
        new UserPortfolioUniqueItemPerfModelResponse();
    result.getItems(userItems);
    return result;
  }

  private List<UserPortfolioUniqueItemPerfModel> fetchUniqueItemsPerformance(
      UserPortfolioUniqueItemPerfCommand command, List<String> userIds) {
    return userPortfolioUniqueItemPerfDao.fetchUniqueItemsPerformance(command.asBean(),
        PGArrayUtils.convertFromListStringToSqlArrayOfString(userIds));
  }

  private List<UserPortfolioUniqueItemPerfModel> fetchUniqueSuggestedItemsPerformance(
      UserPortfolioUniqueItemPerfCommand command, List<String> userIds) {
    return userPortfolioUniqueItemPerfDao.fetchUniqueSuggestedItemsPerformance(command.asBean(),
        PGArrayUtils.convertFromListStringToSqlArrayOfString(userIds));
  }

  @SuppressWarnings("unchecked")
  private List<Map<String, Object>> generateResponse(
      List<UserPortfolioUniqueItemPerfModel> models) {
    Map<String, Object> items = new HashMap<>();

    List<String> contentIds = new ArrayList<>();
    models.forEach(model -> {
      contentIds.add(model.getId());
    });

    Map<String, CoreContentsModel> contentTitles =
        this.coreContentsService.fetchContentTitles(contentIds);
    for (UserPortfolioUniqueItemPerfModel model : models) {
      if (contentTitles != null) {
        CoreContentsModel coreModel = contentTitles.get(model.getId());
        if (coreModel != null && coreModel.getTitle() != null) {
          model.setTitle(coreModel.getTitle());
          model.setType(coreModel.getContentType());
        }
      }
      Boolean isSessionComplete = model.getStatus().equalsIgnoreCase(COMPLETE) ? true : false;
      Double score = model.getScore() != null ? Double.valueOf(model.getScore()) : null;
      Double maxScore = model.getMaxScore() != null ? Double.valueOf(model.getMaxScore()) : null;
      if (model.getType().equalsIgnoreCase(COLLECTION)) {
        if ((maxScore != null && maxScore > 0) && score != null) {
          model.setScore(score);
        } else {
          model.setScore(null);
        }
      } else {
        model.setScore(
            model.getScore() != null ? Double.valueOf(Math.round(model.getScore())) : null);
        if (!isSessionComplete) {
          model.setScore(null);
        }
      }
      if (items.containsKey(model.getUserId())) {
        List<UserPortfolioUniqueItemPerfModel> tempItems =
            (List<UserPortfolioUniqueItemPerfModel>) items.get(model.getUserId());
        tempItems.add(model);
        items.put(model.getUserId(), tempItems);
      } else {
        List<UserPortfolioUniqueItemPerfModel> usageDataList = new ArrayList<>();
        usageDataList.add(model);
        items.put(model.getUserId(), usageDataList);
      }
    }

    if (aggregateByContentSource) {
      Map<String, Object> resultMap = new HashMap<>();
      for (Entry<String, Object> item : items.entrySet()) {
        String uId = item.getKey();
        List<UserPortfolioUniqueItemPerfModel> ul =
            (List<UserPortfolioUniqueItemPerfModel>) item.getValue();
        Map<String, List<UserPortfolioUniqueItemPerfModel>> m = new HashMap<>();
        ul.forEach(ull -> {
          if (m.containsKey(ull.getContentSource())) {
            List<UserPortfolioUniqueItemPerfModel> tempItems = m.get(ull.getContentSource());
            tempItems.add(ull);
            m.put(ull.getContentSource(), tempItems);
          } else {
            List<UserPortfolioUniqueItemPerfModel> usageDataList = new ArrayList<>();
            usageDataList.add(ull);
            m.put(ull.getContentSource(), usageDataList);
          }
        });
        resultMap.put(uId, m);
      }
      items = resultMap;
    }

    List<Map<String, Object>> userItems = new ArrayList<>();
    for (Entry<String, Object> item : items.entrySet()) {
      Map<String, Object> userItemMap = new HashMap<>();
      userItemMap.put(USER_ID, item.getKey());
      userItemMap.put(USAGE_DATA, item.getValue());
      userItems.add(userItemMap);
    }
    return userItems;
  }
}
