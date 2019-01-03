package org.gooru.ds.user.processor.userperf.course;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserPerfCourseDao {

  @Mapper(UserPerfCourseBaseModelMapper.class)
  @SqlQuery("select distinct on (unit_id) unit_id, class_id, class_code, class_title, course_id, course_title, "
      + "course_asmt_time_spent, course_asmt_score, course_coll_time_spent, "
      + "unit_id, unit_title, unit_asmt_time_spent, unit_asmt_score, unit_coll_time_spent, unit_sequence_id, "
      + "course_assessments_complete, total_assessments " + "from course_performance where "
      + "((class_id IS NULL AND :classId IS NULL) OR class_id = :classId) "
      + "and course_id = :courseId and user_id = :user ORDER BY unit_id, unit_sequence_id")
  List<UserPerfCourseBaseModel> fetchUserPerfCourse(
      @BindBean UserPerfCourseCommand.UserPerfCourseCommandBean userPerfCourseCommandBean);

}
