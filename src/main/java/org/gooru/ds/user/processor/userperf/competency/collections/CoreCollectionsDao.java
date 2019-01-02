package org.gooru.ds.user.processor.userperf.competency.collections;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author gooru on 16-May-2018
 */
public interface CoreCollectionsDao {

  @Mapper(CoreCollectionsModelMapper.class)
  @SqlQuery("SELECT id, title FROM collection WHERE id = ANY(:collectionIds::uuid[])")
  List<CoreCollectionsModel> fetchCollectionTitles(@Bind("collectionIds") String collectionIds);
}
