package org.gooru.ds.user.processor.userperf.competency.collections;

import java.util.List;

import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsCommand;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsModel;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsModelMapper;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

interface UserPerfCompetencyCollectionsDao {

    @Mapper(UserPerfCompetencyCollectionsModelMapper.class)
    @SqlQuery(
        "select collection_id, collection_title, latest_session_id, collection_time_spent, collection_score, "
            + "collection_average_reaction, collection_type "
            + " from competency_collection_performance where "
            + " competency_code = :competencyCode and "            
            + " user_id = :user")
    List<UserPerfCompetencyCollectionsModel> fetchUserPerfCompetencyCollections(
        @BindBean UserPerfCompetencyCollectionsCommand.UserPerfCompetencyCollectionsCommandBean userPerfCompetencyCollectionsCommandBean);


}
