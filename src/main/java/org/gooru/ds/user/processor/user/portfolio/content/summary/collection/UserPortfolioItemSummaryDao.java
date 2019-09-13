package org.gooru.ds.user.processor.user.portfolio.content.summary.collection;

import java.sql.Timestamp;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/*
 * @author renuka
 */

interface UserPortfolioItemSummaryDao {
  
  @SqlQuery("SELECT " + 
  " SUM(agg.max_score) AS max_score FROM (SELECT DISTINCT ON (resource_id) collection_id, " + 
  " FIRST_VALUE(updated_at) OVER (PARTITION BY resource_id ORDER BY updated_at desc) AS " + 
  " updated_at, FIRST_VALUE(max_score) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
  " desc) AS max_score FROM daily_class_activity WHERE session_id =:sessionId AND actor_id =:userId AND " + 
  " event_name = 'collection.resource.play' AND resource_type = 'question') AS agg GROUP BY " + 
  " agg.collection_id")
  Double fetchCACollMaxScore(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  @SqlQuery("SELECT updated_at FROM " + 
  " daily_class_activity WHERE session_id =:sessionId AND actor_id =:userId AND event_name = 'collection.play' " + 
  " ORDER BY updated_at DESC LIMIT 1")
  Timestamp fetchCACollLastAccessed(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  @Mapper(UserPortfolioItemCollTimespentModelMapper.class)
  @SqlQuery("SELECT SUM(CASE WHEN (agg.event_name = 'collection.resource.play' and agg.collection_type = 'collection') "
      + "THEN agg.time_spent WHEN (agg.event_name = 'collection.play' and agg.collection_type = 'collection- external') "
      + "THEN agg.time_spent ELSE 0 END) AS timespent, SUM(CASE WHEN (agg.event_name = 'collection.play') "
      + "THEN agg.views ELSE 0 END) AS attempts, collection_type FROM (SELECT collection_id,collection_type,time_spent,views, "
      + "event_name, CASE WHEN (FIRST_VALUE(event_type) OVER (PARTITION BY collection_id ORDER BY updated_at desc) = 'stop') "
      + "THEN 'completed' ELSE 'in-progress' END AS completionStatus FROM daily_class_activity "
      + "WHERE event_name in ('collection.play', 'collection.resource.play') and session_id =:sessionId AND actor_id =:userId) AS agg "
      + "GROUP BY agg.collection_id,agg.completionStatus,agg.collection_type")
  UserPortfolioItemCollTimespentModel fetchCACollTimespent(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @SqlQuery("SELECT SUM(agg.score) " + 
  " AS score FROM (SELECT DISTINCT ON (resource_id) collection_id, FIRST_VALUE(score) OVER " + 
  " (PARTITION BY resource_id ORDER BY updated_at desc) AS score FROM daily_class_activity " + 
  " WHERE session_id =:sessionId AND actor_id =:userId AND event_name = 'collection.resource.play' AND " + 
  " resource_type = 'question' AND resource_attempt_status <> 'skipped' ) AS agg GROUP BY " + 
  " agg.collection_id")
  Double fetchCACollScore(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @SqlQuery("SELECT " + 
  " ROUND(AVG(agg.reaction)) AS reaction FROM (SELECT DISTINCT ON (resource_id) " + 
  " collection_id, FIRST_VALUE(reaction) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
  " desc) AS reaction FROM daily_class_activity WHERE session_id =:sessionId AND actor_id =:userId AND " + 
  " event_name = 'reaction.create' AND reaction <> 0) AS agg GROUP BY agg.collection_id")
  Integer fetchCACollReaction(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @Mapper(UserPortfolioItemCollResAggModelMapper.class)
  @SqlQuery("SELECT " + 
  " resource_id ,resource_type, question_type, SUM(views) AS attempts, " + 
  " SUM(time_spent) AS timespent, 0 as reaction, 0 as score, '[]' AS answer_object FROM " + 
  " daily_class_activity WHERE session_id =:sessionId AND actor_id =:userId AND event_name = " + 
  " 'collection.resource.play' GROUP BY collection_id, resource_id,resource_type,question_type")
  List<UserPortfolioItemCollResAggModel> fetchCACollResourceAgg(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @Mapper(UserPortfolioItemCollResScoreModelMapper.class)
  @SqlQuery(
  "SELECT DISTINCT " + 
  " ON (resource_id) FIRST_VALUE(score) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
  " desc) AS score,FIRST_VALUE(resource_attempt_status) OVER (PARTITION BY resource_id ORDER " + 
  " BY updated_at desc) AS attempt_status, FIRST_VALUE(answer_object) OVER (PARTITION BY " + 
  " resource_id ORDER BY updated_at desc) AS answer_object, FIRST_VALUE(max_score) OVER " + 
  " (PARTITION BY resource_id ORDER BY updated_at desc) AS max_score FROM daily_class_activity " + 
  " WHERE session_id = :sessionId AND actor_id = :userId AND resource_id = :resourceId AND event_name = " + 
  " 'collection.resource.play' AND resource_type = 'question' AND resource_attempt_status <> " + 
  " 'skipped'")
  UserPortfolioItemCollResScoreModel fetchCACollResourceAggScore(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);

  @SqlQuery("SELECT is_graded FROM " + 
  " daily_class_activity WHERE session_id = :sessionId AND actor_id = :userId and resource_id = :resourceId AND event_name = " + 
  " 'collection.resource.play' AND event_type = 'stop'")
  Boolean fetchCACollResourceGradeStatus(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);

  @SqlQuery("SELECT " + 
  " DISTINCT ON (resource_id) FIRST_VALUE(reaction) OVER (PARTITION BY resource_id ORDER BY " + 
  " updated_at desc) AS reaction FROM daily_class_activity WHERE session_id =:sessionId AND actor_id =:userId " + 
  " AND resource_id =:resourceId AND event_name = 'reaction.create' AND reaction <> 0")
  Integer fetchCACollResourceReaction(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);

  
//Coursemap quueries
  @SqlQuery("SELECT " + 
  " SUM(agg.max_score) AS max_score FROM (SELECT DISTINCT ON (resource_id) collection_id, " + 
  " FIRST_VALUE(updated_at) OVER (PARTITION BY resource_id ORDER BY updated_at desc) AS " + 
  " updated_at, FIRST_VALUE(max_score) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
  " desc) AS max_score FROM base_reports WHERE session_id =:sessionId AND actor_id =:userId AND " + 
  " event_name = 'collection.resource.play' AND resource_type = 'question') AS agg GROUP BY " + 
  " agg.collection_id")
  Double fetchCollMaxScore(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  @SqlQuery("SELECT updated_at FROM " + 
  " base_reports WHERE session_id =:sessionId AND actor_id =:userId AND event_name = 'collection.play' " + 
  " ORDER BY updated_at DESC LIMIT 1")
  Timestamp fetchCollLastAccessed(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  @Mapper(UserPortfolioItemCollTimespentModelMapper.class)
  @SqlQuery("SELECT SUM(CASE WHEN (agg.event_name = 'collection.resource.play' and agg.collection_type = 'collection') "
      + "THEN agg.time_spent WHEN (agg.event_name = 'collection.play' and agg.collection_type = 'collection- external') "
      + "THEN agg.time_spent ELSE 0 END) AS timespent, SUM(CASE WHEN (agg.event_name = 'collection.play') "
      + "THEN agg.views ELSE 0 END) AS attempts, collection_type FROM (SELECT collection_id,collection_type,time_spent,views, "
      + "event_name, CASE WHEN (FIRST_VALUE(event_type) OVER (PARTITION BY collection_id ORDER BY updated_at desc) = 'stop') "
      + "THEN 'completed' ELSE 'in-progress' END AS completionStatus FROM base_reports "
      + "WHERE event_name in ('collection.play', 'collection.resource.play') and session_id =:sessionId AND actor_id =:userId) AS agg "
      + "GROUP BY agg.collection_id,agg.completionStatus,agg.collection_type")
  UserPortfolioItemCollTimespentModel fetchCollTimespent(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @SqlQuery("SELECT SUM(agg.score) " + 
  " AS score FROM (SELECT DISTINCT ON (resource_id) collection_id, FIRST_VALUE(score) OVER " + 
  " (PARTITION BY resource_id ORDER BY updated_at desc) AS score FROM base_reports " + 
  " WHERE session_id =:sessionId AND actor_id =:userId AND event_name = 'collection.resource.play' AND " + 
  " resource_type = 'question' AND resource_attempt_status <> 'skipped' ) AS agg GROUP BY " + 
  " agg.collection_id")
  Double fetchCollScore(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @SqlQuery("SELECT " + 
  " ROUND(AVG(agg.reaction)) AS reaction FROM (SELECT DISTINCT ON (resource_id) " + 
  " collection_id, FIRST_VALUE(reaction) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
  " desc) AS reaction FROM base_reports WHERE session_id =:sessionId AND actor_id =:userId AND " + 
  " event_name = 'reaction.create' AND reaction <> 0) AS agg GROUP BY agg.collection_id")
  Integer fetchCollReaction(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @Mapper(UserPortfolioItemCollResAggModelMapper.class)
  @SqlQuery("SELECT " + 
  " resource_id ,resource_type, question_type, SUM(views) AS attempts, " + 
  " SUM(time_spent) AS timespent, 0 as reaction, 0 as score, '[]' AS answer_object FROM " + 
  " base_reports WHERE session_id =:sessionId AND actor_id =:userId AND event_name = " + 
  " 'collection.resource.play' GROUP BY collection_id, resource_id,resource_type,question_type")
  List<UserPortfolioItemCollResAggModel> fetchCollResourceAgg(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @Mapper(UserPortfolioItemCollResScoreModelMapper.class)
  @SqlQuery(
  "SELECT DISTINCT " + 
  " ON (resource_id) FIRST_VALUE(score) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
  " desc) AS score,FIRST_VALUE(resource_attempt_status) OVER (PARTITION BY resource_id ORDER " + 
  " BY updated_at desc) AS attempt_status, FIRST_VALUE(answer_object) OVER (PARTITION BY " + 
  " resource_id ORDER BY updated_at desc) AS answer_object, FIRST_VALUE(max_score) OVER " + 
  " (PARTITION BY resource_id ORDER BY updated_at desc) AS max_score FROM base_reports " + 
  " WHERE session_id = :sessionId AND actor_id = :userId AND resource_id = :resourceId AND event_name = " + 
  " 'collection.resource.play' AND resource_type = 'question' AND resource_attempt_status <> " + 
  " 'skipped'")
  UserPortfolioItemCollResScoreModel fetchCollResourceAggScore(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);

  @SqlQuery("SELECT is_graded FROM " + 
  " base_reports WHERE session_id = :sessionId AND actor_id = :userId and resource_id = :resourceId AND event_name = " + 
  " 'collection.resource.play' AND event_type = 'stop'")
  Boolean fetchCollResourceGradeStatus(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);

  @SqlQuery("SELECT " + 
  " DISTINCT ON (resource_id) FIRST_VALUE(reaction) OVER (PARTITION BY resource_id ORDER BY " + 
  " updated_at desc) AS reaction FROM base_reports WHERE session_id =:sessionId AND actor_id =:userId " + 
  " AND resource_id =:resourceId AND event_name = 'reaction.create' AND reaction <> 0")
  Integer fetchCollResourceReaction(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);


}
