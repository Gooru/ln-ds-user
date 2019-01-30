
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

    Map<String, GradeCompetencyBound> lowBounds = fetchLowLineForGrade(currentGradeId, subjectCode);
    Map<String, GradeCompetencyBound> highBounds = fetchHighLineForGrade(currentGradeId);

    for (String domainCode : highBounds.keySet()) {

      GradeCompetencyBound bound = new GradeCompetencyBound();
      bound.setDomainCode(domainCode);

      GradeCompetencyBound hb = highBounds.get(domainCode);
      bound.setHighlineCode(hb.getHighlineCode());

      GradeCompetencyBound lb = lowBounds.get(domainCode);
      if (lb == null) {
        String lowline = fetchFirstCompetencyInDomain(domainCode, subjectCode);
        bound.setLowlineCode(lowline);
      } else {
        bound.setLowlineCode(lb.getLowlineCode());
      }

      LOGGER.debug("competency bound for domain '{}' : {}", domainCode, bound.toString());
      compBoundMap.put(domainCode, bound);
    }

    return compBoundMap;
  }

  /*
   * We need consolidated list of domains and its high line and low lines. Calculation of high lines
   * is based on the high grade and low lines is based on the low grades.
   */
  public Map<String, GradeCompetencyBound> fetchHighLowLinesByGrade(Integer highGradeId,
      Integer lowGradeId, String subjectCode) {
    Map<String, GradeCompetencyBound> compBoundMap = new HashMap<>();

    Map<String, GradeCompetencyBound> lowBounds = fetchLowLineForGrade(lowGradeId, subjectCode);
    Map<String, GradeCompetencyBound> highBounds = fetchHighLineForGrade(highGradeId);

    for (String domainCode : highBounds.keySet()) {

      GradeCompetencyBound bound = new GradeCompetencyBound();
      bound.setDomainCode(domainCode);

      GradeCompetencyBound hb = highBounds.get(domainCode);
      bound.setHighlineCode(hb.getHighlineCode());

      GradeCompetencyBound lb = lowBounds.get(domainCode);
      if (lb == null) {
        String lowline = fetchFirstCompetencyInDomain(domainCode, subjectCode);
        bound.setLowlineCode(lowline);
      } else {
        bound.setLowlineCode(lb.getLowlineCode());
      }

      compBoundMap.put(domainCode, bound);
    }

    return compBoundMap;
  }

  /*
   * Fetch low lines by grade. If the low line for the grade is null then fetch the first competency
   * in the sequence of that domain and treat it as low line
   */
  private Map<String, GradeCompetencyBound> fetchLowLineForGrade(Integer gradeId,
      String subjectCode) {
    List<GradeCompetencyBound> lowlines = this.dao.fetchHighLowLinesByGrade(gradeId);

    Map<String, GradeCompetencyBound> compBoundMap = new HashMap<>();
    lowlines.forEach(line -> {
      String domainCode = line.getDomainCode();
      String lowline = line.getLowlineCode();

      if (lowline == null || lowline.isEmpty()) {
        lowline = fetchFirstCompetencyInDomain(domainCode, subjectCode);
        line.setLowlineCode(lowline);
      }

      compBoundMap.put(domainCode, line);
    });

    return compBoundMap;
  }

  /*
   * Its assumed that we will always have highline for each grade, so just return the highlines from
   * grade competency bound
   */
  private Map<String, GradeCompetencyBound> fetchHighLineForGrade(Integer gradeId) {
    List<GradeCompetencyBound> highlines = this.dao.fetchHighLowLinesByGrade(gradeId);
    Map<String, GradeCompetencyBound> compBoundMap = new HashMap<>();
    highlines.forEach(line -> {
      compBoundMap.put(line.getDomainCode(), line);
    });
    return compBoundMap;
  }

  /*
   * Fetch the first competency in the sequence of the domain
   */
  private String fetchFirstCompetencyInDomain(String domainCode, String subjectCode) {
    return this.dao.fetchFirstCompetencyByDomainAndSubject(domainCode, subjectCode);
  }
}
