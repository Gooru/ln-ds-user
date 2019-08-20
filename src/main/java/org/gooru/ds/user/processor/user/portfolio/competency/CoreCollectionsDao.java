package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
@LogSqlFactory
interface CoreCollectionsDao {

  @Mapper(CoreCollectionsModelMapper.class)
  @SqlQuery("SELECT id, title, format, subformat, thumbnail, learning_objective, taxonomy FROM collection WHERE id = ANY(:contentIds::uuid[])")
  List<CoreCollectionsModel> fetchCollectionMeta(@Bind("contentIds") String contentIds);
  
  @Mapper(CoreCollectionItemsModelMapper.class)
  @SqlQuery("select sum(question) as question_count, sum(resource) as resource_count, collection_id from (select case when content_format = 'question' then 1 else 0 end as question, case when content_format = 'resource' then 1 else 0 end as resource, collection_id from content where collection_id = ANY(:collectionIds::uuid[])) a group by collection_id")
  List<CoreCollectionItemCountsModel> fetchCollectionItemCounts(@Bind("collectionIds") String collectionIds);
  
  @Mapper(CoreOATaskCountModelMapper.class)
  @SqlQuery("select count(oat.id) as task_count, c.id from collection c inner join oa_tasks oat on c.id = oat.oa_id WHERE c.id = ANY(:collectionIds::uuid[]) group by c.id")
  List<CoreOATaskCountModel> fetchOATaskCounts(@Bind("collectionIds") String collectionIds);
  
}
  
