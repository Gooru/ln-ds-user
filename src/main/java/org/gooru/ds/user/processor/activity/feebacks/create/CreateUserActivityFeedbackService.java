package org.gooru.ds.user.processor.activity.feebacks.create;

import org.skife.jdbi.v2.DBI;


class CreateUserActivityFeedbackService {

  private final CreateUserActivityFeedbackDao createUserActivityFeedbackDao;

  CreateUserActivityFeedbackService(DBI dbi) {
    this.createUserActivityFeedbackDao = dbi.onDemand(CreateUserActivityFeedbackDao.class);
  }

  public void createUserActivityFeedback(CreateUserActivityFeedbackCommand command) {
    this.createUserActivityFeedbackDao
        .createUserActivityFeedback(command.asBean());
  }

}
