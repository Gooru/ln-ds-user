package org.gooru.ds.user.processor.user.portfolio.content.perf.item;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
@LogSqlFactory
interface UserPortfolioItemPerfDao {

  @Mapper(UserPortfolioItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, updated_at, status, path_id, path_type, content_source FROM collection_performance "
      + "WHERE actor_id = :userId AND collection_id = :itemId AND date_in_time_zone <= :dateUntil "
      + "AND (:contentSource IS NULL OR content_source = :contentSource) "
      + "AND (:month IS NULL OR extract(month from date_in_time_zone) = :month) "
      + "AND (:year IS NULL OR extract(year from date_in_time_zone) = :year) "
      + "ORDER BY updated_at DESC OFFSET :offset LIMIT :limit")
  List<UserPortfolioItemPerfModel> fetchItemAllAttemptsPerformance(
      @BindBean UserPortfolioItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);
  
  @Mapper(UserPortfolioItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, updated_at, status, path_id, path_type, content_source FROM collection_performance "
      + "WHERE actor_id = :userId AND collection_id = :itemId AND path_id > 0 and path_type IS NOT NULL "
      + "AND date_in_time_zone <= :dateUntil "
      + "AND (:contentSource IS NULL OR content_source = :contentSource) "
      + "AND (:month IS NULL OR extract(month from date_in_time_zone) = :month) "
      + "AND (:year IS NULL OR extract(year from date_in_time_zone) = :year) "
      + "ORDER BY updated_at DESC OFFSET :offset LIMIT :limit")
  List<UserPortfolioItemPerfModel> fetchSuggestedItemAllAttemptsPerformance(
      @BindBean UserPortfolioItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);

}
