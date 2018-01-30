package org.gooru.ds.user.processor.user.journey;

import java.util.List;

import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserJourneyDao {

    @Mapper(UserJourneyModelMapper.class)
    @SqlQuery("select class_id, class_code, class_title, course_id, course_title, "
                  + "time_spent, average_score, assessments_completed, total_assessments "
                  + "from overall_course_performance where user_id = :user and " 
                  + "class_id = any(:classIds) ")
    List<UserJourneyModel> fetchUserClassJourney(
        @BindBean UserJourneyCommand.UserJourneyCommandBean userJourneyCommandBean,
        @Bind("classIds") PGArray<String> classIds);

    @Mapper(UserJourneyModelMapper.class)
    @SqlQuery("select class_id, class_code, class_title, course_id, course_title, "
                  + "time_spent, average_score, assessments_completed, total_assessments "
                  + "from overall_course_performance where user_id = :user and " 
                  + "class_id IS NULL  and course_id = any(:courseIds)")
    List<UserJourneyModel> fetchUserILJourney(
        @BindBean UserJourneyCommand.UserJourneyCommandBean userJourneyCommandBean,
        @Bind("courseIds") PGArray<String> courseIds);
}
