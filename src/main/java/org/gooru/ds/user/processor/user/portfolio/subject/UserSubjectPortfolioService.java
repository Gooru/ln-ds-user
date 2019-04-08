package org.gooru.ds.user.processor.user.portfolio.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.Domain;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.Portfolio;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.UserSubjectPortfolioResponse;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.Collection;
import org.gooru.ds.user.processor.user.portfolio.subject.response.model.Competency;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserSubjectPortfolioService {  
  
  private final UserSubjectPortfolioDao userDomainPortfolioDao;
  private final CollectionMetadataService coreCollectionsService;
  
  List<UserSubjectPortfolioModel> models = new ArrayList<>();
  List<SubjectModel> subjModels = new ArrayList<>();
  
  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserSubjectPortfolioService.class);

  UserSubjectPortfolioService(DBI dbi, DBI coreDbi) {
    this.userDomainPortfolioDao = dbi.onDemand(UserSubjectPortfolioDao.class);
    this.coreCollectionsService = new CollectionMetadataService(coreDbi);
  }

  public UserSubjectPortfolioModelResponse fetchUserCollectionsPerf(
      UserSubjectPortfolioCommand command) {
    
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
    
    models = userDomainPortfolioDao.fetchCompetencyActivities(PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes), 
        command.getUserId());
    
    List<String> collectionIds = new ArrayList<>(models.size());
    models.forEach(model -> {
      collectionIds.add(model.getId());
      String compCode = model.getCompetencyCode();
      model.setDomainCode(CompetencyDomainMap.get(compCode));
    });

    Map<String, String> collectionTitles =
        this.coreCollectionsService.fetchCollectionTitles(collectionIds);
    LOGGER.debug("collections titles returned {}", collectionTitles.size());
    models.forEach(model -> {
      model.setTitle(collectionTitles.get(model.getId()));
    });
    
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
    
    portfolio.setDomains(new ArrayList<Domain>(DomainMap.values()));    
    String jsonOutput = "";
    ObjectMapper om = new ObjectMapper();
    try {
        jsonOutput = om.writeValueAsString(response);
    } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }    
    LOGGER.info("The subject Json is" + new JsonObject(jsonOutput).encodePrettily());
    //**************************************************************************************************************************************
          
    UserSubjectPortfolioModelResponse result =
        new UserSubjectPortfolioModelResponse();
    result.setCollections(models);
    return result;
  }

}
