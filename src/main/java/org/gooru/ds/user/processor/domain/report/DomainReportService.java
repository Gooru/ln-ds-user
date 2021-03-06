
package org.gooru.ds.user.processor.domain.report;

import java.util.List;
import java.util.Map;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.constants.HttpConstants.HttpStatus;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.domain.report.DomainReportCommand.DomainReportCommandBean;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClass;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClassDao;
import org.gooru.ds.user.processor.domain.report.dbhelpers.DomainCompetencyMatrixModel;
import org.gooru.ds.user.processor.domain.report.dbhelpers.GradeCompetencyBound;
import org.gooru.ds.user.processor.domain.report.dbhelpers.GradeHighLowFetcherService;
import org.gooru.ds.user.processor.domain.report.utils.DomainReportUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 14-Jan-2019
 */
public class DomainReportService {

  private final CoreClassDao coreClassDao;
  private final GradeHighLowFetcherService gradeCompetencyService;
  private final DomainCompletionService domainCompCompletionService;

  private final static Logger LOGGER = LoggerFactory.getLogger(DomainReportService.class);

  public DomainReportService(DBI defaultDbi, DBI coreDbi) {
    this.coreClassDao = coreDbi.onDemand(CoreClassDao.class);
    this.gradeCompetencyService = new GradeHighLowFetcherService(defaultDbi);
    this.domainCompCompletionService = new DomainCompletionService(defaultDbi);
  }

  public JsonObject fetchDomainReport(DomainReportCommand command) {

    DomainReportCommandBean bean = command.asBean();

    // Fetch class, high low bounds
    CoreClass cls = this.coreClassDao.fetchClassGrades(bean.getClassId());
    if (cls == null) {
      LOGGER.warn("class '{}' not found", bean.getClassId());
      throw new HttpResponseWrapperException(HttpStatus.NOT_FOUND, "class not found");
    }

    String subjectCode = DomainReportUtils.fetchSubjectFromClass(cls);

    // Update: 01-Feb-2019 - We are relying on the current grade of the class to fetch the
    // completions.
    Integer currentGrade = cls.getGradeCurrent();
    if (currentGrade == null) {
      LOGGER.warn("no grade is setup for class, aborting ..");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "no grade setup for class");
    }

    // fetch grade competency bounds by the current grade of the class
    Map<String, GradeCompetencyBound> competencyBound =
        this.gradeCompetencyService.fetchHighLowLinesByGrade(currentGrade, subjectCode);

    // Fetch domain competency matrix for the subject
    Map<String, Map<String, DomainCompetencyMatrixModel>> allDomainCompetencyMatrixMap =
        this.domainCompCompletionService.fetchAllDomainCompetencyMatrix(subjectCode);

    // Fetch domains based on the competency bound and populate domain details. Also initiate the
    // domain completion models with initial values
    Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionMap =
        this.domainCompCompletionService.fetchDomains(subjectCode, competencyBound.keySet());

    // For every domain in the competency bound and high-low competencies of the domain fetch the
    // path from low competency to high competency
    for (Map.Entry<String, GradeCompetencyBound> entry : competencyBound.entrySet()) {
      String domainCode = entry.getKey();
      GradeCompetencyBound gcb = entry.getValue();
      Map<String, DomainCompetencyMatrixModel> competencyModels =
          allDomainCompetencyMatrixMap.get(domainCode);
      int lowSeq = competencyModels.get(gcb.getLowlineCode()).getCompetencySeq();
      int highSeq = competencyModels.get(gcb.getHighlineCode()).getCompetencySeq();

      // Explicit check for the sequence because it is possible that some of the domain has same
      // competency as high and low. In this case we do not want to compute the average completion.
      // Since low and high are same, competencies gained is always 0 / null. UI will show non-zero
      // values...at ATC view
      if (lowSeq < highSeq) {
        for (Map.Entry<String, DomainCompetencyMatrixModel> models : competencyModels.entrySet()) {
          DomainCompetencyMatrixModel dcmModel = models.getValue();
          if (dcmModel.getCompetencySeq() >= lowSeq && dcmModel.getCompetencySeq() <= highSeq) {
            CompetencyCompletionModel ccm = new CompetencyCompletionModel();
            ccm.setAvgCompletion(0);
            ccm.setCompetencyCode(dcmModel.getCompetencyCode());
            ccm.setCompetencyName(dcmModel.getCompetencyName());
            ccm.setCompetencySeq(dcmModel.getCompetencySeq());
            ccm.setCompetencyDesc(dcmModel.getCompetencyStudentDesc());
            domainCompetencyCompletionMap.get(domainCode).getCompetencies()
                .put(dcmModel.getCompetencyCode(), ccm);
          }
        }
      }
    }

    // fetch class members
    List<String> classMembers = this.coreClassDao.fetchClassMembers(bean.getClassId());

    this.domainCompCompletionService.computeDomainCompetencyCompletion(classMembers, subjectCode,
        bean.getToDate(), domainCompetencyCompletionMap);

    return DomainCompletionModelResponseBuilder.buildReponse(bean.getAgent(),
        domainCompetencyCompletionMap, classMembers.size());
  }

}
