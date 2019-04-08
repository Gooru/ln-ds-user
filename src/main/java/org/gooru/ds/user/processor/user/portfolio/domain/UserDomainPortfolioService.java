package org.gooru.ds.user.processor.user.portfolio.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.processor.user.portfolio.competency.CollectionMetadataService;
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
  private final CollectionMetadataService coreCollectionsService;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserDomainPortfolioService.class);

  UserDomainPortfolioService(DBI dbi, DBI coreDbi) {
    this.userDomainPortfolioDao = dbi.onDemand(UserDomainPortfolioDao.class);
    this.coreCollectionsService = new CollectionMetadataService(coreDbi);
  }

  public UserDomainPortfolioModelResponse fetchUserCollectionsPerf(
      UserDomainPortfolioCommand command) {
    
    //STUFF MY CODE HERE 
    String subjectCode = command.getSubjectCode();
    String domainCode = command.getdomainCode();
    List<String> compCodes = userDomainPortfolioDao.fetchCompetencyCodes(subjectCode, domainCode);    
    List<UserDomainPortfolioModel> models = userDomainPortfolioDao.fetchCompetencyActivities(
        PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes), command.getUserId());
    
    List<String> collectionIds = new ArrayList<>(models.size());
    models.forEach(model -> {
      collectionIds.add(model.getId());
      LOGGER.debug("adding collection id '{}' in list", model.getId());
    });

    Map<String, String> collectionTitles =
        this.coreCollectionsService.fetchCollectionTitles(collectionIds);
    LOGGER.debug("collections titles returned {}", collectionTitles.size());
    models.forEach(model -> {
      model.setTitle(collectionTitles.get(model.getId()));
    });
    
    
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
      collection.setClassId(tm.getClassId());
      collection.setCollectionType(tm.getCollectionType());
      collection.setContentSource(tm.getContentSource());
      collection.setCourseId(tm.getCourseId());
      collection.setId(tm.getId());
      collection.setLessonId(tm.getLessonId());
      collection.setTitle(tm.getTitle());
      collection.setUnitId(tm.getUnitId());
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


    UserDomainPortfolioModelResponse result =
        new UserDomainPortfolioModelResponse();
    result.setCollections(models);
    return result;
  }

}
