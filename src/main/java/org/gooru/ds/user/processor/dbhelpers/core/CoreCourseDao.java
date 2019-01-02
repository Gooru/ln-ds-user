package org.gooru.ds.user.processor.dbhelpers.core;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

/**
 * @author szgooru on 18-Jul-2018
 */
public interface CoreCourseDao {

  @SqlQuery("SELECT subject_bucket FROM course WHERE id = :courseId::uuid AND is_deleted = false")
  String fetchCourseSubject(@Bind("courseId") String courseId);

  @SqlQuery("SELECT jsonb_object_keys(aggregated_gut_codes) FROM course where id = :courseId::uuid AND is_deleted = false")
  List<String> fetchCourseCompetencies(@Bind("courseId") String courseId);
}
