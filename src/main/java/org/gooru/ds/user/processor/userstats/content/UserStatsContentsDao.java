package org.gooru.ds.user.processor.userstats.content;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 13/1/18.
 */
interface UserStatsContentsDao {

    @Mapper(UserStatsContentsModelMapper.class)
    @SqlQuery("select audio, interactive, text, video, webpage, image from user_stats_content where "
                  + "duration = :activeDuration and  user_id = :user")
    UserStatsContentsModel fetchUserStatsContents(
        @BindBean UserStatsContentsCommand.UserStatsContentsCommandBean userStatsContentsCommandBean);

}
