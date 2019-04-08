package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;


/**
 * @author mukul@gooru
 */
public class CollectionMetadataService {

  private final CollectionMetadataDao coreCollectionsDao;

  public CollectionMetadataService(DBI dbi) {
    this.coreCollectionsDao = dbi.onDemand(CollectionMetadataDao.class);
  }

  public Map<String, String> fetchCollectionTitles(List<String> collectionIds) {
    Map<String, String> collectionTitlesMap = new HashMap<>();
    List<CollectionMetadataModel> collectionTitles =
        coreCollectionsDao.fetchCollectionTitles(toPostgresArrayString(collectionIds));
    collectionTitles.forEach(collection -> {
      collectionTitlesMap.put(collection.getId(), collection.getTitle());
    });
    return collectionTitlesMap;
  }

  public static String toPostgresArrayString(List<String> input) {
    Iterator<String> it = input.iterator();
    if (!it.hasNext()) {
      return "{}";
    }

    StringBuilder sb = new StringBuilder();
    sb.append('{');
    for (;;) {
      String s = it.next();
      sb.append('"').append(s).append('"');
      if (!it.hasNext()) {
        return sb.append('}').toString();
      }
      sb.append(',');
    }
  }
}
