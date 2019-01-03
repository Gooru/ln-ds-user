package org.gooru.ds.user.processor.atc.pvc;

import java.util.List;
import java.util.UUID;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import io.vertx.core.json.JsonObject;

interface ListCourseCompetencyDao {

  @SqlQuery("select aggregated_gut_codes from course where id = :courseId")
  String fetchCourseCompetencyList(@Bind("courseId") UUID courseId);
}
