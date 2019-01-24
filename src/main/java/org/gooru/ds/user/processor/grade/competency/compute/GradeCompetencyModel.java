package org.gooru.ds.user.processor.grade.competency.compute;

import java.util.List;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.CompetencyModel;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.DomainCode;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.DomainModel;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.SubjectCode;

/**
 * @author ashish.
 */
public interface GradeCompetencyModel {

  SubjectCode getSubject();

  List<DomainModel> getDomainsOrdered();

  List<CompetencyModel> getPathForDomain(DomainCode domainCode);

}
