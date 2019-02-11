package org.gooru.ds.user.processor.atc.recompute;

import java.sql.Date;
import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface CompetencyStatsDao {

  @Mapper(CompetencyStatsMapper.class)
  @SqlQuery("SELECT DISTINCT ON (uccs.user_id, uccs.class_id) uccs.user_id, uccs.grade_id, uccs.total, uccs.completed, uccs.in_progress, "
      + "uccs.score, uccs.percent_completed, gm.grade FROM user_class_competency_stats as uccs LEFT JOIN grade_master gm ON "
      + "uccs.grade_id = gm.id where uccs.class_id = :classId AND uccs.course_id = :courseId AND uccs.user_id = ANY(:user) AND "
      + "uccs.subject_code = :subjectCode ORDER BY uccs.user_id, uccs.class_id desc")
  List<CompetencyStatsModel> fetchGradeCompetencyStats(@Bind("user") PGArray<String> user,
      @Bind("classId") String classId, @Bind("courseId") String courseId, @Bind("subjectCode") String subjectCode);

  //DB has a UNIQUE CONSTRAINT ON (user_id, class_id, course_id, month, year)
  @Mapper(CompetencyStatsMapper.class)
  @SqlQuery("SELECT DISTINCT ON (uccs.user_id, uccs.class_id) uccs.user_id, uccs.grade_id, uccs.total, uccs.completed, uccs.in_progress, "
      + "uccs.score, uccs.percent_completed, gm.grade FROM user_class_competency_stats as uccs LEFT JOIN grade_master gm ON "
      + "uccs.grade_id = gm.id where uccs.class_id = :classId AND uccs.course_id = :courseId AND uccs.user_id = ANY(:user) AND "
      + "uccs.subject_code = :subjectCode AND uccs.stats_date <= :statsDate ORDER BY uccs.user_id, uccs.class_id, uccs.stats_date desc")
  List<CompetencyStatsModel> fetchGradeCompetencyStatsTimeBound(@Bind("user") PGArray<String> user,
      @Bind("classId") String classId, @Bind("courseId") String courseId, @Bind("subjectCode") String subjectCode,
      @Bind("statsDate") Date statsDate);

}
