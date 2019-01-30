
package org.gooru.ds.user.processor.domain.report;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.gooru.ds.user.constants.StatusConstants;
import org.gooru.ds.user.processor.atc.pvc.course.competency.utils.CollectionUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author szgooru Created On 29-Jan-2019
 */
public class DomainCompetencyCompletionService {

  private final DomainCompetencyMatrixFetcherDao dao;

  public DomainCompetencyCompletionService(DBI defaultDbi) {
    this.dao = defaultDbi.onDemand(DomainCompetencyMatrixFetcherDao.class);
  }

  public void computeDomainCompetencyCompletion(List<String> classMembers, String subject,
      Timestamp toDate,
      Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionModels) {

    classMembers.forEach(member -> {
      List<DomainCompetencyMatrixModel> userCompetencyModels =
          this.dao.fetchUserDomainCompetencyMatrix(member, subject, toDate);

      if (!userCompetencyModels.isEmpty()) {

        Map<String, Map<String, DomainCompetencyMatrixModel>> completedCompMatrixMap =
            generateCompletedCompetencyMatrixMap(userCompetencyModels);

        Map<String, Map<String, DomainCompetencyMatrixModel>> inferredCompletedCompMatrixMap =
            computeInferredCompletedCompetencyMatrixMap(userCompetencyModels,
                completedCompMatrixMap);

        for (Map.Entry<String, DomainCompetencyCompletionModel> entry : domainCompetencyCompletionModels
            .entrySet()) {
          String domainCode = entry.getKey();
          if (inferredCompletedCompMatrixMap.containsKey(domainCode)) {
            DomainCompetencyCompletionModel domainCompCompletionModel = entry.getValue();
            for (Map.Entry<String, CompetencyCompletionModel> completionModels : domainCompCompletionModel
                .getCompetencies().entrySet()) {
              CompetencyCompletionModel compCompletionModel = completionModels.getValue();
              Integer avgCompletion = compCompletionModel.getAvgCompletion();

              DomainCompetencyMatrixModel matrixModel = inferredCompletedCompMatrixMap
                  .get(domainCode).get(compCompletionModel.getCompetencyCode());
              if (matrixModel.getStatus() >= StatusConstants.INFERRED) {
                compCompletionModel
                    .setAvgCompletion((avgCompletion == null ? 1 : avgCompletion + 1));
              }
            }
          } else {
            entry.getValue().setAvgCompletion(0);
          }
        }
      }
    });
  }

  private Map<String, Map<String, DomainCompetencyMatrixModel>> generateCompletedCompetencyMatrixMap(
      List<DomainCompetencyMatrixModel> userCompetencyModels) {
    Map<String, Map<String, DomainCompetencyMatrixModel>> completedCompMatrixMap = new HashMap<>();

    List<DomainCompetencyMatrixModel> completed = userCompetencyModels.stream()
        .filter(model -> model.getStatus() >= StatusConstants.COMPLETED)
        .collect(Collectors.toList());

    completed.forEach(model -> {
      String domain = model.getDomainCode();
      String compCode = model.getCompetencyCode();

      if (completedCompMatrixMap.containsKey(domain)) {
        Map<String, DomainCompetencyMatrixModel> competencies = completedCompMatrixMap.get(domain);
        competencies.put(compCode, model);
        completedCompMatrixMap.put(domain, competencies);
      } else {
        Map<String, DomainCompetencyMatrixModel> competencies = new HashMap<>();
        competencies.put(compCode, model);
        completedCompMatrixMap.put(domain, competencies);
      }
    });

    return completedCompMatrixMap;
  }

  private Map<String, Map<String, DomainCompetencyMatrixModel>> computeInferredCompletedCompetencyMatrixMap(
      List<DomainCompetencyMatrixModel> userCompetencyModels,
      Map<String, Map<String, DomainCompetencyMatrixModel>> completedCompMatrixMap) {
    Map<String, Map<String, DomainCompetencyMatrixModel>> inferredCompletedCompMatrixMap =
        new HashMap<>();
    userCompetencyModels.forEach(model -> {
      String domainCode = model.getDomainCode();
      int sequence = model.getCompetencySeq();
      int status = model.getStatus();

      if (completedCompMatrixMap.containsKey(domainCode)) {
        // fetch all completed competencies of the domain
        Map<String, DomainCompetencyMatrixModel> competencies =
            completedCompMatrixMap.get(domainCode);
        for (Map.Entry<String, DomainCompetencyMatrixModel> entry : competencies.entrySet()) {
          DomainCompetencyMatrixModel compModel = entry.getValue();
          int compSeq = compModel.getCompetencySeq();

          if (sequence < compSeq && status < StatusConstants.ASSERTED) {
            model.setStatus(StatusConstants.INFERRED);
          }
        }

        if (inferredCompletedCompMatrixMap.containsKey(domainCode)) {
          Map<String, DomainCompetencyMatrixModel> inferredCompletedCompetencies =
              inferredCompletedCompMatrixMap.get(domainCode);
          inferredCompletedCompetencies.put(model.getCompetencyCode(), model);
          inferredCompletedCompMatrixMap.put(domainCode, inferredCompletedCompetencies);
        } else {
          Map<String, DomainCompetencyMatrixModel> inferredCompletedCompetencies = new HashMap<>();
          inferredCompletedCompetencies.put(model.getCompetencyCode(), model);
          inferredCompletedCompMatrixMap.put(domainCode, inferredCompletedCompetencies);
        }
      }
    });

    return inferredCompletedCompMatrixMap;
  }

  public Map<String, Map<String, DomainCompetencyMatrixModel>> fetchAllDomainCompetencyMatrix(
      String subjectCode) {
    List<DomainCompetencyMatrixModel> allDomainCompetencies =
        this.dao.fetchAllDomainCompetencies(subjectCode);

    Map<String, Map<String, DomainCompetencyMatrixModel>> allDomainCompetencyMap = new HashMap<>();
    allDomainCompetencies.forEach(competencyModel -> {
      String domainCode = competencyModel.getDomainCode();
      if (allDomainCompetencyMap.containsKey(domainCode)) {
        Map<String, DomainCompetencyMatrixModel> competencies =
            allDomainCompetencyMap.get(domainCode);
        competencies.put(competencyModel.getCompetencyCode(), competencyModel);
        allDomainCompetencyMap.put(domainCode, competencies);
      } else {
        Map<String, DomainCompetencyMatrixModel> competencies = new HashMap<>();
        competencies.put(competencyModel.getCompetencyCode(), competencyModel);
        allDomainCompetencyMap.put(domainCode, competencies);
      }
    });
    return allDomainCompetencyMap;
  }

  public Map<String, DomainCompetencyCompletionModel> fetchDomains(String subjectCode,
      Set<String> domainCodes) {
    List<DomainCompetencyCompletionModel> allDomains =
        this.dao.fetchDomains(subjectCode, CollectionUtils.convertToSqlArrayOfString(domainCodes));
    Map<String, DomainCompetencyCompletionModel> domainCompCompletionModelMap = new HashMap<>();
    allDomains.forEach(domain -> {
      domainCompCompletionModelMap.put(domain.getDomain().getDomainCode(), domain);
    });

    return domainCompCompletionModelMap;

  }
}
