
package org.gooru.ds.user.processor.domain.report;

import java.util.List;
import java.util.Map;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.domain.report.DomainReportCommand.DomainReportCommandBean;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClass;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClassDao;
import org.gooru.ds.user.processor.domain.report.dbhelpers.GradeCompetencyBound;
import org.gooru.ds.user.processor.domain.report.dbhelpers.GradeHighLowFetcherService;
import org.skife.jdbi.v2.DBI;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 14-Jan-2019
 */
public class DomainReportService {

  private final CoreClassDao coreClassDao;
  private final GradeHighLowFetcherService gradeCompetencyService;
  private final DomainCompetencyCompletionService domainCompCompletionService;

  public DomainReportService(DBI defaultDbi, DBI coreDbi) {
    this.coreClassDao = coreDbi.onDemand(CoreClassDao.class);
    this.gradeCompetencyService = new GradeHighLowFetcherService(defaultDbi);
    this.domainCompCompletionService = new DomainCompetencyCompletionService(defaultDbi);
  }

  public DomainCompetencyCompletionModelResponse fetchDomainReport(DomainReportCommand command) {
    
    DomainReportCommandBean bean = command.asBean();
    
    // Fetch class, high low bounds
    CoreClass cls = this.coreClassDao.fetchClassGrades(bean.getClassId());

    String subjectCode = fetchSubjectFromClass(cls);

    // Based on the availability of these three grade values we will calculate the low and high
    // bounds.
    // If low and high both grades are not assigned to course then use the current grade of the
    // class
    // From low, high and current find the lower and higher grades as use them as floor and ceiling
    Integer currentGrade = cls.getGradeCurrent();
    Integer lowBound = cls.getGradeLowerBound();
    Integer highBound = cls.getGradeUpperBound();

    // Compute high and low grades
    if (lowBound == null && highBound == null) {

    }

    // fetch high and low grade competencies by domain
    // If there is no high and low bounds setup at class we are fetching the high-low competencies
    // based on the current grade
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

    // fetch class members
    List<String> classMembers = this.coreClassDao.fetchClassMembers(bean.getClassId());
    
    this.domainCompCompletionService.computeDomainCompetencyCompletion(classMembers, subjectCode,
        bean.getToDate(), domainCompetencyCompletionMap);
    
    return null;
  }

  private String fetchSubjectFromClass(CoreClass cls) {
    JsonObject classPreference = cls.getPreference();
    String subject = classPreference.getString("subject");
    if (subject == null) {
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "no subject associated with class");
    }

    // TODO: validate subject against db?
    return subject;
  }
}
