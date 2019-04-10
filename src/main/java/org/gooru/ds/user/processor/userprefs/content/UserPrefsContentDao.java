package org.gooru.ds.user.processor.userprefs.content;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 12/1/18.
 */
interface UserPrefsContentDao {

  @Mapper(UserPrefsContentModelMapper.class)
  @SqlQuery("select audio_pref, interactive_pref, text_pref, video_pref, webpage_pref, image_pref "
      + " from learner_prefs_per_million where user_id = :user")
  UserPrefsContentModel fetchUserPrefsContent(
      @BindBean UserPrefsContentCommand.UserPrefsContentCommandBean userStatsTimespentCommandBean);

}
