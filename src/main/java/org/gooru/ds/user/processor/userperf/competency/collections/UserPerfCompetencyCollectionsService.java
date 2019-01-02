package org.gooru.ds.user.processor.userperf.competency.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPerfCompetencyCollectionsService {

  private final UserPerfCompetencyCollectionsDao userPerfCompetencyCollectionsDao;
  private final CoreCollectionsService coreCollectionsService;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserPerfCompetencyCollectionsService.class);

  UserPerfCompetencyCollectionsService(DBI dbi, DBI coreDbi) {
    this.userPerfCompetencyCollectionsDao = dbi.onDemand(UserPerfCompetencyCollectionsDao.class);
    this.coreCollectionsService = new CoreCollectionsService(coreDbi);
  }

  public UserPerfCompetencyCollectionsModelResponse fetchUserCollectionsPerf(
      UserPerfCompetencyCollectionsCommand command) {
    String gutCode = command.getGutCode();
    List<UserPerfCompetencyCollectionsModel> models =
        userPerfCompetencyCollectionsDao.fetchUserPerfCompetencyCollections(command.asBean());
    if (gutCode != null && !gutCode.isEmpty()) {
      if (command.getStatus() != null) {
        models = userPerfCompetencyCollectionsDao
            .fetchUserPerfCompetencyCollectionsByGutAndStatus(command.asBean());
      } else {
        models = userPerfCompetencyCollectionsDao
            .fetchUserPerfCompetencyCollectionsByGut(command.asBean());
      }
    } else {
      models =
          userPerfCompetencyCollectionsDao.fetchUserPerfCompetencyCollections(command.asBean());
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

    UserPerfCompetencyCollectionsModelResponse result =
        new UserPerfCompetencyCollectionsModelResponse();
    result.setCollections(models);
    return result;
  }

}
