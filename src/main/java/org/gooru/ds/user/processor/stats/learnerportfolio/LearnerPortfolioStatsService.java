package org.gooru.ds.user.processor.stats.learnerportfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;


class LearnerPortfolioStatsService {


  private final LearnerPortfolioStatsDao learnerPortfolioStatsDao;

  private final String LEARNER_PORTFOLIO_STATS = "learnerPortfolioStats";
  private final String COLLECTION = "collection";
  private final String ASSESSMENT = "assessment";
  private final String COLLECTION_EXTERNAL = "collection-external";
  private final String ASSESSMENT_EXTERNAL = "assessment-external";
  private final String OFFLINE_ACTIVITY = "offline-activity";


  LearnerPortfolioStatsService(DBI dbi) {
    this.learnerPortfolioStatsDao = dbi.onDemand(LearnerPortfolioStatsDao.class);
  }

  private Map<String, List<LearnerPortfolioStatsResponseModel>> learnerPortfolioStats(
      List<LearnerPortfolioStatsModel> learnerPortfolioStatsModels) {
    List<LearnerPortfolioStatsResponseModel> listLearnerPortfolioStats = new ArrayList<>();
    Map<String, List<LearnerPortfolioStatsResponseModel>> learnerPortfolioStats = new HashMap<>();
    if (learnerPortfolioStatsModels != null && !learnerPortfolioStatsModels.isEmpty()) {
      Map<String, LearnerPortfolioStatsResponseModel> learnerPortfolioStatsModelMap =
          groupLearnerPortfolioStatsBySubject(learnerPortfolioStatsModels);
      learnerPortfolioStatsModelMap.forEach((subjectCode, learnerPortfolioStat) -> {
        listLearnerPortfolioStats.add(learnerPortfolioStat);
      });
      Collections.sort(listLearnerPortfolioStats,
          Comparator.comparing(LearnerPortfolioStatsResponseModel::getClassificationSeq)
              .thenComparing(LearnerPortfolioStatsResponseModel::getSubjectSeq));
    }
    learnerPortfolioStats.put(LEARNER_PORTFOLIO_STATS, listLearnerPortfolioStats);

    return learnerPortfolioStats;
  }

  private Map<String, LearnerPortfolioStatsResponseModel> groupLearnerPortfolioStatsBySubject(
      List<LearnerPortfolioStatsModel> learnerPortfolioStatsModels) {
    Map<String, LearnerPortfolioStatsResponseModel> learnerPortfolioStatsModelMap = new HashMap<>();
    learnerPortfolioStatsModels.forEach(learnerPortfolioStatsModel -> {
      String subjectCode = learnerPortfolioStatsModel.getSubjectCode();
      LearnerPortfolioStatsResponseModel subjectCompetencyStats = null;
      if (learnerPortfolioStatsModelMap.get(subjectCode) != null) {
        subjectCompetencyStats = learnerPortfolioStatsModelMap.get(subjectCode);
      } else {
        subjectCompetencyStats = new LearnerPortfolioStatsResponseModel();
        subjectCompetencyStats.setSubjectName(learnerPortfolioStatsModel.getSubjectName());
        subjectCompetencyStats.setSubjectCode(learnerPortfolioStatsModel.getSubjectCode());
        subjectCompetencyStats
            .setClassificationCode(learnerPortfolioStatsModel.getClassificationCode());
        subjectCompetencyStats
            .setClassificationSeq(learnerPortfolioStatsModel.getClassificationSeq());
        subjectCompetencyStats.setSubjectSeq(learnerPortfolioStatsModel.getSubjectSeq());
        subjectCompetencyStats.setClassificationName(learnerPortfolioStatsModel.getClassificationName());
        learnerPortfolioStatsModelMap.put(subjectCode, subjectCompetencyStats);
      }
      String collectionType = learnerPortfolioStatsModel.getCollectionType();
      Integer count = learnerPortfolioStatsModel.getCollectionCount();
      setCollectionCount(collectionType, count, subjectCompetencyStats);

    });
    return learnerPortfolioStatsModelMap;
  }

  private void setCollectionCount(String collectionType, Integer count,
      LearnerPortfolioStatsResponseModel subjectCompetencyStats) {
    if (collectionType != null) {
      if (collectionType.equalsIgnoreCase(ASSESSMENT)) {
        subjectCompetencyStats.setAssessmentCount(count);
      } else if (collectionType.equalsIgnoreCase(COLLECTION)) {
        subjectCompetencyStats.setCollectionCount(count);
      } else if (collectionType.equalsIgnoreCase(COLLECTION_EXTERNAL)) {
        subjectCompetencyStats.setCollectionExternalCount(count);
      } else if (collectionType.equalsIgnoreCase(ASSESSMENT_EXTERNAL)) {
        subjectCompetencyStats.setAssessmentExternalCount(count);
      } else if (collectionType.equalsIgnoreCase(OFFLINE_ACTIVITY)) {
        subjectCompetencyStats.setOaCount(count);
      }
    }
  }



  Map<String, List<LearnerPortfolioStatsResponseModel>> fetchLearnerPortfolioStats(
      LearnerPortfolioStatsCommand command) {
    final List<LearnerPortfolioStatsModel> learnerPortfolioStatsModel =
        learnerPortfolioStatsDao.fetchLearnerPortfolioStats(command.asBean());
    return learnerPortfolioStats(learnerPortfolioStatsModel);
  }


}
