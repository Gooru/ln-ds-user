package org.gooru.ds.user.processor.content.portfolio.asmt.summary;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
interface UserPortfolioItemSummaryDao {

  @Mapper(UserPortfolioItemSummaryModelMapper.class)
  @SqlQuery("select score, collection_id, collection_type, reaction, timespent, updated_at "
      + "from collection_performance WHERE collection_id = :itemId AND session_id = :sessionId AND actor_id = :userId "
      + "AND status = true AND content_source = 'coursemap'")
  List<UserPortfolioItemSummaryModel> fetchUserAsmtSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  @Mapper(UserPortfolioItemSummaryModelMapper.class)
  @SqlQuery("select score, collection_id, collection_type, reaction, timespent, updated_at "
      + "from collection_performance WHERE collection_id = :itemId AND session_id = :sessionId AND actor_id = :userId "
      + "and class_id IS NULL AND status = true AND content_source = 'ILActivity'")
  List<UserPortfolioItemSummaryModel> fetchUserILAsmtSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  @Mapper(UserPortfolioItemSummaryModelMapper.class)
  @SqlQuery("select score, collection_id, collection_type, reaction, timespent, updated_at "
      + "from collection_performance WHERE collection_id = :itemId AND session_id = :sessionId AND actor_id = :userId "
      + "AND status = true AND content_source = 'dailyclassactivity'")
  List<UserPortfolioItemSummaryModel> fetchUserCAAsmtSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  //Ques/Res Summary
  @Mapper(UserPortfolioItemQuestionSummaryModelMapper.class)
  @SqlQuery("select distinct " + 
      " on (resource_id) FIRST_VALUE(score) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
      " desc) AS score,resource_id,FIRST_VALUE(reaction) OVER (PARTITION BY resource_id ORDER BY " + 
      " updated_at desc) AS reaction,FIRST_VALUE(time_spent) OVER (PARTITION BY resource_id ORDER " + 
      " BY updated_at desc) as time_spent,updated_at, session_id, max_score, " + 
      " collection_type,FIRST_VALUE(views) OVER (PARTITION BY resource_id ORDER BY updated_at asc) " + 
      " AS resourceViews, resource_type, question_type,FIRST_VALUE(answer_object) OVER (PARTITION " + 
      " BY resource_id ORDER BY updated_at desc) as answer_object "
      + "from base_reports where actor_id = :userId and collection_id = :itemId and session_id = :sessionId "
      + "and event_name = 'collection.resource.play' and event_type = 'stop' AND content_source = 'coursemap'")
  List<UserPortfolioItemQuestionSummaryModel> fetchUserAsmtQuestionSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @Mapper(UserPortfolioItemQuestionSummaryModelMapper.class)
  @SqlQuery("select distinct on " + 
      "  (resource_id) FIRST_VALUE(score) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
      "  desc) AS score, resource_id, FIRST_VALUE(time_spent) OVER (PARTITION BY resource_id " + 
      "  ORDER BY updated_at desc) as time_spent,FIRST_VALUE(reaction) OVER (PARTITION BY resource_id ORDER BY " + 
      "  updated_at desc) AS reaction, updated_at, max_score, resource_type, question_type, FIRST_VALUE(answer_object) OVER " + 
      "  (PARTITION BY resource_id ORDER BY updated_at desc) as answer_object from " + 
      "  daily_class_activity where actor_id = :userId and collection_id = :itemId and session_id = :sessionId "
      + "and event_name = 'collection.resource.play' and event_type = 'stop' AND content_source = 'dailyclassactivity'")
  List<UserPortfolioItemQuestionSummaryModel> fetchUserCAAsmtQuestionSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @Mapper(UserPortfolioItemQuestionSummaryModelMapper.class)
  @SqlQuery("select distinct on " + 
      "  (resource_id) FIRST_VALUE(score) OVER (PARTITION BY resource_id ORDER BY updated_at " + 
      "  desc) AS score, resource_id, FIRST_VALUE(time_spent) OVER (PARTITION BY resource_id " + 
      "  ORDER BY updated_at desc) as time_spent,FIRST_VALUE(reaction) OVER (PARTITION BY resource_id ORDER BY " + 
      "  updated_at desc) AS reaction, updated_at, max_score, resource_type, question_type, FIRST_VALUE(answer_object) OVER " + 
      "  (PARTITION BY resource_id ORDER BY updated_at desc) as answer_object from " + 
      "  base_reports where class_id IS NULL and actor_id = :userId and collection_id = :itemId and session_id = :sessionId "
      + "and event_name = 'collection.resource.play' and event_type = 'stop' AND content_source = 'ILActivity'")
  List<UserPortfolioItemQuestionSummaryModel> fetchUserILAsmtQuestionSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  
  @SqlQuery("SELECT FIRST_VALUE(reaction) OVER (PARTITION BY resource_id ORDER BY updated_at desc) AS " + 
      " reaction FROM daily_class_activity WHERE actor_id = :userId and resource_id = :resourceId and session_id = :sessionId " + 
      " AND collection_id= :itemId AND reaction > 0 AND event_name = 'reaction.create'")
  Integer fetchUserCAAsmtQuestionReactionSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);
  
  @SqlQuery("SELECT FIRST_VALUE(reaction) OVER " + 
      "  (PARTITION BY resource_id ORDER BY updated_at desc) AS reaction FROM base_reports WHERE actor_id = :userId and resource_id = :resourceId and session_id = :sessionId " + 
      " AND collection_id= :itemId AND reaction > 0 AND event_name = 'reaction.create'")
  Integer fetchUserAsmtQuestionReactionSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);

  @SqlQuery("SELECT is_graded FROM base_reports WHERE collection_id = :itemId AND session_id = :sessionId AND actor_id = :userId "
      + "AND resource_id = :resourceId AND event_name = 'collection.resource.play' AND event_type = 'stop'")
  Boolean fetchItemIsGraded(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);

  @SqlQuery("SELECT is_graded FROM daily_class_activity WHERE collection_id = :itemId AND session_id = :sessionId ANd actor_id = :userId "
      + "AND resource_id = :resourceId AND event_name = 'collection.resource.play' AND event_type = 'stop'")
  Boolean fetchCAItemIsGraded(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);


}
