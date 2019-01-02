package org.gooru.ds.user.processor.grade.boundary;

import java.util.List;

/**
 * @author szgooru on 19-Sep-2018
 */
public class GradeBoundaryListModelResponse {

  private List<GradeBoundaryListModel> domains;

  public List<GradeBoundaryListModel> getDomains() {
    return domains;
  }

  public void setDomains(List<GradeBoundaryListModel> domains) {
    this.domains = domains;
  }

}
