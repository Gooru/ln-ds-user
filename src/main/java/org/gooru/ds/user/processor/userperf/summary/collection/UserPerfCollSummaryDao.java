package org.gooru.ds.user.processor.userperf.summary.collection;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserPerfCollSummaryDao {

    @Mapper(UserPerfCollSummaryModelMapper.class)
    @SqlQuery("select resource_id, score, reaction, time_spent, resource_type, path_id, resource_title, resource_content_type " 
                  + "from ds_master where actor_id = :user and ((class_id IS NULL AND :classId IS NULL) OR class_id = :classId) "
                  + "and course_id = :courseId and lesson_id = :lessonId "
                  + "and unit_id = :unitId and collection_id = :collectionId and session_id = :sessionId "
                  + "and event_name = 'collection.resource.play' and event_type = 'stop' and collection_type = "
                  + "'collection'")
    List<UserPerfCollSummaryModel> fetchUserPerfCollSummary(
        @BindBean UserPerfCollSummaryCommand.UserPerfCollSummaryCommandBean userPerfCollSummaryCommandBean);
    
    @Mapper(UserPerfCollSummaryModelMapper.class)
    @SqlQuery("select resource_id, score, reaction, time_spent, resource_type, path_id, resource_title, resource_content_type " 
                  + "from ds_master where actor_id = :user and collection_id = :collectionId and session_id = :sessionId "
                  + "and event_name = 'collection.resource.play' and event_type = 'stop' and collection_type = "
                  + "'collection'")
    List<UserPerfCollSummaryModel> fetchUserPerfCollSummaryforComp(
        @BindBean UserPerfCollSummaryCommand.UserPerfCollSummaryCommandBean userPerfCollSummaryCommandBean);

}
