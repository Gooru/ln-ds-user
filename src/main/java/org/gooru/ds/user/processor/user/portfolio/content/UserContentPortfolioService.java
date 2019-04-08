package org.gooru.ds.user.processor.user.portfolio.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.processor.user.portfolio.content.response.model.Activity;
import org.gooru.ds.user.processor.user.portfolio.content.response.model.Content;
import org.gooru.ds.user.processor.user.portfolio.content.response.model.Portfolio;
import org.gooru.ds.user.processor.user.portfolio.content.response.model.UserContentPortfolioResponse;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;

public class UserContentPortfolioService {
  
  private final UserContentPortfolioDao userContentPortfolioDao;
  private final CollectionMetadataService coreCollectionsService;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserContentPortfolioService.class);

  UserContentPortfolioService(DBI anaDbi, DBI coreDbi) {
    this.userContentPortfolioDao = anaDbi.onDemand(UserContentPortfolioDao.class);
    this.coreCollectionsService = new CollectionMetadataService(coreDbi);
  }

  public UserContentPortfolioModelResponse fetchUserCollectionsPerf(
      UserContentPortfolioCommand command) {
     
    List<UserContentPortfolioModel> models = new ArrayList<>();
    if (command.getActivityType() == null) {
      models = userContentPortfolioDao.fetchContentActivities(command.getUserId());      
    } else {
      models = userContentPortfolioDao.fetchContentActivitiesbyActivityType
          (command.getUserId(), command.getActivityType());
    }
      
    List<String> collectionIds = new ArrayList<>(models.size());
    models.forEach(model -> {
      collectionIds.add(model.getCollectionId());
      LOGGER.debug("adding collection id '{}' in list", model.getCollectionId());
    });

    Map<String, String> collectionTitles =
        this.coreCollectionsService.fetchCollectionTitles(collectionIds);
    LOGGER.debug("collections titles returned {}", collectionTitles.size());
    models.forEach(model -> {
      model.setTitle(collectionTitles.get(model.getCollectionId()));
    });
    
    //**************************************************************************************************************************************

    UserContentPortfolioResponse response = new UserContentPortfolioResponse();
    Portfolio portfolio = new Portfolio();
    response.setPortfolio(portfolio);
    
    Map<String, Content> ContentMap = new HashMap<>();
    
    models.forEach(tm -> {
      Content content = null;
      String activityType = tm.getCollectionType();
      if( ContentMap.containsKey(activityType)) {
          content = ContentMap.get(activityType);
      }else {
          content = new Content();
          content.setActivityType(activityType);              
          ContentMap.put(activityType, content);
      }
            
      Activity activity = new Activity();
      content.addActivity(activity);
     
      
      activity.setActivityTimestamp(tm.getActivityTimestamp());
      activity.setClassId(tm.getClassId());
      activity.setCollectionType(tm.getCollectionType());
      activity.setContentSource(tm.getContentSource());
      activity.setCourseId(tm.getCourseId());
      activity.setCollectionId(tm.getCollectionId());
      activity.setLessonId(tm.getLessonId());
      activity.setTitle(tm.getTitle());
      activity.setUnitId(tm.getUnitId());
      activity.setSessionId(tm.getSessionId());
      activity.setScore(tm.getScore());
  });

      portfolio.setContent(new ArrayList<Content>(ContentMap.values()));    
    String jsonOutput = "";
    ObjectMapper om = new ObjectMapper();
    try {
        jsonOutput = om.writeValueAsString(response);
    } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }    
    LOGGER.info("The content Json is" + new JsonObject(jsonOutput).encodePrettily());
    //**************************************************************************************************************************************


    UserContentPortfolioModelResponse result =
        new UserContentPortfolioModelResponse();
//    result.setCollections(models);
    return result;
  }

}