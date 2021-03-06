package org.gooru.ds.user.processor.userperf.assessments;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserPerfAssessmentsDao {

  @Mapper(UserPerfAssessmentsModelMapper.class)
  @SqlQuery("select collection_id, collection_title, latest_session_id, collection_time_spent, collection_score, "
      + "collection_average_reaction" + " from course_collection_performance where "
      + " ((class_id IS NULL AND :classId IS NULL) OR class_id = :classId) "
      + " and course_id = :courseId and unit_id = :unitId and lesson_id = :lessonId "
      + " and collection_type = 'assessment' and user_id = :user")
  List<UserPerfAssessmentsModel> fetchUserPerfAssessments(
      @BindBean UserPerfAssessmentsCommand.UserPerfAssessmentsCommandBean userPerfAssessmentsCommandBean);

}
