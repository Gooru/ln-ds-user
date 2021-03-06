package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
class UserPortfolioUniqueItemPerfService {

  private final UserPortfolioUniqueItemPerfDao userPortfolioUniqueItemPerfDao;
  private final CoreService coreCollectionsService;
  private final UserPortfolioCompetencyMasteryService competencyMasteryService;
  private final UniqueItemPerformanceService uniqueItemPerformanceService;
  private UserPortfolioUniqueItemPerfCommand command;
  private String activityType;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserPortfolioUniqueItemPerfService.class);
  private static final String COLLECTION = "collection";
  private static final String ASSESSMENT = "assessment";
  private static final String OFFLINE_ACTIVITY = "offline-activity";
  private static final String COMPLETE = "complete";
  private static final String USER_ID = "userId";
  private static final String USAGE_DATA = "usageData";
  private static final Pattern COLLECTION_TYPES = Pattern.compile("collection|collection-external");

  UserPortfolioUniqueItemPerfService(DBI dbi, DBI coreDbi, DBI dsDbi) {
    this.userPortfolioUniqueItemPerfDao = dbi.onDemand(UserPortfolioUniqueItemPerfDao.class);
    this.coreCollectionsService = new CoreService(coreDbi);
    this.competencyMasteryService = new UserPortfolioCompetencyMasteryService(dsDbi);
    this.uniqueItemPerformanceService = new UniqueItemPerformanceService(dbi);
  }

  public UserPortfolioUniqueItemPerfModelResponse fetchUserPortfolioUniqueItemPerf(
      UserPortfolioUniqueItemPerfCommand command) {

    this.command = command;
    this.activityType = this.command.getActivityType();

    List<UserPortfolioUniqueItemPerfModel> models = new ArrayList<>();

    switch (activityType) {
      case ASSESSMENT:
        if (command.getStartDate() != null && command.getEndDate() != null) {
          models = userPortfolioUniqueItemPerfDao
              .fetchUniqueAsmtsPerformanceByDateRange(command.asBean());
        } else {
          models = userPortfolioUniqueItemPerfDao.fetchUniqueAsmtsPerformance(command.asBean());
        }
        break;
      case COLLECTION:
        if (command.getStartDate() != null && command.getEndDate() != null) {

          models = userPortfolioUniqueItemPerfDao
              .fetchUniqueCollsPerformanceByDateRange(command.asBean());
        } else {
          models = userPortfolioUniqueItemPerfDao.fetchUniqueCollsPerformance(command.asBean());
        }
        break;
      case OFFLINE_ACTIVITY:
        if (command.getStartDate() != null && command.getEndDate() != null) {
          models =
              userPortfolioUniqueItemPerfDao.fetchUniqueOasPerformanceByDateRange(command.asBean());
        } else {
          models = userPortfolioUniqueItemPerfDao.fetchUniqueOasPerformance(command.asBean());
        }
        break;
    }
    
    Map<String, Object> userItems = generateResponse(models);
    UserPortfolioUniqueItemPerfModelResponse result =
        new UserPortfolioUniqueItemPerfModelResponse();
    result.setItems(userItems);
    return result;
  }

  private Map<String, Object> generateResponse(
      List<UserPortfolioUniqueItemPerfModel> models) {
    Map<String, Object> userItem = new HashMap<>();
    userItem.put(USER_ID, command.getUserId());

    List<String> collectionIds = new ArrayList<>();
    models.forEach(model -> {
      collectionIds.add(model.getId());
    });

    Map<String, CoreCollectionsModel> collectionMeta =
        this.coreCollectionsService.fetchCollectionMeta(collectionIds);
    Map<String, CoreCollectionItemCountsModel> collectionItemCounts =
        this.coreCollectionsService.fetchCollectionItemCount(collectionIds);
    Map<String, Map<String, Object>> collectionMasteryData =
        this.competencyMasteryService.fetchCollectionMastery(command.getUserId(), collectionIds);
    Map<String, Integer> oaTaskCounts = null;
    if (activityType.equalsIgnoreCase(OFFLINE_ACTIVITY)) {
      oaTaskCounts = this.coreCollectionsService.fetchOATaskCount(collectionIds);
    }
    Map<String, Long> collectionTimespent = this.uniqueItemPerformanceService
        .fetchCumulativeTimespentForCollection(command.getUserId(), collectionIds);
    Set<String> userIds = new HashSet<>(models.size());
    Map<String, String> ownerIdMap = new HashMap<>(models.size());
    for (UserPortfolioUniqueItemPerfModel model : models) {
      CoreCollectionsModel coreModel = new CoreCollectionsModel();
      if (collectionMeta != null && collectionMeta.containsKey(model.getId())) {
        coreModel = collectionMeta.get(model.getId());
      }

      model.setTitle(coreModel.getTitle());
      model.setSubType(coreModel.getSubType());
      model.setLearningObjective(coreModel.getLearningObjective());
      model.setThumbnail(coreModel.getThumbnail());
      model.setTaxonomy(coreModel.getTaxonomy() != null ? coreModel.getTaxonomy().getMap() : null);
      model.setGutCodes(coreModel.getGutCodes() != null ? coreModel.getGutCodes() : null);
      if (coreModel.getOwnerId() != null) {
        userIds.add(coreModel.getOwnerId());
        ownerIdMap.put(model.getId(), coreModel.getOwnerId());
      }
      
      CoreCollectionItemCountsModel cModel = new CoreCollectionItemCountsModel();
      if (collectionItemCounts != null && collectionItemCounts.containsKey(model.getId())) {
        cModel = collectionItemCounts.get(model.getId());
      }
      model.setQuestionCount(cModel.getQuestionCount());
      model.setResourceCount(cModel.getResourceCount());
      if (COLLECTION_TYPES.matcher(model.getType()).matches()) {
        Double score = model.getScore() != null ? Double.valueOf(model.getScore()) : null;
        Double maxScore = model.getMaxScore() != null ? Double.valueOf(model.getMaxScore()) : null;
        model.setScore(((maxScore != null && maxScore > 0) && score != null) ? score : null);
        if (collectionTimespent != null && collectionTimespent.containsKey(model.getId())) {
          model.setTimespent(collectionTimespent.get(model.getId()));
        }
      } else {
        model.setScore((model.getStatus().equalsIgnoreCase(COMPLETE) && model.getScore() != null)
            ? Double.valueOf(Math.round(model.getScore()))
            : null);
      }
      
      Integer taskCount = 0;
      if (oaTaskCounts != null && oaTaskCounts.containsKey(model.getId())) {
        taskCount = oaTaskCounts.get(model.getId());
      }
      model.setTaskCount(taskCount);
      
      if (collectionMasteryData != null && collectionMasteryData.containsKey(model.getId())) {
        model.setMasterySummary(collectionMasteryData.get(model.getId()));
      }

    }
    enrichResponseWithOwnerInfo(models, userIds, ownerIdMap);
    userItem.put(USAGE_DATA, models);

    if (activityType.equalsIgnoreCase(OFFLINE_ACTIVITY)) {
        Map<String, List<UserPortfolioUniqueItemPerfModel>> aggregatedResponse = new HashMap<>();
        models.forEach(ull -> {
          if (aggregatedResponse.containsKey(ull.getSubType())) {
            List<UserPortfolioUniqueItemPerfModel> tempItems = aggregatedResponse.get(ull.getSubType());
            tempItems.add(ull);
            aggregatedResponse.put(ull.getSubType(), tempItems);
          } else {
            List<UserPortfolioUniqueItemPerfModel> usageDataList = new ArrayList<>();
            usageDataList.add(ull);
            aggregatedResponse.put(ull.getSubType(), usageDataList);
          }
        });
        userItem.put(USAGE_DATA, aggregatedResponse);
    }
    return userItem;
  }

  private void enrichResponseWithOwnerInfo(List<UserPortfolioUniqueItemPerfModel> models,
      Set<String> userIds, Map<String, String> ownerIdMap) {
    if (userIds.size() > 0) {
      Map<String, UserModel> usersMeta = this.coreCollectionsService.fetchUserMeta(userIds);
      for (UserPortfolioUniqueItemPerfModel model : models) {
        if (ownerIdMap.containsKey(model.getId())
            && usersMeta.containsKey(ownerIdMap.get(model.getId()))) {
          model.setOwner(usersMeta.get(ownerIdMap.get(model.getId())));
        }
      }
    }
  }
}
