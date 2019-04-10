package org.gooru.ds.user.processor.user.portfolio.content.perf.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
class UserPortfolioItemPerfService {

  private final UserPortfolioItemPerfDao userPortfolioUniqueItemPerfDao;
  private UserPortfolioItemPerfCommand command;
  private String userId;
  private Boolean showSuggestedContent;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioItemPerfService.class);
  private static final String COLLECTION = "collection";
  private static final String COMPLETE = "complete";
  private static final String USER_ID = "userId";
  private static final String USAGE_DATA = "usageData";

  UserPortfolioItemPerfService(DBI dbi, DBI coreDbi) {
    this.userPortfolioUniqueItemPerfDao = dbi.onDemand(UserPortfolioItemPerfDao.class);
  }

  public UserPortfolioItemPerfModelResponse fetchUserPortfolioUniqueItemPerf(
      UserPortfolioItemPerfCommand command) {

    this.command = command;
    userId = this.command.getUserId();
    showSuggestedContent = this.command.getShowSuggestedContent();

    List<UserPortfolioItemPerfModel> models = new ArrayList<>();

    if (showSuggestedContent) {
      models = fetchUniqueSuggestedItemsPerformance(this.command);
    } else {
      models = fetchUniqueItemsPerformance(this.command);
    }

    List<Map<String, Object>> userItems = generateResponse(models);

    UserPortfolioItemPerfModelResponse result =
        new UserPortfolioItemPerfModelResponse();
    result.getItems(userItems);
    return result;

  }

  private List<UserPortfolioItemPerfModel> fetchUniqueItemsPerformance(UserPortfolioItemPerfCommand command) {
    return userPortfolioUniqueItemPerfDao.fetchItemAllAttemptsPerformance(command.asBean());
  }
  
  private List<UserPortfolioItemPerfModel> fetchUniqueSuggestedItemsPerformance(UserPortfolioItemPerfCommand command) {
    return userPortfolioUniqueItemPerfDao.fetchSuggestedItemAllAttemptsPerformance(command.asBean());
  }
  
  private List<Map<String, Object>> generateResponse(
      List<UserPortfolioItemPerfModel> models) {
    Map<String, List<UserPortfolioItemPerfModel>> items = new HashMap<>();

    models.forEach(model -> {
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
      if (items.containsKey(userId)) {
        List<UserPortfolioItemPerfModel> tempItems = items.get(userId);
        tempItems.add(model);
        items.put(userId, tempItems);
      } else {
        List<UserPortfolioItemPerfModel> usageDataList = new ArrayList<>();
        usageDataList.add(model);
        items.put(userId, usageDataList);
      }
    });
    
    List<Map<String, Object>> userItems = new ArrayList<>();
    for (Entry<String, List<UserPortfolioItemPerfModel>> item : items.entrySet()) {
      Map<String, Object> userItemMap = new HashMap<>();
      userItemMap.put(USER_ID, item.getKey());
      userItemMap.put(USAGE_DATA, item.getValue());
      userItems.add(userItemMap);
    }
    return userItems;
  }

}
