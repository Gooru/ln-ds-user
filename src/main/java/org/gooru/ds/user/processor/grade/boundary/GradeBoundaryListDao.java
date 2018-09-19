package org.gooru.ds.user.processor.grade.boundary;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface GradeBoundaryListDao {

	@Mapper(GradeBoundaryListModelMapper.class)
	@SqlQuery("SELECT gcb.tx_domain_code, gcb.highline_tx_comp_code FROM grade_master gm, grade_competency_bound gcb WHERE gcb.grade_id = gm.id"
			+ " AND gm.tx_subject_code = :subject AND gm.grade = :grade")
	List<GradeBoundaryListModel> fetchGradeBoundaryList(
			@BindBean GradeBoundaryListCommand.GradeBoundaryListCommandBean bean);
}
