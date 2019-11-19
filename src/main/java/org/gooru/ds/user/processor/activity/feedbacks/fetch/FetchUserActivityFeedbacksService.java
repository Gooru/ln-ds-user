package org.gooru.ds.user.processor.activity.feedbacks.fetch;

import java.util.List;
import org.skife.jdbi.v2.DBI;


class FetchUserActivityFeedbacksService {

  private final FetchUserActivityFeedbacksDao fetchUserActivityFeedbackDao;

  FetchUserActivityFeedbacksService(DBI dbi) {
    this.fetchUserActivityFeedbackDao = dbi.onDemand(FetchUserActivityFeedbacksDao.class);
  }

  public FetchUserActivityFeedbacksModelResponse fetchUserActivityFeedback(FetchUserActivityFeedbacksCommand command) {
    List<FetchUserActivityFeedbacksModel> models = this.fetchUserActivityFeedbackDao.fetchUserActivityFeedbacks(command.asBean());
    FetchUserActivityFeedbacksModelResponse  response= new FetchUserActivityFeedbacksModelResponse();
    response.setUserActivityFeedbacks(models);
    return response;
  }

}
