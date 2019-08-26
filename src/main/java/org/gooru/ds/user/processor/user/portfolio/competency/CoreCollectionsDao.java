package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
interface CoreCollectionsDao {

  @Mapper(CoreCollectionsModelMapper.class)
  @SqlQuery("SELECT id, title, format, subformat, thumbnail, learning_objective, taxonomy FROM collection WHERE id = ANY(:contentIds::uuid[])")
  List<CoreCollectionsModel> fetchCollectionMeta(@Bind("contentIds") PGArray<String> pgArray);
  
  @Mapper(CoreCollectionItemsModelMapper.class)
  @SqlQuery("select sum(question) as question_count, sum(resource) as resource_count, collection_id from (select case when content_format = 'question' then 1 else 0 end as question, case when content_format = 'resource' then 1 else 0 end as resource, collection_id from content where collection_id = ANY(:collectionIds::uuid[])) a group by collection_id")
  List<CoreCollectionItemCountsModel> fetchCollectionItemCounts(@Bind("collectionIds") PGArray<String> pgArray);
  
  @Mapper(CoreOATaskCountModelMapper.class)
  @SqlQuery("select count(id) as task_count, oa_id as id from oa_tasks WHERE oa_id = ANY(:collectionIds::uuid[]) group by oa_id")
  List<CoreOATaskCountModel> fetchOATaskCounts(@Bind("collectionIds") PGArray<String> pgArray);
  
}
  
