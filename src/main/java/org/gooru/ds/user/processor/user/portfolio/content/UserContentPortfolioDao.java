package org.gooru.ds.user.processor.user.portfolio.content;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

interface UserContentPortfolioDao {
  
  @Mapper(UserContentPortfolioModelMapper.class)
  @SqlQuery("SELECT timespent, score, reaction, views, is_graded, course_id, class_id, unit_id, lesson_id, collection_id, collection_type, session_id, "
      + "content_source, updated_at, status, path_id, path_type FROM collection_performance WHERE actor_id = :user AND status = 'true' ORDER BY updated_at DESC ")
      //+ "offset ? limit ?")
  List<UserContentPortfolioModel> fetchContentActivities(@Bind("user") String user);
  
  @Mapper(UserContentPortfolioModelMapper.class)
  @SqlQuery("SELECT timespent, score, reaction, views, is_graded, course_id, class_id, unit_id, lesson_id, collection_id, collection_type, session_id, "
      + "content_source, updated_at, status, path_id, path_type FROM collection_performance WHERE actor_id = :user AND status = 'true' AND "
      + "collection_type = :activityType ORDER BY updated_at DESC ")
      //+ "offset ? limit ?")
  List<UserContentPortfolioModel> fetchContentActivitiesbyActivityType(@Bind("user") String user, @Bind("activityType") String activityType);

}
