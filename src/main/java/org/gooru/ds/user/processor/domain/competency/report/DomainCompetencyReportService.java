
package org.gooru.ds.user.processor.domain.competency.report;

import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClassDao;
import org.gooru.ds.user.processor.domain.report.dbhelpers.GradeHighLowFetcherService;
import org.skife.jdbi.v2.DBI;

/**
 * @author szgooru Created On 31-Jan-2019
 */
public class DomainCompetencyReportService {
  
  private final CoreClassDao coreClassDao;
  private final GradeHighLowFetcherService gradeCompetencyService;
  
  public DomainCompetencyReportService(DBI defaultDbi, DBI coreDbi) {
    this.coreClassDao = coreDbi.onDemand(CoreClassDao.class);
    this.gradeCompetencyService = new GradeHighLowFetcherService(defaultDbi);
  }
  
  
}
