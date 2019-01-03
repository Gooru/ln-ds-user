package org.gooru.ds.user.processor.baselearnerprofile;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;


/**
 * @author mukul@gooru
 * 
 */
public abstract class LearnerProfileBaselineUpdateDao {

  @SqlBatch("INSERT INTO baseline_learner_profile (tx_subject_code, class_id, course_id, user_id, gut_code, status, created_at) "
      + "VALUES(:subjectCode, :classId, :courseId, :userId, :competencyCode, :status, :createdAt) ON CONFLICT DO NOTHING")
  protected abstract void UpdateLearnerBaselineProfile(
      @BindBean List<LearnerProfileReadModel> models, @Bind("classId") String classId,
      @Bind("courseId") String courseId, @Bind("createdAt") Timestamp createdAt);

  @SqlBatch("INSERT INTO baseline_learner_profile (tx_subject_code, course_id, user_id, gut_code, status, created_at) "
      + "VALUES(:subjectCode, :courseId, :userId, :competencyCode, :status, :createdAt) ON CONFLICT DO NOTHING")
  protected abstract void UpdateLearnerBaselineProfileForIL(
      @BindBean List<LearnerProfileReadModel> models, @Bind("courseId") String courseId,
      @Bind("createdAt") Timestamp createdAt);
}
