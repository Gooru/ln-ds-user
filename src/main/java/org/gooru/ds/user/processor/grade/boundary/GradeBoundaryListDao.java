package org.gooru.ds.user.processor.grade.boundary;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface GradeBoundaryListDao {

  @Mapper(GradeBoundaryListModelMapper.class)
  @SqlQuery("SELECT tx_domain_code, highline_tx_comp_code FROM grade_competency_bound WHERE grade_id = :grade")
  List<GradeBoundaryListModel> fetchGradeBoundaryList(
      @BindBean GradeBoundaryListCommand.GradeBoundaryListCommandBean bean);
}
