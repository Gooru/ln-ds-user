package org.gooru.ds.user.processor.user.portfolio.content.perf.items;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
@LogSqlFactory
interface UserPortfolioUniqueItemPerfDao {

  @Mapper(UserPortfolioUniqueItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, updated_at, status, path_id, path_type, content_source from (SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, updated_at, status, path_id, path_type, content_source, row_number() over (partition by collection_id order by updated_at desc) r FROM collection_performance "
      + "WHERE actor_id = ANY(:userIds) AND date_in_time_zone <= :dateUntil "
      + "AND (:contentSource IS NULL OR content_source = :contentSource) "
      + "AND (:month IS NULL OR extract(month from date_in_time_zone) = :month) "
      + "AND (:year IS NULL OR extract(year from date_in_time_zone) = :year) "
      + "ORDER BY updated_at DESC) AS a WHERE r=1 OFFSET :offset LIMIT :limit")
  List<UserPortfolioUniqueItemPerfModel> fetchUniqueItemsPerformance(
      @BindBean UserPortfolioUniqueItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean,
      @Bind("userIds") PGArray<String> userIds);
  
  @Mapper(UserPortfolioUniqueItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, updated_at, status, path_id, path_type, content_source from (SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, updated_at, status, path_id, path_type, content_source, row_number() over (partition by collection_id order by updated_at desc) r FROM collection_performance "
      + "WHERE actor_id = ANY(:userIds) AND path_id > 0 and path_type IS NOT NULL "
      + "AND date_in_time_zone <= :dateUntil "
      + "AND (:contentSource IS NULL OR content_source = :contentSource) "
      + "AND (:month IS NULL OR extract(month from date_in_time_zone) = :month) "
      + "AND (:year IS NULL OR extract(year from date_in_time_zone) = :year) "
      + "ORDER BY updated_at DESC) AS a WHERE r=1 OFFSET :offset LIMIT :limit")
  List<UserPortfolioUniqueItemPerfModel> fetchUniqueSuggestedItemsPerformance(
      @BindBean UserPortfolioUniqueItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean,
      @Bind("userIds") PGArray<String> userIds);

}
