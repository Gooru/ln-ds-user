package org.gooru.ds.user.processor.content.portfolio.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
class UserPortfolioItemPerfService {

  private final UserPortfolioItemPerfDao userPortfolioUniqueItemPerfDao;
  private UserPortfolioItemPerfCommand command;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioItemPerfService.class);
  private static final String COLLECTION = "collection";
  private static final String COMPLETE = "complete";
  private static final String USER_ID = "userId";
  private static final String ACTIVITY_ID = "activityId";
  private static final String USAGE_DATA = "usageData";

  UserPortfolioItemPerfService(DBI dbi, DBI coreDbi) {
    this.userPortfolioUniqueItemPerfDao = dbi.onDemand(UserPortfolioItemPerfDao.class);
  }

  public UserPortfolioItemPerfModelResponse fetchUserPortfolioItemAllSessionPerf(
      UserPortfolioItemPerfCommand command) {

    this.command = command;

    List<UserPortfolioItemPerfModel> models = new ArrayList<>();

    if (this.command.getStartDate() != null && this.command.getEndDate() != null) {
      models = fetchItemsAllAttemptsPerformanceInDateRange(this.command);
    } else {
      models = fetchUniqueItemsPerformance(this.command);
    }

    Map<String, Object> userItems = generateResponse(models);

    UserPortfolioItemPerfModelResponse result =
        new UserPortfolioItemPerfModelResponse();
    result.getItems(userItems);
    return result;

  }

  private List<UserPortfolioItemPerfModel> fetchUniqueItemsPerformance(UserPortfolioItemPerfCommand command) {
    return userPortfolioUniqueItemPerfDao.fetchItemAllAttemptsPerformance(command.asBean());
  }
  
  private List<UserPortfolioItemPerfModel> fetchItemsAllAttemptsPerformanceInDateRange(UserPortfolioItemPerfCommand command) {
    return userPortfolioUniqueItemPerfDao.fetchItemAllAttemptsPerformanceInDateRange(command.asBean());
  }
  
  private Map<String, Object> generateResponse(
      List<UserPortfolioItemPerfModel> models) {
    Map<String, Object> userItemMap = new HashMap<>();
    models.forEach(model -> {
      if (model.getType().equalsIgnoreCase(COLLECTION)) {
        Double score = model.getScore() != null ? Double.valueOf(model.getScore()) : null;
        Double maxScore = model.getMaxScore() != null ? Double.valueOf(model.getMaxScore()) : null;
        model.setScore(((maxScore != null && maxScore > 0) && score != null) ? score : null);
      } else {
        model.setScore((model.getStatus().equalsIgnoreCase(COMPLETE) && model.getScore() != null)
            ? Double.valueOf(Math.round(model.getScore()))
            : null);
      }
    });
    userItemMap.put(USER_ID, command.getUserId());
    userItemMap.put(ACTIVITY_ID, command.getItemId());
    userItemMap.put(USAGE_DATA, models);

    if (command.getAggByContentSource()) {
      Map<String, List<UserPortfolioItemPerfModel>> aggregatedResponse = new HashMap<>();
      models.forEach(ull -> {
        UserPortfolioItemPerfModel u = ull;
        if (aggregatedResponse.containsKey(u.getContentSource())) {
          List<UserPortfolioItemPerfModel> tempItems = aggregatedResponse.get(u.getContentSource());
          tempItems.add(u);
          aggregatedResponse.put(u.getContentSource(), tempItems);
        } else {
          List<UserPortfolioItemPerfModel> usageDataList = new ArrayList<>();
          usageDataList.add(u);
          aggregatedResponse.put(u.getContentSource(), usageDataList);
        }
      });
      userItemMap.put(USAGE_DATA, aggregatedResponse);
    }
    return userItemMap;
  }

}
