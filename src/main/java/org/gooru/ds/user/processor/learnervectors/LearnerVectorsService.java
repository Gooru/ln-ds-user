package org.gooru.ds.user.processor.learnervectors;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class LearnerVectorsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LearnerVectorsService.class);

  private final LearnerVectorsDao learnerVectorsContentDao;


  LearnerVectorsService(DBI dbi) {
    this.learnerVectorsContentDao = dbi.onDemand(LearnerVectorsDao.class);
  }

  public LearnerVectorsModel fetchLearnerVectors(LearnerVectorsCommand command) {
    LearnerVectorsCommand.LearnerVectorsCommandBean learnerVectorsCommandBean = command.asBean();
    LearnerVectorsModel result = null;
    if (learnerVectorsCommandBean.getSubject() != null) {
      result = learnerVectorsContentDao.fetchUserVectorsBySubject(learnerVectorsCommandBean);
    } else {
      result = learnerVectorsContentDao.fetchUserVectors(learnerVectorsCommandBean);
    }
    if (result != null) {
      LOGGER.debug("LearnerVectorsModel: {}", result.toString());
    }
    return result != null ? result : new LearnerVectorsModel();
  }

}
