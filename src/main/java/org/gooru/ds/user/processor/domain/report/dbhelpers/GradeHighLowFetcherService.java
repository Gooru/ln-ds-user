
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author szgooru Created On 18-Jan-2019
 */
public class GradeHighLowFetcherService {

  private final GradeHighLowFetcherDao dao;
  private final static Logger LOGGER = LoggerFactory.getLogger(GradeHighLowFetcherService.class);

  public GradeHighLowFetcherService(DBI dbi) {
    this.dao = dbi.onDemand(GradeHighLowFetcherDao.class);
  }

  /*
   * If there is no high and low grades, then high and low lines are based on the current grade only
   */

  public Map<String, GradeCompetencyBound> fetchHighLowLinesByGrade(Integer currentGradeId,
      String subjectCode) {
    Map<String, GradeCompetencyBound> compBoundMap = new HashMap<>();

    //Map<String, GradeCompetencyBound> lowBounds = fetchLowLineForGrade(currentGradeId, subjectCode);
    //Map<String, GradeCompetencyBound> highBounds = fetchHighLineForGrade(currentGradeId);
    
    List<GradeCompetencyBound> bounds = this.dao.fetchHighLowLinesByGrade(currentGradeId);
    for (GradeCompetencyBound bound : bounds) {
      String domainCode = bound.getDomainCode();
      String lowline = bound.getLowlineCode();
      String highline = bound.getHighlineCode();
      
      GradeCompetencyBound gcb = new GradeCompetencyBound();
      // We do not want to report the domain completion for which the high and low competencies
      // are same.
      if (lowline != null && lowline.equalsIgnoreCase(highline)) {
        continue;
      }
      
      // If lowline of the grade is null then fetch the first competency of the domain in sequence.
      if (lowline == null || lowline.isEmpty()) {
        lowline = fetchFirstCompetencyInDomain(domainCode, subjectCode);
      } 
      
      gcb.setDomainCode(domainCode);
      gcb.setHighlineCode(highline);
      gcb.setLowlineCode(lowline);
      LOGGER.debug("competency bound for domain '{}' : {}", domainCode, gcb.toString());
      compBoundMap.put(domainCode, gcb);
    };
    
    return compBoundMap;
  }

  /*
   * Fetch the first competency in the sequence of the domain
   */
  private String fetchFirstCompetencyInDomain(String domainCode, String subjectCode) {
    return this.dao.fetchFirstCompetencyByDomainAndSubject(domainCode, subjectCode);
  }
}
