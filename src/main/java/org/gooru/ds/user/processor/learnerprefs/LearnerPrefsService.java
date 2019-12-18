package org.gooru.ds.user.processor.learnerprefs;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class LearnerPrefsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LearnerPrefsService.class);

  private final LearnerPrefsDao learnerPrefsDao;


  LearnerPrefsService(DBI dbi) {
    this.learnerPrefsDao = dbi.onDemand(LearnerPrefsDao.class);
  }

  public LearnerPrefsModel fetchLearnerPrefs(LearnerPrefsCommand command) {
    LearnerPrefsCommand.LearnerPrefsCommandBean learnerPrefsCommandBean = command.asBean();
    LearnerPrefsModel result = null;
    if (learnerPrefsCommandBean.getSubject() != null && learnerPrefsCommandBean.getDomain() != null
        && learnerPrefsCommandBean.getCompCode() != null) {
      result = learnerPrefsDao.fetchLearnerPrefsBySubjectDomainComp(learnerPrefsCommandBean);
    } else if (learnerPrefsCommandBean.getSubject() != null
        && learnerPrefsCommandBean.getDomain() != null) {
      result = learnerPrefsDao.fetchLearnerPrefsBySubjectDomain(learnerPrefsCommandBean);
    } else if (learnerPrefsCommandBean.getSubject() != null) {
      result = learnerPrefsDao.fetchLearnerPrefsBySubject(learnerPrefsCommandBean);
    } else {
      result = learnerPrefsDao.fetchLearnerPrefs(learnerPrefsCommandBean);
    }
    if (result != null) {
      LOGGER.debug("LearnerPrefsModel: {}", result.toString());
    }
    return result != null ? result : new LearnerPrefsModel();
  }

}
