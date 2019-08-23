package org.gooru.ds.user.processor.user.portfolio.content.items;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
interface UserPortfolioUniqueItemPerfDao {

  @Mapper(UserPortfolioUniqueItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, created_at, status, path_id, path_type, content_source from (SELECT *, row_number() over (partition by collection_id order by updated_at desc) r FROM collection_performance "
      + "WHERE actor_id = :userId AND date_in_time_zone <= :dateUntil AND collection_type IN ('assessment', 'assessment-external') AND status = true "
      + "ORDER BY updated_at DESC) AS a WHERE r=1 OFFSET :offset LIMIT :limit")
  List<UserPortfolioUniqueItemPerfModel> fetchUniqueAsmtsPerformance(
      @BindBean UserPortfolioUniqueItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);
  
  @Mapper(UserPortfolioUniqueItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, created_at, status, path_id, path_type, content_source from (SELECT *, row_number() over (partition by collection_id order by updated_at desc) r FROM collection_performance "
      + "WHERE actor_id = :userId AND date_in_time_zone <= :dateUntil AND collection_type IN ('collection', 'collection-external') "
      + "ORDER BY updated_at DESC) AS a WHERE r=1 OFFSET :offset LIMIT :limit")
  List<UserPortfolioUniqueItemPerfModel> fetchUniqueCollsPerformance(
      @BindBean UserPortfolioUniqueItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);
  
  @Mapper(UserPortfolioUniqueItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, created_at, status, path_id, path_type, content_source from (SELECT *, row_number() over (partition by collection_id order by updated_at desc) r FROM collection_performance "
      + "WHERE actor_id = :userId AND date_in_time_zone <= :dateUntil AND collection_type = 'offline-activity' "
      + "ORDER BY updated_at DESC) AS a WHERE r=1 OFFSET :offset LIMIT :limit")
  List<UserPortfolioUniqueItemPerfModel> fetchUniqueOasPerformance(
      @BindBean UserPortfolioUniqueItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);
  
  @Mapper(UserPortfolioUniqueItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, created_at, status, path_id, path_type, content_source from (SELECT *, row_number() over (partition by collection_id order by updated_at desc) r FROM collection_performance "
      + "WHERE actor_id = :userId AND date_in_time_zone BETWEEN :startDate AND :endDate AND collection_type IN ('assessment', 'assessment-external') AND status = true "
      + "ORDER BY updated_at DESC) AS a WHERE r=1 OFFSET :offset LIMIT :limit")
  List<UserPortfolioUniqueItemPerfModel> fetchUniqueAsmtsPerformanceByDateRange(
      @BindBean UserPortfolioUniqueItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);
  
  @Mapper(UserPortfolioUniqueItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, created_at, status, path_id, path_type, content_source from (SELECT *, row_number() over (partition by collection_id order by updated_at desc) r FROM collection_performance "
      + "WHERE actor_id = :userId AND date_in_time_zone BETWEEN :startDate AND :endDate AND collection_type IN ('collection', 'collection-external') "
      + "ORDER BY updated_at DESC) AS a WHERE r=1 OFFSET :offset LIMIT :limit")
  List<UserPortfolioUniqueItemPerfModel> fetchUniqueCollsPerformanceByDateRange(
      @BindBean UserPortfolioUniqueItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);
  
  @Mapper(UserPortfolioUniqueItemPerfModelMapper.class)
  @SqlQuery("SELECT timespent, score, max_score, reaction, views, is_graded, actor_id, class_id, course_id, unit_id, lesson_id, collection_id, collection_type, session_id, created_at, status, path_id, path_type, content_source from (SELECT *, row_number() over (partition by collection_id order by updated_at desc) r FROM collection_performance "
      + "WHERE actor_id = :userId AND date_in_time_zone BETWEEN :startDate AND :endDate AND collection_type = 'offline-activity' "
      + "ORDER BY updated_at DESC) AS a WHERE r=1 OFFSET :offset LIMIT :limit")
  List<UserPortfolioUniqueItemPerfModel> fetchUniqueOasPerformanceByDateRange(
      @BindBean UserPortfolioUniqueItemPerfCommand.UserPortfolioUniqueItemPerfCommandBean userPerfAsmtSummaryCommandBean);
  
  @Mapper(UserPortfolioMasteryStatusModelMapper.class)
  @SqlQuery("select distinct on (gut_code, collection_id) cm.tx_comp_display_code, gut_code,collection_id,collection_type, first_value(status) OVER (PARTITION BY gut_code,collection_id ORDER BY updated_at desc) as status from domain_competency_matrix cm left join learner_profile_competency_evidence_ts lpces on  cm.tx_comp_code = lpces.gut_code WHERE user_id = :userId AND collection_id = ANY(:collectionIds)")
  List<UserPortfolioMasteryStatusModel> fetchMasteryStatus(
      @Bind("userId") String userId, @Bind("collectionIds") PGArray<String> pgArray);
  
}
