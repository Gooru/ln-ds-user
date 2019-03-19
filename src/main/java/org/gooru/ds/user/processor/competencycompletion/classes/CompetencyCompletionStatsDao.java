package org.gooru.ds.user.processor.competencycompletion.classes;

import java.sql.Date;
import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 *  
 */
interface CompetencyCompletionStatsDao {

  @Mapper(CompetencyCompletionStatsModelMapper.class)
  @SqlQuery("SELECT uccs.class_id, SUM(uccs.total) AS total, SUM(uccs.completed) AS completed FROM (SELECT DISTINCT ON "
      + "(user_id, class_id) class_id, user_id, total, completed FROM user_class_competency_stats where class_id = :classId "
      + "AND user_id = ANY(:user) AND stats_date <= :statsDate ORDER BY user_id, class_id, stats_date desc) AS uccs "
      + "GROUP BY uccs.class_id")
  CompetencyCompletionStatsModel fetchClassCompetencyCompletionStats(@Bind("user") PGArray<String> user,
      @Bind("classId") String classId, @Bind("statsDate") Date statsDate);
  
}
