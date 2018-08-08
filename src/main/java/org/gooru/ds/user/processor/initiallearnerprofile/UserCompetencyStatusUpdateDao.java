package org.gooru.ds.user.processor.initiallearnerprofile;

import java.sql.Timestamp;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;

/**
 * @author mukul@gooru
 * 
 */
public abstract class UserCompetencyStatusUpdateDao {
		
	@SqlBatch("INSERT INTO user_competency_status (user_id, comp_mcomp_id, completion_status, created_at, updated_at) "
			+ "VALUES(:userId, :competencyCode, :status, :createdAt, :updatedAt) ON CONFLICT DO NOTHING")
	protected abstract void UpdateUserCompetencyStatus(@BindBean List<InitialLearnerProfileReadModel> models, @Bind("userId") String userId,
			@Bind("createdAt") Timestamp createdAt, @Bind("updatedAt") Timestamp updatedAt);


}
