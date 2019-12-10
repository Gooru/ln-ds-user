package org.gooru.ds.user.processor.stats.learnerportfolio.subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;


class LearnerPortfolioStatsSubjectService {


  private final LearnerPortfolioStatsSubjectDao learnerPortfolioStatsSubjectDao;

  private final String LEARNER_PORTFOLIO_STATS = "learnerPortfolioStats";
  private final String COLLECTION = "collection";
  private final String ASSESSMENT = "assessment";
  private final String COLLECTION_EXTERNAL = "collection-external";
  private final String ASSESSMENT_EXTERNAL = "assessment-external";
  private final String OFFLINE_ACTIVITY = "offline-activity";


  LearnerPortfolioStatsSubjectService(DBI dbi) {
    this.learnerPortfolioStatsSubjectDao = dbi.onDemand(LearnerPortfolioStatsSubjectDao.class);
  }

  private Map<String, List<LearnerPortfolioStatsSubjectResponseModel>> learnerPortfolioStatsSubject(
      List<LearnerPortfolioStatsSubjectModel> learnerPortfolioStatsSubjectModels) {
    List<LearnerPortfolioStatsSubjectResponseModel> listLearnerPortfolioStatsSubject =
        new ArrayList<>();
    Map<String, List<LearnerPortfolioStatsSubjectResponseModel>> learnerPortfolioStatsSubject =
        new HashMap<>();
    if (learnerPortfolioStatsSubjectModels != null
        && !learnerPortfolioStatsSubjectModels.isEmpty()) {
      Map<String, LearnerPortfolioStatsSubjectResponseModel> learnerPortfolioStatsSubjectModelMap =
          groupLearnerPortfolioStatsBySubject(learnerPortfolioStatsSubjectModels);
      learnerPortfolioStatsSubjectModelMap.forEach((domainCode, learnerPortfolioSubjectStat) -> {
        listLearnerPortfolioStatsSubject.add(learnerPortfolioSubjectStat);
      });
      Collections.sort(listLearnerPortfolioStatsSubject,
          Comparator.comparing(LearnerPortfolioStatsSubjectResponseModel::getDomainSeq));
    }
    learnerPortfolioStatsSubject.put(LEARNER_PORTFOLIO_STATS, listLearnerPortfolioStatsSubject);

    return learnerPortfolioStatsSubject;
  }

  private Map<String, LearnerPortfolioStatsSubjectResponseModel> groupLearnerPortfolioStatsBySubject(
      List<LearnerPortfolioStatsSubjectModel> learnerPortfolioStatsSubjectModels) {
    Map<String, LearnerPortfolioStatsSubjectResponseModel> learnerPortfolioStatsSubjectModelMap =
        new HashMap<>();
    learnerPortfolioStatsSubjectModels.forEach(learnerPortfolioStatsSubjectModel -> {
      String domainCode = learnerPortfolioStatsSubjectModel.getDomainCode();
      LearnerPortfolioStatsSubjectResponseModel subjectDomainCompetencyStats = null;
      if (learnerPortfolioStatsSubjectModelMap.get(domainCode) != null) {
        subjectDomainCompetencyStats = learnerPortfolioStatsSubjectModelMap.get(domainCode);
      } else {
        subjectDomainCompetencyStats = new LearnerPortfolioStatsSubjectResponseModel();
        subjectDomainCompetencyStats
            .setDomainCode(learnerPortfolioStatsSubjectModel.getDomainCode());
        subjectDomainCompetencyStats
            .setDomainName(learnerPortfolioStatsSubjectModel.getDomainName());
        subjectDomainCompetencyStats.setDomainSeq(learnerPortfolioStatsSubjectModel.getDomainSeq());
        learnerPortfolioStatsSubjectModelMap.put(domainCode, subjectDomainCompetencyStats);
      }
      String collectionType = learnerPortfolioStatsSubjectModel.getCollectionType();
      Integer count = learnerPortfolioStatsSubjectModel.getCollectionCount();
      setCollectionCount(collectionType, count, subjectDomainCompetencyStats);


    });
    return learnerPortfolioStatsSubjectModelMap;
  }

  private void setCollectionCount(String collectionType, Integer count,
      LearnerPortfolioStatsSubjectResponseModel subjectCompetencyStats) {
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

  Map<String, List<LearnerPortfolioStatsSubjectResponseModel>> fetchLearnerPortfolioStatsSubject(
      LearnerPortfolioStatsSubjectCommand command) {
    final List<LearnerPortfolioStatsSubjectModel> learnerSubjectCompetencyStatsModels =
        learnerPortfolioStatsSubjectDao.fetchLearnerPortfolioStatsSubject(command.asBean());
    return learnerPortfolioStatsSubject(learnerSubjectCompetencyStatsModels);
  }



}
