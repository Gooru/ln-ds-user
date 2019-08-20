package org.gooru.ds.user.processor.content.portfolio.item;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
interface UserPortfolioItemPerfDao {

  @Mapper(UserPortfolioItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, created_at, updated_at, status, path_id, path_type, content_source FROM collection_performance "
      + "WHERE actor_id = :userId AND collection_id = :itemId AND date_in_time_zone <= :dateUntil "
      + "AND (:contentSource IS NULL OR content_source = :contentSource) "
      + "ORDER BY updated_at DESC OFFSET :offset LIMIT :limit")
  List<UserPortfolioItemPerfModel> fetchItemAllAttemptsPerformance(
      @BindBean UserPortfolioItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);
  
  @Mapper(UserPortfolioItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, created_at, updated_at, status, path_id, path_type, content_source FROM collection_performance "
      + "WHERE actor_id = :userId AND collection_id = :itemId "
      + "AND date_in_time_zone BETWEEN :startDate AND :endDate "
      + "AND (:contentSource IS NULL OR content_source = :contentSource) "
      + "ORDER BY updated_at DESC OFFSET :offset LIMIT :limit")
  List<UserPortfolioItemPerfModel> fetchItemAllAttemptsPerformanceInDateRange(
      @BindBean UserPortfolioItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);

}
