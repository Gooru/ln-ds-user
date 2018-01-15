package org.gooru.ds.user.processor.userperf.collections;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserPerfCollectionsDao {

    @Mapper(UserPerfCollectionsModelMapper.class)
    @SqlQuery("select collection_id, collection_title, collection_time_spent, collection_average_reaction"
                  + " from course_collection_performance where "
                  + " class_id = :classId  and course_id = :courseId and unit_id = :unitId and lesson_id = :lessonId "
                  + " and collection_type = 'collection' and user_id = :user")
    UserPerfCollectionsModel fetchUserPerfCollections(
        @BindBean UserPerfCollectionsCommand.UserPerfCollectionsCommandBean userPerfCollectionsCommandBean);

}
