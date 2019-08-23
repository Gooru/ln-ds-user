package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.processor.user.portfolio.content.items.UserPortfolioCompetencyMasteryService;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mukul@gooru
 */
public class UserCompetencyPortfolioService {
  
  private final UserCompetencyPortfolioDao userCompetencyPortfolioDao;
  private final CoreCollectionsService coreCollectionsService;
  private final UserPortfolioCompetencyMasteryService competencyMasteryService;
  private UserCompetencyPortfolioCommand command;
  private String activityType;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserCompetencyPortfolioService.class);
  private static final String COLLECTION = "collection";
  private static final String ASSESSMENT = "assessment";
  private static final String OFFLINE_ACTIVITY = "offline-activity";
  private static final String USER_ID = "userId";
  private static final String USAGE_DATA = "usageData";

  UserCompetencyPortfolioService(DBI dbi, DBI coreDbi) {
    this.userCompetencyPortfolioDao = dbi.onDemand(UserCompetencyPortfolioDao.class);
    this.coreCollectionsService = new CoreCollectionsService(coreDbi);
    this.competencyMasteryService = new UserPortfolioCompetencyMasteryService(dbi);
  }

  public UserCompetencyPortfolioModelResponse fetchUserCollectionsPerf(
      UserCompetencyPortfolioCommand command) {
    this.command = command;
    this.activityType = this.command.getActivityType();

    String gutCode = command.getGutCode();
    List<UserCompetencyPortfolioModel> models = new ArrayList<>();
    if (gutCode != null && !gutCode.isEmpty()) {
      switch (activityType) {
        case ASSESSMENT:
          models = fetchAssessments(command);
          break;
        case COLLECTION:
          models = fetchCollections(command);
          break;
        case OFFLINE_ACTIVITY:
          models = fetchOfflineActivities(command);
          break;
      }
    }
    
    Map<String, Object> userItems = generateResponse(models);

    UserCompetencyPortfolioModelResponse result =
        new UserCompetencyPortfolioModelResponse();
    result.setItems(userItems);
    return result;
  }

  private List<UserCompetencyPortfolioModel> fetchOfflineActivities(
      UserCompetencyPortfolioCommand command) {
    List<UserCompetencyPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      if (command.getStatus() != null) {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyCollectionsByGutAndStatusInDateRange(command.asBean());
      } else {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyCollectionsByGutInDateRange(command.asBean());
      }
    } else {
      if (command.getStatus() != null) {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyOAsByGutAndStatus(command.asBean());
      } else {
        models = userCompetencyPortfolioDao.fetchUserPerfCompetencyOAsByGut(command.asBean());
      }
    }
    return models;
  }

  private List<UserCompetencyPortfolioModel> fetchCollections(
      UserCompetencyPortfolioCommand command) {
    List<UserCompetencyPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      if (command.getStatus() != null) {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyOAsByGutAndStatusInDateRange(command.asBean());
      } else {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyOAsByGutInDateRange(command.asBean());
      }
    } else {
      if (command.getStatus() != null) {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyCollectionsByGutAndStatus(command.asBean());
      } else {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyCollectionsByGut(command.asBean());
      }
    }
    return models;
  }

  private List<UserCompetencyPortfolioModel> fetchAssessments(
      UserCompetencyPortfolioCommand command) {
    List<UserCompetencyPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      if (command.getStatus() != null) {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyAssessmentsByGutAndStatusInDateRange(command.asBean());
      } else {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyAssessmentsByGutInDateRange(command.asBean());
      }
    } else {
      if (command.getStatus() != null) {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyAssessmentsByGutAndStatus(command.asBean());
      } else {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyAssessmentsByGut(command.asBean());
      }
    }
    return models;
  }

  private Map<String, Object> generateResponse(List<UserCompetencyPortfolioModel> models) {
    Map<String, Object> userItem = new HashMap<>();
    userItem.put(USER_ID, command.getUserId());

    List<String> collectionIds = new ArrayList<>(models.size());
    models.forEach(model -> {
      collectionIds.add(model.getId());
      LOGGER.debug("adding collection id '{}' in list", model.getId());
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
    for (UserCompetencyPortfolioModel model : models) {

      CoreCollectionsModel coreModel = new CoreCollectionsModel();
      if (collectionMeta != null && collectionMeta.containsKey(model.getId())) {
        coreModel = collectionMeta.get(model.getId());
      }
      model.setTitle(coreModel.getTitle());
      model.setType(coreModel.getType());
      model.setSubType(coreModel.getSubType());
      model.setLearningObjective(coreModel.getLearningObjective());
      model.setThumbnail(coreModel.getThumbnail());
      model.setTaxonomy(coreModel.getTaxonomy() != null ? coreModel.getTaxonomy().getMap() : null);

      CoreCollectionItemCountsModel cModel = new CoreCollectionItemCountsModel();
      if (collectionItemCounts != null && collectionItemCounts.containsKey(model.getId())) {
        cModel = collectionItemCounts.get(model.getId());
      }
      model.setQuestionCount(cModel.getQuestionCount());
      model.setResourceCount(cModel.getResourceCount());
      if (activityType.equalsIgnoreCase(OFFLINE_ACTIVITY)) {
        Integer taskCount = 0;
        if (oaTaskCounts != null && oaTaskCounts.containsKey(model.getId())) {
          taskCount = oaTaskCounts.get(model.getId());
        }
        model.setTaskCount(taskCount);
      }
      
      if (collectionMasteryData != null && collectionMasteryData.containsKey(model.getId())) {
        model.setMasterySummary(collectionMasteryData.get(model.getId()));
      }
    }
    userItem.put(USAGE_DATA, models);

    if (activityType.equalsIgnoreCase(OFFLINE_ACTIVITY)) {
      Map<String, List<UserCompetencyPortfolioModel>> aggregatedResponse = new HashMap<>();
      models.forEach(ull -> {
        if (aggregatedResponse.containsKey(ull.getSubType())) {
          List<UserCompetencyPortfolioModel> tempItems = aggregatedResponse.get(ull.getSubType());
          tempItems.add(ull);
          aggregatedResponse.put(ull.getSubType(), tempItems);
        } else {
          List<UserCompetencyPortfolioModel> usageDataList = new ArrayList<>();
          usageDataList.add(ull);
          aggregatedResponse.put(ull.getSubType(), usageDataList);
        }
      });
      userItem.put(USAGE_DATA, aggregatedResponse);
    }
    return userItem;
  }

}
