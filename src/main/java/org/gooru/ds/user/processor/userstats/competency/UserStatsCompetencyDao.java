package org.gooru.ds.user.processor.userstats.competency;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 11/1/18.
 */
interface UserStatsCompetencyDao {

  @Mapper(UserStatsCompetencyModelMapper.class)
  @SqlQuery("select in_progress, completed from user_stats_competency where duration = :activeDuration and "
      + " user_id = :user")
  UserStatsCompetencyModel fetchUserStatsCompetency(
      @BindBean UserStatsCompetencyCommand.UserStatsCompetencyCommandBean uerStatsCompetencyCommandBean);

}
