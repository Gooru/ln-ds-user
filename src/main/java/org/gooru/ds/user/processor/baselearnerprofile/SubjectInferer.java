package org.gooru.ds.user.processor.baselearnerprofile;

import java.util.UUID;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.skife.jdbi.v2.DBI;

/**
 * @author ashish.
 */
public interface SubjectInferer {

  String inferSubjectForCourse(UUID courseId);

  static SubjectInferer build() {
    return new SubjectInfererImpl(DBICreator.getDbiForCoreDS());
  }

  static SubjectInferer build(DBI dbi) {
    return new SubjectInfererImpl(dbi);
  }
}
