package org.gooru.ds.user.processor.user.portfolio.content.summary.assessment;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author renuka
 */
public interface CoreContentsDao {

  @Mapper(CoreContentsModelMapper.class)
  @SqlQuery("SELECT id, title, content_format, content_subformat FROM content WHERE id = ANY(:contentIds::uuid[])")
  List<CoreContentsModel> fetchContentTitles(@Bind("contentIds") PGArray<String> pgArray);
}
