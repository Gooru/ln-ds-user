package org.gooru.ds.user.processor.user.portfolio.content.perf.asmt.summary;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
@LogSqlFactory
interface UserPortfolioItemSummaryDao {

  @Mapper(UserPortfolioItemQuestionSummaryModelMapper.class)
  @SqlQuery("select resource_id, score, reaction, time_spent, resource_type, question_type, answer_object, updated_at, max_score "
      + "from base_reports where actor_id = :userId and collection_id = :itemId and session_id = :sessionId "
      + "and event_name = 'collection.resource.play' and event_type = 'stop'")
  List<UserPortfolioItemQuestionSummaryModel> fetchUserAsmtQuestionSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @Mapper(UserPortfolioItemQuestionSummaryModelMapper.class)
  @SqlQuery("select resource_id, score, reaction, time_spent, resource_type, question_type, answer_object, updated_at, max_score "
      + "from daily_class_activity where actor_id = :userId and collection_id = :itemId and session_id = :sessionId "
      + "and event_name = 'collection.resource.play' and event_type = 'stop'")
  List<UserPortfolioItemQuestionSummaryModel> fetchUserCAAsmtQuestionSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);

  @Mapper(UserPortfolioItemSummaryModelMapper.class)
  @SqlQuery("select score, collection_id, collection_type, reaction, time_spent, updated_at "
      + "from base_reports WHERE collection_id = :itemId AND session_id = :sessionId AND event_name = 'collection.play' AND event_type = 'stop'")
  List<UserPortfolioItemSummaryModel> fetchUserAsmtSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  @Mapper(UserPortfolioItemSummaryModelMapper.class)
  @SqlQuery("select score, collection_id, collection_type, reaction, time_spent, updated_at "
      + "from daily_class_activity WHERE collection_id = :itemId AND session_id = :sessionId AND event_name = 'collection.play' AND event_type = 'stop'")
  List<UserPortfolioItemSummaryModel> fetchUserCAAsmtSummary(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean);
  
  @SqlQuery("SELECT is_graded FROM base_reports WHERE collection_id = :itemId AND session_id = :sessionId "
      + "AND resource_id = :resourceId AND event_name = 'collection.resource.play' AND event_type = 'stop'")
  Boolean fetchItemIsGraded(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);

  @SqlQuery("SELECT is_graded FROM daily_class_activity WHERE collection_id = :itemId AND session_id = :sessionId "
      + "AND resource_id = :resourceId AND event_name = 'collection.resource.play' AND event_type = 'stop'")
  Boolean fetchCAItemIsGraded(
      @BindBean UserPortfolioItemSummaryCommand.UserPortfolioItemSummaryCommandBean userPortfolioItemSummaryCommandBean,
      @Bind("resourceId") String resourceId);


}
