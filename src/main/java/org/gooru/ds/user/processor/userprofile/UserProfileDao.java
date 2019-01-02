package org.gooru.ds.user.processor.userprofile;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 17/1/18.
 */
interface UserProfileDao {

  @Mapper(UserProfileModelMapper.class)
  @SqlQuery("select upm.user_id, upm.username, upm.email, upm.first_name, upm.last_name, upm.user_category, upm"
      + ".tenant_id, upm.thumbnail, upm.gender, upm.about, upm.school, upm.school_district, upm.country, "
      + "upm.state, uv.authority/1000::real authority, uv.reputation/1000::real "
      + "reputation, uv.citizenship/1000::real citizenship  from users_profile_master upm, user_vectors  "
      + "uv where upm.user_id = :user and upm.user_id = uv.user_id")
  UserProfileModel fetchUserProfile(
      @BindBean UserProfileCommand.UserProfileCommandBean userProfileCommandBean);

}
