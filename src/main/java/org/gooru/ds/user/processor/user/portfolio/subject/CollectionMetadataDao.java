package org.gooru.ds.user.processor.user.portfolio.subject;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 */
public interface CollectionMetadataDao {

  @Mapper(CollectionMetadataModelMapper.class)
  @SqlQuery("SELECT id, title FROM collection WHERE id = ANY(:collectionIds::uuid[])")
  List<CollectionMetadataModel> fetchCollectionTitles(@Bind("collectionIds") String collectionIds);
}
