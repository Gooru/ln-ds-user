package org.gooru.ds.user.processor.grade.competency.compute;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.CompetencyModel;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.DomainCode;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.DomainModel;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.SubjectCode;

/**
 * @author ashish.
 */
class GradeCompetencyModelImpl implements GradeCompetencyModel {
  private final SubjectCode subjectCode;
  private final List<DomainModel> domains;
  private final Map<DomainCode, List<CompetencyModel>> domainCodeCompetencyModelMap;


  GradeCompetencyModelImpl(SubjectCode subjectCode, List<DomainModel> domains,
      Map<DomainCode, List<CompetencyModel>> domainCodeCompetencyModelMap) {
    this.subjectCode = subjectCode;
    this.domains = Collections.unmodifiableList(domains);
    this.domainCodeCompetencyModelMap = domainCodeCompetencyModelMap;
  }

  @Override
  public SubjectCode getSubject() {
    return subjectCode;
  }

  @Override
  public List<DomainModel> getDomainsOrdered() {
    return domains;
  }

  @Override
  public List<CompetencyModel> getPathForDomain(DomainCode domainCode) {
    final List<CompetencyModel> result = domainCodeCompetencyModelMap.get(domainCode);
    if (result != null) {
      return Collections.unmodifiableList(result);
    }
    return Collections.emptyList();
  }

}
