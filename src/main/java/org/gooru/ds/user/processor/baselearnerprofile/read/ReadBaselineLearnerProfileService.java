package org.gooru.ds.user.processor.baselearnerprofile.read;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ReadBaselineLearnerProfileService {

  private final static Logger LOGGER = LoggerFactory
      .getLogger(ReadBaselineLearnerProfileService.class);

  private final ReadBaselineLearnerProfileDao dao;

  ReadBaselineLearnerProfileService(DBI dbi) {
    this.dao = dbi.onDemand(ReadBaselineLearnerProfileDao.class);
  }

  String fetchBaselineLearnerProfile(
      ReadBaselineLearnerProfileCommand command) {
    if (command.getClassId() != null) {
      return dao.fetchProfileBaselinedForUserInClass(command.asBean());
    } else {
      return dao.fetchProfileBaselinedForUserInIL(command.asBean());
    }
  }

}
