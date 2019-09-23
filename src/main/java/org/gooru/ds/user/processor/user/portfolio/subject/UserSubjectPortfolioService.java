package org.gooru.ds.user.processor.user.portfolio.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.processor.user.portfolio.competency.CoreCollectionItemCountsModel;
import org.gooru.ds.user.processor.user.portfolio.competency.CoreCollectionsModel;
import org.gooru.ds.user.processor.user.portfolio.competency.CoreCollectionsService;
import org.gooru.ds.user.processor.user.portfolio.competency.REEfInfoModel;
import org.gooru.ds.user.processor.user.portfolio.content.items.UserPortfolioCompetencyMasteryService;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.Collection;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.Competency;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.Domain;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.Portfolio;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.UserSubjectPortfolioResponse;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author mukul@gooru
 */
public class UserSubjectPortfolioService {  
  
  private final UserSubjectPortfolioDao userDomainPortfolioDao;
  private final CoreCollectionsService coreCollectionsService;
  private final UserPortfolioCompetencyMasteryService competencyMasteryService;
  private UserSubjectPortfolioCommand command;
  private String activityType;
  private static final String COLLECTION = "collection";
  private static final String ASSESSMENT = "assessment";
  private static final String OFFLINE_ACTIVITY = "offline-activity";
  private static final String USER_ID = "userId";
  private static final String USAGE_DATA = "usageData";
  
