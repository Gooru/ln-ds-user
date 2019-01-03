package org.gooru.ds.user.processor.activeuserlist;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 11/1/18.
 */
interface ActiveUserListDao {

  @SqlQuery("select user_id from active_user_stats where duration = :activeDuration and subject_code = :subject "
      + " order by last_activity_date desc offset :offset limit :limit")
  List<String> fetchActiveUserListForSubject(
      @BindBean ActiveUserListCommand.ActiveUserListCommandBean activeUserListCommandBean);

  @Mapper(ActiveUserListModelMapper.class)
  @SqlQuery("select a.user_id, a.username, a.first_name, a.last_name, a.thumbnail, b.grade, c.authority/1000::real "
      + " authority, c.reputation/1000::real reputation, c.citizenship/1000::real citizenship from "
      + " users_profile_master a, user_grade_map b, user_vectors c where a.user_id = any(:users) and "
      + " a.user_id = b.user_id  and b.subject_code = :subject and c.user_id = a.user_id")
  List<ActiveUserListModel> fetchUserDetails(
      @BindBean ActiveUserListCommand.ActiveUserListCommandBean activeUserListCommandBean,
      @Bind("users") PGArray<String> users);

}
