package org.gooru.ds.user.processor.baselearnerprofile.read;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface ReadBaselineLearnerProfileDao {

  @SqlQuery("select lp_data from learner_profile_baselined WHERE class_id = :classId AND course_id = :courseId "
      + " AND user_id = :user AND tx_subject_code = :subject")
  String fetchProfileBaselinedForUserInClass(
      @BindBean ReadBaselineLearnerProfileCommand.ReadBaselineLearnerProfileCommandBean bean);

  @SqlQuery("select lp_data from learner_profile_baselined WHERE class_id is null AND course_id = :courseId "
      + " AND user_id = :user AND tx_subject_code = :subject")
  String fetchProfileBaselinedForUserInIL(
      @BindBean ReadBaselineLearnerProfileCommand.ReadBaselineLearnerProfileCommandBean bean);

}
