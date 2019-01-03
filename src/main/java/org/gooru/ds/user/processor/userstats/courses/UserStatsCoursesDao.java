package org.gooru.ds.user.processor.userstats.courses;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 13/1/18.
 */
interface UserStatsCoursesDao {

  @Mapper(UserStatsCoursesModelMapper.class)
  @SqlQuery("select class_id, course_id, class_title, course_title, performance, "
      + "completion, timespent, started_in_duration from "
      + " user_stats_courses where user_id = :user and duration = :activeDuration")
  List<UserStatsCoursesModel> fetchUserStatsCourses(
      @BindBean UserStatsCoursesCommand.UserStatsCoursesCommandBean bean);
}
