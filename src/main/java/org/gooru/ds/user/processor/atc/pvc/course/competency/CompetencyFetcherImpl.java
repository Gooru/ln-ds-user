package org.gooru.ds.user.processor.atc.pvc.course.competency;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish.
 */
class CompetencyFetcherImpl implements CompetencyFetcher {

  private final DBI dbi;
  private CompetencyFetcherDao dao;
  private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyFetcher.class);

  CompetencyFetcherImpl(DBI dbi) {
    this.dbi = dbi;
  }

  @Override
  public List<String> fetchCompetenciesForCourse(UUID courseId) {
    if (courseId != null) {
      List<String> competencies = getCompetencyFetcherDao().findCompetenciesForCourse(courseId);
      return (competencies != null && !competencies.isEmpty()) ? competencies
          : Collections.emptyList();
    }
    return Collections.emptyList();
  }

  private CompetencyFetcherDao getCompetencyFetcherDao() {
    if (dao == null) {
      dao = dbi.onDemand(CompetencyFetcherDao.class);
    }
    return dao;
  }

}
