package org.gooru.ds.user.processor.atc.pvc.course.competency;

import java.util.List;
import java.util.UUID;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.skife.jdbi.v2.DBI;

/**
 * @author ashish.
 */
interface CompetencyFetcher {
  List<String> fetchCompetenciesForCourse(UUID courseId);

  static CompetencyFetcher build() {

    return new CompetencyFetcherImpl(DBICreator.getDbiForCoreDS());
    // return new CompetencyFetcherImpl(DBICreator.getDbiForDefaultDS());
  }

  static CompetencyFetcher build(DBI dbi) {
    return new CompetencyFetcherImpl(dbi);
  }
}
