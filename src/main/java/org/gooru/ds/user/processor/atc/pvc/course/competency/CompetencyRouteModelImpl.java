package org.gooru.ds.user.processor.atc.pvc.course.competency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.processor.atc.pvc.competency.CompetencyModel;
import org.gooru.ds.user.processor.atc.pvc.competency.DomainCode;
import org.gooru.ds.user.processor.atc.pvc.competency.DomainModel;
import org.gooru.ds.user.processor.atc.pvc.competency.SubjectCode;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish.
 */
class CompetencyRouteModelImpl implements CompetencyRouteModel {
  private final SubjectCode subjectCode;
  private final List<DomainModel> domains;
  private final Map<DomainCode, List<CompetencyModel>> domainCodeCompetencyModelMap;
  private JsonObject jsonRepresentation = null;


  CompetencyRouteModelImpl(SubjectCode subjectCode, List<DomainModel> domains,
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

  @Override
  public JsonObject toJson() {
    if (jsonRepresentation == null) {
      createJsonRepresentation();
    }
    return jsonRepresentation;
  }

  private void createJsonRepresentation() {
    jsonRepresentation = new CompetencyRouteModelRenderer().asJson(this);
  }


}
