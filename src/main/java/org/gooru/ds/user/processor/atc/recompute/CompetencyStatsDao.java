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
      + "from user_class_competency_stats where class_id = :classId AND user_id = ANY(:user) AND subject_code = :subjectCode "
      + "ORDER BY user_id, class_id, updated_at desc")
  List<CompetencyStatsModel> fetchGradeCompetencyStats(@Bind("user") PGArray<String> user,
      @Bind("classId") String classId, @Bind("subjectCode") String subjectCode);

  @Mapper(CompetencyStatsMapper.class)
  @SqlQuery("SELECT DISTINCT ON (user_id, class_id) user_id, grade_id, total, completed, in_progress, score, percent_completed "
      + "FROM user_class_competency_stats where class_id = :classId AND user_id = ANY(:user) AND subject_code = :subjectCode "
      + "AND extract(month from updated_at) <= :month AND extract(year from updated_at) <= :year ORDER BY user_id, class_id, updated_at desc")
  List<CompetencyStatsModel> fetchGradeCompetencyStatsTimeBound(@Bind("user") PGArray<String> user,
      @Bind("classId") String classId, @Bind("subjectCode") String subjectCode,
      @Bind("month") Integer month, @Bind("year") Integer year);

}
