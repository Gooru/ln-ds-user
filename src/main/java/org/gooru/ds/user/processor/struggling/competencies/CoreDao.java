
package org.gooru.ds.user.processor.struggling.competencies;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 17-Oct-2019
 */
public interface CoreDao {

  @Mapper(UserModelMapper.class)
  @SqlQuery("SELECT id, username, first_name, last_name, display_name, thumbnail FROM users WHERE id = ANY(:userIds::uuid[]) AND is_deleted = false ")
  public List<UserModel> fetchUserDetails(@Bind("userIds") PGArray<String> userIds);
  
  @SqlQuery("SELECT user_id FROM class_member WHERE class_id = :classId::uuid AND is_active = false")
  public List<String> fetchClassMembers(@Bind("classId") String classId);
}
