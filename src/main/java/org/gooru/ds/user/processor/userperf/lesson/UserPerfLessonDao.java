package org.gooru.ds.user.processor.userperf.lesson;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserPerfLessonDao {

    @Mapper(UserPerfLessonModelMapper.class)
    @SqlQuery("select lesson_id, lesson_title, lesson_asmt_time_spent, lesson_asmt_score, lesson_coll_time_spent "
                  + "from course_performance where "
                  + "class_id = :classId  and course_id = :courseId and unit_id = :unitId and user_id = :user")
    List<UserPerfLessonModel> fetchUserPerfLesson(
        @BindBean UserPerfLessonCommand.UserPerfLessonCommandBean userPerfLessonCommandBean);

}
