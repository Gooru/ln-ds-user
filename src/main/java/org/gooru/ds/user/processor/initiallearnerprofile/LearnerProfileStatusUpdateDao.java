package org.gooru.ds.user.processor.initiallearnerprofile;


import java.sql.Timestamp;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;


/**
 * @author mukul@gooru
 * 
 */
public abstract class LearnerProfileStatusUpdateDao {	

	@SqlBatch("INSERT INTO learner_profile_competency_status(tx_subject_code, user_id, gut_code, status, profile_source, created_at, updated_at) "
			+ "VALUES(:subjectCode, :userId, :competencyCode, :status, :profileSource, :createdAt, :updatedAt) ON CONFLICT (user_id, gut_code) "
			+ "DO UPDATE SET status = :status, updated_at = :updatedAt WHERE learner_profile_competency_status.status "
			+ "<= EXCLUDED.status")
	protected abstract void UpdateLearnerProfileCompetencyStatus(@BindBean List<InitialLearnerProfileReadModel> models, @Bind("userId") String userId,
			@Bind("createdAt") Timestamp createdAt, @Bind("updatedAt") Timestamp updatedAt);

	
	@SqlBatch("INSERT INTO learner_profile_competency_status_ts(tx_subject_code, user_id, gut_code, status, profile_source, created_at, updated_at) "
			+ "VALUES(:subjectCode, :userId, :competencyCode, :status, :profileSource, :createdAt, :updatedAt) ON CONFLICT (user_id, gut_code) "
			+ "DO UPDATE SET status = :status, updated_at = :updatedAt WHERE learner_profile_competency_status_ts.status "
			+ "<= EXCLUDED.status")
	protected abstract void UpdateLearnerProfileCompetencyStatusTs(@BindBean List<InitialLearnerProfileReadModel> models, @Bind("userId") String userId,
			@Bind("createdAt") Timestamp createdAt, @Bind("updatedAt") Timestamp updatedAt);
}
