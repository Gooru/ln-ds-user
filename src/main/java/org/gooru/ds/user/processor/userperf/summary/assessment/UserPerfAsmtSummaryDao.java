package org.gooru.ds.user.processor.userperf.summary.assessment;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserPerfAsmtSummaryDao {

    @Mapper(UserPerfAsmtSummaryModelMapper.class)
    @SqlQuery("select resource_id, score, reaction, time_spent, resource_type, path_id " + " from ds_master "
                  + "where actor_id = :user and ((class_id IS NULL AND :classId IS NULL) OR class_id = :classId) "
                  + "and course_id = :courseId and lesson_id = :lessonId "
                  + "and unit_id = :unitId and collection_id = :assessmentId and session_id = :sessionId "
                  + "and event_name = 'collection.resource.play' and event_type = 'stop'")
    List<UserPerfAsmtSummaryModel> fetchUserPerfAsmtSummary(
        @BindBean UserPerfAsmtSummaryCommand.UserPerfAsmtSummaryCommandBean userPerfAsmtSummaryCommandBean);

}
