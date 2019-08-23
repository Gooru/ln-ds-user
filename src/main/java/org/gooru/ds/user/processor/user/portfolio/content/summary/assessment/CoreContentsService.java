package org.gooru.ds.user.processor.user.portfolio.content.summary.assessment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;

/**
 * @author renuka
 */
public class CoreContentsService {

  private final CoreContentsDao dao;

  public CoreContentsService(DBI dbi) {
    this.dao = dbi.onDemand(CoreContentsDao.class);
  }

  public Map<String, CoreContentsModel> fetchContentTitles(List<String> contentIds) {
    Map<String, CoreContentsModel> contents = new HashMap<>();
    List<CoreContentsModel> contentModels =
        this.dao.fetchContentTitles(toPostgresArrayString(contentIds));
    contentModels.forEach(model -> {
      contents.put(model.getId(), model);
    });
    return contents;
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
