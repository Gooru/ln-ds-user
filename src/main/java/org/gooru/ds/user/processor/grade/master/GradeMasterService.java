package org.gooru.ds.user.processor.grade.master;

import java.util.List;
import org.gooru.ds.user.processor.grade.master.GradeMasterCommand.GradeMasterCommandBean;
import org.skife.jdbi.v2.DBI;

/**
 * @author szgooru on 21-Sep-2018
 */
class GradeMasterService {

  private final GradeMasterDao dao;

  GradeMasterService(DBI dbi) {
    this.dao = dbi.onDemand(GradeMasterDao.class);
  }

  GradeMasterModelResponse fetchGrades(GradeMasterCommand command) {
    GradeMasterModelResponse response = new GradeMasterModelResponse();
    GradeMasterCommandBean bean = command.asBean();

    List<GradeMasterModel> grades = this.dao.fetchGrades(bean.getSubject(), bean.getFwCode());
    response.setGrades(grades);
    return response;
  }
}
