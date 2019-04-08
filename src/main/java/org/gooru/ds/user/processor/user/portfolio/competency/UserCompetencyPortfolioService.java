package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mukul@gooru
 */
public class UserCompetencyPortfolioService {
  
  private final UserCompetencyPortfolioDao userCompetencyPortfolioDao;
  private final CollectionMetadataService coreCollectionsService;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserCompetencyPortfolioService.class);

  UserCompetencyPortfolioService(DBI dbi, DBI coreDbi) {
    this.userCompetencyPortfolioDao = dbi.onDemand(UserCompetencyPortfolioDao.class);
    this.coreCollectionsService = new CollectionMetadataService(coreDbi);
  }

  public UserCompetencyPortfolioModelResponse fetchUserCollectionsPerf(
      UserCompetencyPortfolioCommand command) {
    String gutCode = command.getGutCode();
    List<UserCompetencyPortfolioModel> models = new ArrayList<>();
    if (gutCode != null && !gutCode.isEmpty()) {
      if (command.getStatus() != null) {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyCollectionsByGutAndStatus(command.asBean());
      } else {
        models = userCompetencyPortfolioDao
            .fetchUserPerfCompetencyCollectionsByGut(command.asBean());
      }
    } 
    
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

    UserCompetencyPortfolioModelResponse result =
        new UserCompetencyPortfolioModelResponse();
    result.setCollections(models);
    return result;
  }


}
