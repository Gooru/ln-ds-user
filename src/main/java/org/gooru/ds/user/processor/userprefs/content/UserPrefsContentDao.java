package org.gooru.ds.user.processor.userprefs.content;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 12/1/18.
 */
interface UserPrefsContentDao {

  @Mapper(UserPrefsContentModelMapper.class)
  @SqlQuery("select audio/1000::real audio, interactive/1000::real interactive, text/1000::real textual, "
      + "video/1000::real video, webpage/1000::real webpage, image/1000::real image "
      + "from user_prefs_content where user_id = :user")
  UserPrefsContentModel fetchUserPrefsContent(
      @BindBean UserPrefsContentCommand.UserPrefsContentCommandBean userStatsTimespentCommandBean);

}