  List<UserSubjectPortfolioModel> models = new ArrayList<>();
  List<SubjectModel> subjModels = new ArrayList<>();
  
  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserSubjectPortfolioService.class);

  UserSubjectPortfolioService(DBI dbi, DBI coreDbi) {
    this.userDomainPortfolioDao = dbi.onDemand(UserSubjectPortfolioDao.class);
    this.coreCollectionsService = new CoreCollectionsService(coreDbi);
    this.competencyMasteryService = new UserPortfolioCompetencyMasteryService(dbi);
  }

  public UserSubjectPortfolioModelResponse fetchUserCollectionsPerf(
      UserSubjectPortfolioCommand command) {
    this.command = command;
    this.activityType = this.command.getActivityType();

    String subjectCode = command.getSubjectCode();
    subjModels = userDomainPortfolioDao.fetchCompetencyCodes(subjectCode);    
    List<String> compCodes = new ArrayList<>(subjModels.size());
    Map<String, String> CompetencyDomainMap = new HashMap<>();
    
    subjModels.forEach(subjModel -> {
      compCodes.add(subjModel.getCompCode());
      if (!CompetencyDomainMap.containsKey(subjModel.getCompCode())) {
        CompetencyDomainMap.put(subjModel.getCompCode(), subjModel.getDomainCode());
      }
    });
    
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
    
    generateResponse(CompetencyDomainMap);
    Map<String, Object> response = generateResponse(CompetencyDomainMap);

    UserSubjectPortfolioModelResponse result =
        new UserSubjectPortfolioModelResponse();
    result.setItems(response);
    return result;
  }

  private List<UserSubjectPortfolioModel> fetchOfflineActivities(
      UserSubjectPortfolioCommand command, List<String> compCodes) {
    List<UserSubjectPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      models = userDomainPortfolioDao.fetchCompetencyOfflineActivitiesInDateRange(
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes), command.asBean());
    } else {
      models = userDomainPortfolioDao.fetchCompetencyOfflineActivities(
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes), command.asBean());
    }
    return models;
  }

  private List<UserSubjectPortfolioModel> fetchCollections(
      UserSubjectPortfolioCommand command, List<String> compCodes) {
    List<UserSubjectPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      models = userDomainPortfolioDao.fetchCompetencyCollectionsInDateRange(
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes), command.asBean());
    } else {
      models = userDomainPortfolioDao.fetchCompetencyCollections(
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes), command.asBean());
    }
    return models;
  }

  private List<UserSubjectPortfolioModel> fetchAssessments(
      UserSubjectPortfolioCommand command, List<String> compCodes) {
    List<UserSubjectPortfolioModel> models;
    if (command.getStartDate() != null && command.getEndDate() != null) {
      models = userDomainPortfolioDao.fetchCompetencyAssessmentsInDateRange(
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes), command.asBean());
    } else {
      models = userDomainPortfolioDao.fetchCompetencyAssessments(
          PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes), command.asBean());
    }
    return models;
  }
  
  private Map<String, Object> generateResponse(Map<String, String> CompetencyDomainMap) {
    Map<String, Object> userItem = new HashMap<>();
    userItem.put(USER_ID, command.getUserId());

    List<String> collectionIds = new ArrayList<>(models.size());
    models.forEach(model -> {
      collectionIds.add(model.getId());
      String compCode = model.getCompetencyCode();
      model.setDomainCode(CompetencyDomainMap.get(compCode));
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
    Map<String, REEfInfoModel> reefInfo = this.coreCollectionsService.fetchREEfInfo(collectionIds);
    for (UserSubjectPortfolioModel model : models) {

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
      model.setGutCodes(coreModel.getGutCodes() != null ? coreModel.getGutCodes() : null);
      model.setOwnerId(coreModel.getOwnerId());
      model.setOriginalCreatorId(coreModel.getOriginalCreatorId());

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
      
      REEfInfoModel reefModel = new REEfInfoModel();
      if (reefInfo != null && reefInfo.containsKey(model.getId())) {
        reefModel = reefInfo.get(model.getId());
      }
      model.setEfficacy(reefModel.getEfficacy());
      model.setEngagement(reefModel.getEngagement());
      model.setRelevance(reefModel.getRelevance());
    }
    
    //**************************************************************************************************************************************

    UserSubjectPortfolioResponse response = new UserSubjectPortfolioResponse();
    Portfolio portfolio = new Portfolio();
    response.setPortfolio(portfolio);

    Map<String, Domain> DomainMap = new HashMap<>();
    Map<String, Competency> DomainCompetencyMap = new HashMap<>();

    
    models.forEach(tm -> {
      Domain domain = null;
      String domCode = tm.getDomainCode();
      if( DomainMap.containsKey(domCode)) {
          domain = DomainMap.get(domCode);
      }else {
          domain = new Domain();
          domain.setDomainCode(domCode);              
          DomainMap.put(domCode, domain);
      }
      
      Competency domainCompetency = null;
      String compCode = tm.getCompetencyCode();
      if( DomainCompetencyMap.containsKey(compCode)) {
          domainCompetency = DomainCompetencyMap.get(compCode);
      }else {
          domainCompetency = new Competency();
          domainCompetency.setCompetencyCode(compCode);          
          domain.addCompetencies(domainCompetency);
          DomainCompetencyMap.put(compCode, domainCompetency);
      }
      
      Collection collection = new Collection();
      domainCompetency.addCollection(collection);
      collection.setActivityTimestamp(tm.getActivityTimestamp());
      collection.setContentSource(tm.getContentSource());
      collection.setCollectionType(tm.getType());
      collection.setId(tm.getId());
      collection.setTitle(tm.getTitle());
      collection.setSessionId(tm.getSessionId());
      collection.setScore(tm.getScore());
  });
    
    portfolio.setDomains(new ArrayList<Domain>(DomainMap.values()));    
    String jsonOutput = "";
    ObjectMapper om = new ObjectMapper();
    try {
        jsonOutput = om.writeValueAsString(response);
    } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }    
//    LOGGER.info("The subject Json is" + new JsonObject(jsonOutput).encodePrettily());
    //**************************************************************************************************************************************
    
    userItem.put(USAGE_DATA, models);

    if (activityType.equalsIgnoreCase(OFFLINE_ACTIVITY)) {
        Map<String, List<UserSubjectPortfolioModel>> aggregatedResponse = new HashMap<>();
        models.forEach(ull -> {
          if (aggregatedResponse.containsKey(ull.getSubType())) {
            List<UserSubjectPortfolioModel> tempItems = aggregatedResponse.get(ull.getSubType());
            tempItems.add(ull);
            aggregatedResponse.put(ull.getSubType(), tempItems);
          } else {
            List<UserSubjectPortfolioModel> usageDataList = new ArrayList<>();
            usageDataList.add(ull);
            aggregatedResponse.put(ull.getSubType(), usageDataList);
          }
        });
        userItem.put(USAGE_DATA, aggregatedResponse);
    }
    return userItem;
  }

}
