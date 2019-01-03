package org.gooru.ds.user.processor.userstats.provider;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 13/1/18.
 */
interface UserStatsProviderDao {

  @Mapper(UserStatsProviderModelMapper.class)
  @SqlQuery("select pro.id, pro.name provider_name, stat.counter from providers pro left join "
      + "user_stats_provider stat on pro.id = stat.provider_id and stat.user_id = :user "
      + " and stat.duration = :activeDuration")
  List<UserStatsProviderModel> fetchUserStatsProviders(
      @BindBean UserStatsProviderCommand.UserStatsProvidersCommandBean userStatsProvidersCommandBean);

}
