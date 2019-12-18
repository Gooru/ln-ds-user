package org.gooru.ds.user.processor.stats.learnerportfolio.subjectdomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;


class LearnerPortfolioStatsSubjectDomainService {


  private final LearnerPortfolioStatsSubjectDomainDao learnerPortfolioStatsSubjectDomainDao;

  private final String LEARNER_PORTFOLIO_STATS = "learnerPortfolioStats";
  private final String COLLECTION = "collection";
  private final String ASSESSMENT = "assessment";
  private final String COLLECTION_EXTERNAL = "collection-external";
  private final String ASSESSMENT_EXTERNAL = "assessment-external";
  private final String OFFLINE_ACTIVITY = "offline-activity";


  LearnerPortfolioStatsSubjectDomainService(DBI dbi) {
    this.learnerPortfolioStatsSubjectDomainDao =
        dbi.onDemand(LearnerPortfolioStatsSubjectDomainDao.class);
  }

  private Map<String, List<LearnerPortfolioStatsSubjectDomainResponseModel>> learnerPortfolioStatsSubjectDomain(
      List<LearnerPortfolioStatsSubjectDomainModel> learnerPortfolioStatsSubjectDomainModels) {
    List<LearnerPortfolioStatsSubjectDomainResponseModel> listLearnerPortfolioStatsSubject =
        new ArrayList<>();
    Map<String, List<LearnerPortfolioStatsSubjectDomainResponseModel>> learnerPortfolioStatsSubject =
        new HashMap<>();
    if (learnerPortfolioStatsSubjectDomainModels != null
        && !learnerPortfolioStatsSubjectDomainModels.isEmpty()) {
      Map<String, LearnerPortfolioStatsSubjectDomainResponseModel> learnerPortfolioStatsSubjectDomainModelMap =
          groupLearnerPortfolioStatsBySubjectDomain(learnerPortfolioStatsSubjectDomainModels);
      learnerPortfolioStatsSubjectDomainModelMap
          .forEach((subjectCode, learnerPortfolioSubjectDomainStat) -> {
            listLearnerPortfolioStatsSubject.add(learnerPortfolioSubjectDomainStat);
          });
      Collections.sort(listLearnerPortfolioStatsSubject,
          Comparator.comparing(LearnerPortfolioStatsSubjectDomainResponseModel::getCompetencySeq));
    }
    learnerPortfolioStatsSubject.put(LEARNER_PORTFOLIO_STATS, listLearnerPortfolioStatsSubject);

    return learnerPortfolioStatsSubject;
  }

  private Map<String, LearnerPortfolioStatsSubjectDomainResponseModel> groupLearnerPortfolioStatsBySubjectDomain(
      List<LearnerPortfolioStatsSubjectDomainModel> learnerPortfolioStatsSubjectDomainModels) {
    Map<String, LearnerPortfolioStatsSubjectDomainResponseModel> learnerPortfolioStatsSubjectDomainModelMap =
        new HashMap<>();
    learnerPortfolioStatsSubjectDomainModels.forEach(learnerPortfolioStatsSubjectDomainModel -> {
      String competencyCode = learnerPortfolioStatsSubjectDomainModel.getCompetencyCode();
      LearnerPortfolioStatsSubjectDomainResponseModel subjectDomainCompetencyStats = null;
      if (learnerPortfolioStatsSubjectDomainModelMap.get(competencyCode) != null) {
        subjectDomainCompetencyStats =
            learnerPortfolioStatsSubjectDomainModelMap.get(competencyCode);
      } else {
        subjectDomainCompetencyStats = new LearnerPortfolioStatsSubjectDomainResponseModel();
        subjectDomainCompetencyStats
            .setCompetencyCode(learnerPortfolioStatsSubjectDomainModel.getCompetencyCode());
        subjectDomainCompetencyStats
            .setCompetencyName(learnerPortfolioStatsSubjectDomainModel.getCompetencyName());
        subjectDomainCompetencyStats
            .setCompetencySeq(learnerPortfolioStatsSubjectDomainModel.getCompetencySeq());
        learnerPortfolioStatsSubjectDomainModelMap.put(competencyCode,
            subjectDomainCompetencyStats);
      }
      String collectionType = learnerPortfolioStatsSubjectDomainModel.getCollectionType();
      Integer count = learnerPortfolioStatsSubjectDomainModel.getCollectionCount();
      setCollectionCount(collectionType, count, subjectDomainCompetencyStats);

    });
    return learnerPortfolioStatsSubjectDomainModelMap;

  }

  private void setCollectionCount(String collectionType, Integer count,
      LearnerPortfolioStatsSubjectDomainResponseModel subjectDomainCompCompetencyStats) {
    if (collectionType != null) {
      if (collectionType.equalsIgnoreCase(ASSESSMENT)) {
        subjectDomainCompCompetencyStats.setAssessmentCount(count);
      } else if (collectionType.equalsIgnoreCase(COLLECTION)) {
        subjectDomainCompCompetencyStats.setCollectionCount(count);
      } else if (collectionType.equalsIgnoreCase(COLLECTION_EXTERNAL)) {
        subjectDomainCompCompetencyStats.setCollectionExternalCount(count);
      } else if (collectionType.equalsIgnoreCase(ASSESSMENT_EXTERNAL)) {
        subjectDomainCompCompetencyStats.setAssessmentExternalCount(count);
      } else if (collectionType.equalsIgnoreCase(OFFLINE_ACTIVITY)) {
        subjectDomainCompCompetencyStats.setOaCount(count);
      }
    }
  }

  Map<String, List<LearnerPortfolioStatsSubjectDomainResponseModel>> fetchLearnerPortfolioStatsSubjectDomain(
      LearnerPortfolioStatsSubjectDomainCommand command) {
    final List<LearnerPortfolioStatsSubjectDomainModel> learnerPortfolioStatsSubjectDomainModels =
        learnerPortfolioStatsSubjectDomainDao
            .fetchLearnerPortfolioStatsSubjectDomain(command.asBean());
    return learnerPortfolioStatsSubjectDomain(learnerPortfolioStatsSubjectDomainModels);
  }

}
