package org.gooru.ds.user.processor.grade.competency.compute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.CompetencyModel;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.CompetencyPath;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.CompetencyCollector;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.DomainCode;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.DomainModel;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.ProgressionLevel;
import org.gooru.ds.user.processor.grade.competency.compute.competencymodel.SubjectCode;
import org.gooru.ds.user.processor.grade.competency.compute.utils.CollectionUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author ashish.
 */
class GradeCompetencyModelBuilder {

  private final TaxonomyDao taxonomyDao;
  private SubjectCode subjectCode;
  private List<DomainModel> domains; // This list is ordered
  private Map<DomainCode, List<CompetencyModel>> domainCodeCompetencyModelMap;
  private Map<DomainCode, List<CompetencyModel>> domainCompetencyMatrix;
  private List<String> domainsAsString; // This is unordered
  private CompetencyCollector competencyCollector;

  GradeCompetencyModelBuilder(TaxonomyDao taxonomyDao) {
    this.taxonomyDao = taxonomyDao;
  }

  GradeCompetencyModelBuilder(DBI dbi) {
    this.taxonomyDao = dbi.onDemand(TaxonomyDao.class);
  }

  GradeCompetencyModel build(SubjectCode subjectCode, CompetencyCollector competencyCollector) {
    this.subjectCode = subjectCode;
    this.competencyCollector = competencyCollector;
    domainsAsString = competencyCollector.getDomains().stream().map(DomainCode::getCode)
        .collect(Collectors.toList());
    fetchOrderedDomainModels();
    fetchDomainCompetencyMatrix();
    enrichCompetencyWithDetails();
    return new GradeCompetencyModelImpl(subjectCode, domains, domainCodeCompetencyModelMap);
  }

  private void enrichCompetencyWithDetails() {
    domainCodeCompetencyModelMap = new HashMap<>();

    for (DomainModel domainModel : domains) {
      CompetencyPath competencyPath =
          competencyCollector.getPathForDomain(domainModel.getDomainCode());
      if (!competencyPath.isPathInProgressionOrder() || competencyPath.getPath().isEmpty()) {
        continue;
      }
      enrichCompetencyPathForSpecifiedDomainModel(domainModel, competencyPath.getPath());
    }
  }

  private void enrichCompetencyPathForSpecifiedDomainModel(DomainModel domainModel,
      List<ProgressionLevel> progressionPath) {
    DomainCode domainCode = domainModel.getDomainCode();
    List<CompetencyModel> competenciesInDomain = domainCompetencyMatrix.get(domainCode);
    List<CompetencyModel> competenciesOnPath = new ArrayList<>();

    int currentItemInProgression = 0;

    for (CompetencyModel competencyModel : competenciesInDomain) {
      ProgressionLevel currentLevel = progressionPath.get(currentItemInProgression);
      if (currentLevel.getProgressionLevel() < competencyModel.getSequence()) {
        while (currentLevel.getProgressionLevel() < competencyModel.getSequence()) {
          currentItemInProgression++;
          if (currentItemInProgression >= progressionPath.size()) {
            break;
          }
        }
      }

      if (currentLevel.getProgressionLevel() < competencyModel.getSequence()) {
        competenciesOnPath.add(competencyModel);
        break;
      }

      if (currentLevel.getProgressionLevel() == competencyModel.getSequence()) {
        competenciesOnPath.add(competencyModel);
        currentItemInProgression++;
        if (currentItemInProgression >= progressionPath.size()) {
          break;
        }
      }
    }
    domainCodeCompetencyModelMap.put(domainCode, competenciesOnPath);
  }

  private void fetchDomainCompetencyMatrix() {
    List<CompetencyModel> competencyModels =
        taxonomyDao.fetchDomainCompetencyMatrixForSpecifiedDomains(subjectCode.getCode(),
            CollectionUtils.convertToSqlArrayOfString(domainsAsString));
    DomainCode previousDomain = null;
    List<CompetencyModel> currentDomainsCompetencyModels = Collections.emptyList();
    domainCompetencyMatrix = new HashMap<>();

    for (CompetencyModel competencyModel : competencyModels) {
      DomainCode currentDomain = competencyModel.getDomainCode();
      if (previousDomain == null) {
        currentDomainsCompetencyModels = new ArrayList<>();
      } else if (!previousDomain.equals(currentDomain)) {
        domainCompetencyMatrix.put(previousDomain, currentDomainsCompetencyModels);
        currentDomainsCompetencyModels = new ArrayList<>();
      }
      previousDomain = currentDomain;
      currentDomainsCompetencyModels.add(competencyModel);
    }
    domainCompetencyMatrix.put(previousDomain, currentDomainsCompetencyModels);
  }

  private void fetchOrderedDomainModels() {
    domains = taxonomyDao.fetchDomainModelsForSpecifiedDomainsInOrder(subjectCode.getCode(),
        CollectionUtils.convertToSqlArrayOfString(domainsAsString));
  }
}
