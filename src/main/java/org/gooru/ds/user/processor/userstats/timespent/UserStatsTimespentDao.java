package org.gooru.ds.user.processor.userstats.timespent;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 12/1/18.
 */
interface UserStatsTimespentDao {

    @Mapper(UserStatsTimespentModelMapper.class)
    @SqlQuery("select audio, interactive, text, video, webpage, image from user_stats_timespent where "
                  + "duration = :activeDuration and  user_id = :user")
    UserStatsTimespentModel fetchUserStatsTimespent(
        @BindBean UserStatsTimespentCommand.UserStatsTimespentCommandBean userStatsTimespentCommandBean);

}
