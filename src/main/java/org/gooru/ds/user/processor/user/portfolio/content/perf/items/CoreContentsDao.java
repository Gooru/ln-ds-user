package org.gooru.ds.user.processor.user.portfolio.content.perf.items;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru on 07-Jun-2018
 */
public interface CoreContentsDao {

  @Mapper(CoreContentsModelMapper.class)
  @SqlQuery("SELECT id, title, content_subformat FROM content WHERE id = ANY(:contentIds::uuid[])")
  List<CoreContentsModel> fetchContentTitles(@Bind("contentIds") String contentIds);
}
