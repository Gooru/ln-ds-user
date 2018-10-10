package org.gooru.ds.user.processor.grade.master;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru on 21-Sep-2018
 */
public interface GradeMasterDao {

	@Mapper(GradeMasterModelMapper.class)
	@SqlQuery("SELECT id, grade, description, thumbnail, grade_seq FROM grade_master WHERE tx_subject_code = :subject")
	List<GradeMasterModel> fetchGrades(@Bind("subject") String subject);
}
