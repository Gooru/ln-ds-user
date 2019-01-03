package org.gooru.ds.user.processor.userstats.curator;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 13/1/18.
 */
interface UserStatsCuratorDao {

  @Mapper(UserStatsCuratorModelMapper.class)
  @SqlQuery("select cur.id, cur.name curator_name, stat.counter from curators cur left join "
      + "user_stats_curator stat on cur.id = stat.curator_id and stat.user_id = :user "
      + " and stat.duration = :activeDuration")
  List<UserStatsCuratorModel> fetchUserStatsCurator(
      @BindBean UserStatsCuratorCommand.UserStatsCuratorsCommandBean userStatsCuratorsCommandBean);

}
