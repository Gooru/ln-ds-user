package org.gooru.ds.user.processor.userstats.journeys;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 11/1/18.
 */
interface UserStatsJourneysDao {

    @Mapper(UserStatsJourneysModelMapper.class)
    @SqlQuery("select independent_journeys, class_journeys from user_stats_journeys where duration = "
                  + ":activeDuration and  user_id = :user")
    UserStatsJourneysModel fetchUserStatsJourneys(
        @BindBean UserStatsJourneysCommand.UserStatsJourneysCommandBean userStatsJourneysCommandBean);

}
