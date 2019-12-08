package org.gooru.ds.user.processor.learnervectors;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class LearnerVectorsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LearnerVectorsService.class);

  private final LearnerVectorsDao learnerVectorsDao;


  LearnerVectorsService(DBI dbi) {
    this.learnerVectorsDao = dbi.onDemand(LearnerVectorsDao.class);
  }

  public LearnerVectorsModel fetchLearnerVectors(LearnerVectorsCommand command) {
    LearnerVectorsCommand.LearnerVectorsCommandBean learnerVectorsCommandBean = command.asBean();
    LearnerVectorsModel result = null;
    if (learnerVectorsCommandBean.getSubject() != null
        && learnerVectorsCommandBean.getDomain() != null) {
      result = learnerVectorsDao.fetchLearnerVectorsBySubjectDomain(learnerVectorsCommandBean);
    } else if (learnerVectorsCommandBean.getSubject() != null) {
      result = learnerVectorsDao.fetchLearnerVectorsBySubject(learnerVectorsCommandBean);
    } else {
      result = learnerVectorsDao.fetchLearnerVectors(learnerVectorsCommandBean);
    }
    if (result != null) {
      LOGGER.debug("LearnerVectorsModel: {}", result.toString());
    }
    return result != null ? result : new LearnerVectorsModel();
  }

}
