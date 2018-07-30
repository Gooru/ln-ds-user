package org.gooru.ds.user.processor.initiallearnerprofile;

import java.sql.Timestamp;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public abstract class GradeMapUpdateDao {

	@SqlUpdate("INSERT INTO user_grade_map(subject_code, user_id, grade) "
			+ "VALUES(:subjectCode, :userId, :grade) ON CONFLICT DO NOTHING ")
	protected abstract void UpdateLearnerProfileCompetencyStatus(@Bind("subjectCode") String subjectCode, @Bind("userId") String userId,
			@Bind("grade") String grade);
}
