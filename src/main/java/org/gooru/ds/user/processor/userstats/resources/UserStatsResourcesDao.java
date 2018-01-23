package org.gooru.ds.user.processor.userstats.resources;

import java.util.List;

import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesCommand;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesModel;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesModelMapper;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 */
interface UserStatsResourcesDao {

    @Mapper(UserStatsResourcesModelMapper.class)
    @SqlQuery("select resource_id, resource_title, path_id, resource_time_spent from userstat_resource_timespent where "
                  + "user_id = :user and resource_content_type = :contentType and duration = :activeDuration "
                  + "order by resource_time_spent desc offset :offset limit :limit")
    List<UserStatsResourcesModel> fetchUserStatsResource(
        @BindBean UserStatsResourcesCommand.UserStatsResourceCommandBean userStatsResourceCommandBean);


}
