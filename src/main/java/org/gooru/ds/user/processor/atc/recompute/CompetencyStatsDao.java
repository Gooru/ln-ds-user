package org.gooru.ds.user.processor.atc.recompute;

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
  @SqlQuery("SELECT DISTINCT ON (user_id, class_id) user_id, grade_id, total, completed, in_progress, score, percent_completed "
      + "from user_class_competency_stats where class_id = :classId AND course_id = :courseId AND user_id = ANY(:user) AND subject_code = :subjectCode "
      + "ORDER BY user_id, class_id, updated_at desc")
  List<CompetencyStatsModel> fetchGradeCompetencyStats(@Bind("user") PGArray<String> user,
      @Bind("classId") String classId, @Bind("courseId") String courseId, @Bind("subjectCode") String subjectCode);

  //DB has a UNIQUE CONSTRAINT ON (user_id, class_id, course_id, month, year)
  @Mapper(CompetencyStatsMapper.class)
  @SqlQuery("SELECT user_id, grade_id, total, completed, in_progress, score, percent_completed "
      + "FROM user_class_competency_stats where class_id = :classId AND course_id = :courseId AND user_id = ANY(:user) AND subject_code = :subjectCode "
      + "AND month = :month AND year = :year ORDER BY user_id desc")
  List<CompetencyStatsModel> fetchGradeCompetencyStatsTimeBound(@Bind("user") PGArray<String> user,
      @Bind("classId") String classId, @Bind("courseId") String courseId, @Bind("subjectCode") String subjectCode,
      @Bind("month") Integer month, @Bind("year") Integer year);

}
