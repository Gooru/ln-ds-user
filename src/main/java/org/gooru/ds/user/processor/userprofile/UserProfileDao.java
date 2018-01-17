package org.gooru.ds.user.processor.userprofile;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 17/1/18.
 */
interface UserProfileDao {

    @Mapper(UserProfileModelMapper.class)
    @SqlQuery("select user_id, username, email, first_name, last_name, user_category, tenant_id, thumbnail, gender, "
                  + "about, school, school_district, country, state from users_profile_master where user_id = :user")
    UserProfileModel fetchUserProfile(@BindBean UserProfileCommand.UserProfileCommandBean userProfileCommandBean);

}
