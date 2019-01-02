package org.gooru.ds.user.processor.grade.boundary;

import java.util.List;
import org.skife.jdbi.v2.DBI;

/**
 * @author szgooru on 19-Sep-2018
 */
public class GradeBoundaryListService {

  private final GradeBoundaryListDao dao;

  public GradeBoundaryListService(DBI dbi) {
    this.dao = dbi.onDemand(GradeBoundaryListDao.class);
  }

  public GradeBoundaryListModelResponse fetchGradeBoundaryList(GradeBoundaryListCommand command) {

    List<GradeBoundaryListModel> models = this.dao.fetchGradeBoundaryList(command.asBean());

    GradeBoundaryListModelResponse response = new GradeBoundaryListModelResponse();
    response.setDomains(models);
    return response;
  }
}
