package org.gooru.ds.user.processor.userprefs.providers;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 13/1/18.
 */
interface UserPrefsProviderDao {

  @Mapper(UserPrefsProviderModelMapper.class)
  @SqlQuery("select pro.id, pro.name provider_name, pre.pref/1000::real pref from providers pro left join "
      + "user_prefs_provider pre on pro.id = pre.provider_id and pre.user_id = :user")
  List<UserPrefsProviderModel> fetchUserPrefsProvider(
      @BindBean UserPrefsProviderCommand.UserPrefsProviderCommandBean userPrefsProviderCommandBean);

}
