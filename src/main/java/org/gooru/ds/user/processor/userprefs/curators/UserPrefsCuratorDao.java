package org.gooru.ds.user.processor.userprefs.curators;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 13/1/18.
 */
interface UserPrefsCuratorDao {

  @Mapper(UserPrefsCuratorModelMapper.class)
  @SqlQuery("select cur.id, cur.name curator_name, pre.pref/1000::real pref from curators cur left join "
      + "user_prefs_curator pre on cur.id = pre.curator_id and pre.user_id = :user")
  List<UserPrefsCuratorModel> fetchUserPrefsCurator(
      @BindBean UserPrefsCuratorCommand.UserPrefsCuratorCommandBean userPrefsCuratorCommandBean);

}
