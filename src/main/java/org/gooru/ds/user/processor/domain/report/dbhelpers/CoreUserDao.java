
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import java.util.List;
import java.util.UUID;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public interface CoreUserDao {

  @Mapper(UserModelMapper.class)
  @SqlQuery("SELECT id, first_name, last_name, thumbnail FROM users WHERE id = ANY(:userIds) AND is_deleted = false")
  List<UserModel> fetchUserDetails(@Bind("userIds") PGArray<UUID> userIds);
}
