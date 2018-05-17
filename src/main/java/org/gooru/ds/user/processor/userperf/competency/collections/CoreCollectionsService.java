package org.gooru.ds.user.processor.userperf.competency.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DBI;

/**
 * @author gooru on 16-May-2018
 */
public class CoreCollectionsService {

	private final CoreCollectionsDao coreCollectionsDao;
	
	public CoreCollectionsService(DBI dbi) {
		this.coreCollectionsDao = dbi.onDemand(CoreCollectionsDao.class);
	}
	
	public Map<String, String> fetchCollectionTitles(List<String> collectionIds) {
		Map<String, String> collectionTitlesMap = new HashMap<>();
		List<CoreCollectionsModel> collectionTitles = coreCollectionsDao.fetchCollectionTitles(toPostgresArrayString(collectionIds));
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
