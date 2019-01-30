
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru Created On 18-Jan-2019
 */
public interface CoreClassDao {

  @Mapper(CoreClassMapper.class)
  @SqlQuery("SELECT grade_current, grade_lower_bound, grade_upper_bound, preference FROM class WHERE id = :classId::uuid")
  CoreClass fetchClassGrades(@Bind("classId") String classId);

  @SqlQuery("SELECT user_id FROM class_member WHERE class_id = :classId::uuid AND class_member_status = 'joined'")
  List<String> fetchClassMembers(@Bind("classId") String classId);
}
