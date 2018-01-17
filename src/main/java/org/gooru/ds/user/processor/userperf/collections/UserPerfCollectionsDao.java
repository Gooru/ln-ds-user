package org.gooru.ds.user.processor.userperf.collections;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserPerfCollectionsDao {

    @Mapper(UserPerfCollectionsModelMapper.class)
    @SqlQuery("select collection_id, collection_title, collection_time_spent, collection_average_reaction, "
                  + " collection_score, latest_session_id, collection_type, sequence_id "
                  + " from course_collection_performance where "
                  + " ((class_id IS NULL AND :classId IS NULL) OR class_id = :classId) "
                  + " and course_id = :courseId and unit_id = :unitId and lesson_id = :lessonId "
                  + " and user_id = :user ORDER BY sequence_id")
    List<UserPerfCollectionsModel> fetchUserPerfCollections(
        @BindBean UserPerfCollectionsCommand.UserPerfCollectionsCommandBean userPerfCollectionsCommandBean);

}
