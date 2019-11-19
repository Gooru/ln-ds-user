package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.constants.StatusConstants;
import org.skife.jdbi.v2.DBI;

/**
 * @author renuka
 */
public class UserPortfolioCompetencyMasteryService {

  private final UserPortfolioUniqueItemPerfDao dao;

  public UserPortfolioCompetencyMasteryService(DBI dbi) {
    this.dao = dbi.onDemand(UserPortfolioUniqueItemPerfDao.class);
  }
  
  public Map<String, Map<String, Object>> fetchCollectionMastery(String userId, List<String> collectionIds) {
    Map<String, Map<String, Object>> masterySummary = new HashMap<>();
    List<UserPortfolioMasteryStatusModel> activityModels =
        this.dao.fetchMasteryStatus(userId, PGArrayUtils.convertFromListStringToSqlArrayOfString(collectionIds));
    if (activityModels != null) {
      Map<String, List<UserPortfolioMasteryStatusModel>> activities = new HashMap<>();
      segregateGutModelsPerActivity(activityModels, activities);
      generateResponse(masterySummary, activities);
    }
    return masterySummary;
  }

  private void segregateGutModelsPerActivity(
      List<UserPortfolioMasteryStatusModel> activityModels,
      Map<String, List<UserPortfolioMasteryStatusModel>> activities) {
    activityModels.forEach(model -> {
      if (activities.containsKey(model.getId())) {
        List<UserPortfolioMasteryStatusModel> aggModels = activities.get(model.getId());
        aggModels.add(model);
        activities.put(model.getId(), aggModels);
      } else {
        List<UserPortfolioMasteryStatusModel> aggModels = new ArrayList<>();
        aggModels.add(model);
        activities.put(model.getId(), aggModels);
      }
    });
  }

  private void generateResponse(Map<String, Map<String, Object>> contentMastery,
      Map<String, List<UserPortfolioMasteryStatusModel>> contents) {
    contents.forEach((k,v) -> {
      List<UserPortfolioMasteryStatusModel> models = v;
      Map<String, Object> masteryData = new HashMap<>();
      List<Map<String, String>> masteredCompetencyList = new ArrayList<>();
      List<Map<String, String>> completedCompetencyList = new ArrayList<>();
      List<Map<String, String>> inferredCompetencyList = new ArrayList<>();
      List<Map<String, String>> inprogressCompetencyList = new ArrayList<>();
      aggregateCompetencyPerfPerStatus(models, masteredCompetencyList,
          completedCompetencyList, inferredCompetencyList, inprogressCompetencyList);
      masteryData.put("mastered", masteredCompetencyList);
      masteryData.put("completed", completedCompetencyList);
      masteryData.put("inferred", inferredCompetencyList);
      masteryData.put("inprogress", inprogressCompetencyList);
      contentMastery.put(k, masteryData);
    });
  }
  
  
  private void aggregateCompetencyPerfPerStatus(
      List<UserPortfolioMasteryStatusModel> models, List<Map<String, String>> masteredCompetencyList,
      List<Map<String, String>> completedCompetencyList, List<Map<String, String>> inferredCompetencyList,
      List<Map<String, String>> inprogressCompetencyList) {
    for (UserPortfolioMasteryStatusModel competencyStudyStatus : models) {
      switch (competencyStudyStatus.getStatus()) {
        case StatusConstants.MASTERED:
          addToRespectiveArray(competencyStudyStatus, masteredCompetencyList);
          break;
        case StatusConstants.COMPLETED:
          addToRespectiveArray(competencyStudyStatus, completedCompetencyList);
          break;
        case StatusConstants.INFERRED:
          addToRespectiveArray(competencyStudyStatus, inferredCompetencyList);
          break;
        case StatusConstants.IN_PROGRESS:
          addToRespectiveArray(competencyStudyStatus, inprogressCompetencyList);
          break;
        default:
          // Do Nothing
      }
    }
  }

  private void addToRespectiveArray(UserPortfolioMasteryStatusModel competencyStudyStatus,
      List<Map<String, String>> masteredCompetencyList) {
    Map<String, String> competencies = new HashMap<>();
    competencies.put("id", competencyStudyStatus.getGutCode());
    competencies.put("code", competencyStudyStatus.getGutDisplayCode());
    masteredCompetencyList.add(competencies);
  }
}
