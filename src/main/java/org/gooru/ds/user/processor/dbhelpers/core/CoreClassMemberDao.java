package org.gooru.ds.user.processor.dbhelpers.core;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

/**
 * @author szgooru on 18-Jul-2018
 */
public interface CoreClassMemberDao {

  @SqlQuery("SELECT user_id FROM class_member WHERE class_id = :classId::uuid")
  List<String> fetchClassMembers(@Bind("classId") String classId);
}
