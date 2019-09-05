package org.gooru.ds.user.processor.user.portfolio.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.processor.user.portfolio.competency.CoreCollectionItemCountsModel;
import org.gooru.ds.user.processor.user.portfolio.competency.CoreCollectionsModel;
import org.gooru.ds.user.processor.user.portfolio.competency.CoreCollectionsService;
import org.gooru.ds.user.processor.user.portfolio.content.items.UserPortfolioCompetencyMasteryService;
import org.gooru.ds.user.processor.user.portfolio.domain.response.model.Collection;
import org.gooru.ds.user.processor.user.portfolio.domain.response.model.Competency;
import org.gooru.ds.user.processor.user.portfolio.domain.response.model.Portfolio;
import org.gooru.ds.user.processor.user.portfolio.domain.response.model.UserDomainPortfolioResponse;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserDomainPortfolioService {
  
  private final UserDomainPortfolioDao userDomainPortfolioDao;
  private final CoreCollectionsService coreCollectionsService;
  private final UserPortfolioCompetencyMasteryService competencyMasteryService;
  private UserDomainPortfolioCommand command;
  private String activityType;
  private static final String COLLECTION = "collection";
  private static final String ASSESSMENT = "assessment";
  private static final String OFFLINE_ACTIVITY = "offline-activity";
  private static final String USER_ID = "userId";
  private static final String USAGE_DATA = "usageData";
  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserDomainPortfolioService.class);

  UserDomainPortfolioService(DBI dbi, DBI coreDbi) {
    this.userDomainPortfolioDao = dbi.onDemand(UserDomainPortfolioDao.class);
    this.coreCollectionsService = new CoreCollectionsService(coreDbi);
    this.competencyMasteryService = new UserPortfolioCompetencyMasteryService(dbi);
  }

  public UserDomainPortfolioModelResponse fetchUserCollectionsPerf(
      UserDomainPortfolioCommand command) {
    this.command = command;
    this.activityType = this.command.getActivityType();

    String subjectCode = command.getSubjectCode();
    String domainCode = command.getDomainCode();
    List<UserDomainPortfolioModel> models = new ArrayList<>();

    List<String> compCodes = userDomainPortfolioDao.fetchCompetencyCodes(subjectCode, domainCode);
    switch (activityType) {
      case ASSESSMENT:
        models = fetchAssessments(command, compCodes);
        break;
      case COLLECTION:
        models = fetchCollections(command, compCodes);
        break;
      case OFFLINE_ACTIVITY:
        models = fetchOfflineActivities(command, compCodes);
        break;
    }

    Map<String, Object> response = generateResponse(models);

    UserDomainPortfolioModelResponse result =
        new UserDomainPortfolioModelResponse();
    result.setItems(response);
    return result;
  }

  private List<UserDomainPortfolioModel> fetchOfflineActivities(
      UserDomainPortfolioCommand command, List<String> compCodes) {
    List<UserDomainPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      models = userDomainPortfolioDao.fetchCompetencyOfflineActivitiesInDateRange(command.asBean(),
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));
    } else {
      models = userDomainPortfolioDao.fetchCompetencyOfflineActivities(command.asBean(),
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));
    }
    return models;
  }

  private List<UserDomainPortfolioModel> fetchCollections(
      UserDomainPortfolioCommand command, List<String> compCodes) {
    List<UserDomainPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      models = userDomainPortfolioDao.fetchCompetencyCollectionsInDateRange(command.asBean(),
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));
    } else {
      models = userDomainPortfolioDao.fetchCompetencyCollections(command.asBean(),
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));
    }
    return models;
  }

  private List<UserDomainPortfolioModel> fetchAssessments(
      UserDomainPortfolioCommand command, List<String> compCodes) {
    List<UserDomainPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      models = userDomainPortfolioDao.fetchCompetencyAssessmentsInDateRange(command.asBean(),
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));
    } else {
      models = userDomainPortfolioDao.fetchCompetencyAssessments(command.asBean(),
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));
    }
    return models;
  }
  
  private Map<String, Object> generateResponse(List<UserDomainPortfolioModel> models) {
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
    for (UserDomainPortfolioModel model : models) {

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
    
    
    //**************************************************************************************************************************************

    UserDomainPortfolioResponse response = new UserDomainPortfolioResponse();
    Portfolio portfolio = new Portfolio();
    response.setPortfolio(portfolio);

    Map<String, Competency> CompetencyMap = new HashMap<>();
    
    models.forEach(tm -> {
      Competency competency = null;
      String compCode = tm.getCompetencyCode();
      if( CompetencyMap.containsKey(compCode)) {
          competency = CompetencyMap.get(compCode);
      }else {
          competency = new Competency();
          competency.setCompetencyCode(compCode);              
          CompetencyMap.put(compCode, competency);
      }
            
      Collection collection = new Collection();
      competency.addCollection(collection);
      collection.setActivityTimestamp(tm.getActivityTimestamp());
      collection.setContentSource(tm.getContentSource());
      collection.setId(tm.getId());
      collection.setTitle(tm.getTitle());
      collection.setSessionId(tm.getSessionId());
      collection.setScore(tm.getScore());
  });
    
    portfolio.setCompetencies(new ArrayList<Competency>(CompetencyMap.values()));    
    String jsonOutput = "";
    ObjectMapper om = new ObjectMapper();
    try {
        jsonOutput = om.writeValueAsString(response);
    } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }    
    LOGGER.info("The domain Json is" + new JsonObject(jsonOutput).encodePrettily());
    //**************************************************************************************************************************************
    
    userItem.put(USAGE_DATA, models);

    if (activityType.equalsIgnoreCase(OFFLINE_ACTIVITY)) {
        Map<String, List<UserDomainPortfolioModel>> aggregatedResponse = new HashMap<>();
        models.forEach(ull -> {
          if (aggregatedResponse.containsKey(ull.getSubType())) {
            List<UserDomainPortfolioModel> tempItems = aggregatedResponse.get(ull.getSubType());
            tempItems.add(ull);
            aggregatedResponse.put(ull.getSubType(), tempItems);
          } else {
            List<UserDomainPortfolioModel> usageDataList = new ArrayList<>();
            usageDataList.add(ull);
            aggregatedResponse.put(ull.getSubType(), usageDataList);
          }
        });
        userItem.put(USAGE_DATA, aggregatedResponse);
    }
    return userItem;
  }

}
