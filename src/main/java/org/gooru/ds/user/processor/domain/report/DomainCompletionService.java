
package org.gooru.ds.user.processor.domain.report;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.gooru.ds.user.constants.StatusConstants;
import org.gooru.ds.user.processor.atc.pvc.course.competency.utils.CollectionUtils;
import org.gooru.ds.user.processor.domain.report.dbhelpers.DomainCompetencyMatrixFetcherDao;
import org.gooru.ds.user.processor.domain.report.dbhelpers.DomainCompetencyMatrixModel;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author szgooru Created On 29-Jan-2019
 */
public class DomainCompletionService {

  private final DomainCompetencyMatrixFetcherDao dao;
  private final static Logger LOGGER = LoggerFactory.getLogger(DomainCompletionService.class);

  public DomainCompletionService(DBI defaultDbi) {
    this.dao = defaultDbi.onDemand(DomainCompetencyMatrixFetcherDao.class);
  }

  public void computeDomainCompetencyCompletion(List<String> classMembers, String subject,
      Timestamp toDate,
      Map<String, DomainCompetencyCompletionModel> domainCompetencyCompletionModels) {
    int memberCount = classMembers.size();

    classMembers.forEach(member -> {
      LOGGER.debug("processing '{}' user skyline", member);
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
          DomainCompetencyCompletionModel domainCompCompletionModel = entry.getValue();
          if (inferredCompletedCompMatrixMap.containsKey(domainCode)) {
            int domainAvgCompletion = 0;
            for (Map.Entry<String, CompetencyCompletionModel> completionModels : domainCompCompletionModel
                .getCompetencies().entrySet()) {
              CompetencyCompletionModel compCompletionModel = completionModels.getValue();
              Integer avgCompletion = compCompletionModel.getAvgCompletion();

              DomainCompetencyMatrixModel matrixModel = inferredCompletedCompMatrixMap
                  .get(domainCode).get(compCompletionModel.getCompetencyCode());
              if (matrixModel.getStatus() >= StatusConstants.INFERRED) {
                compCompletionModel
                    .setAvgCompletion((avgCompletion == null ? 1 : avgCompletion + 1));
                domainAvgCompletion = domainAvgCompletion + 1;
              }
            }
            LOGGER.debug("setting avg completion:{} for domain '{}'", domainAvgCompletion,
                domainCode);
            long finalAvgCompletion =
                (domainCompCompletionModel.getAverage_completions() == null ? 0
                    : domainCompCompletionModel.getAverage_completions()) + domainAvgCompletion;
            domainCompCompletionModel.setAverage_completions(finalAvgCompletion);
          } else {
            domainCompCompletionModel.setAverage_completions(
                (domainCompCompletionModel.getAverage_completions() == null ? 0
                    : domainCompCompletionModel.getAverage_completions()));
          }
        }
      }
    });

    for (Map.Entry<String, DomainCompetencyCompletionModel> entry : domainCompetencyCompletionModels
        .entrySet()) {
      DomainCompetencyCompletionModel domainCompCompletionModel = entry.getValue();
      int numberOfCompetencies = domainCompCompletionModel.getCompetencies().size();
      if (numberOfCompetencies > 0) {
        int totalCompletionsByDomain = 0;
        for (Map.Entry<String, CompetencyCompletionModel> completionModels : domainCompCompletionModel
            .getCompetencies().entrySet()) {
          CompetencyCompletionModel ccm = completionModels.getValue();

          // Add up the competency completions of all competencies under the domain for domain level
          // percentage completions
          totalCompletionsByDomain = ccm.getAvgCompletion() + totalCompletionsByDomain;

          // Compute the percentage complete for the competency
          if (ccm.getAvgCompletion() > 0) {
            double totalCompetencyCompletions = ccm.getAvgCompletion();
            ccm.setPercentageCompletion(
                Math.round((totalCompetencyCompletions / memberCount) * 100));
          } else {
            ccm.setPercentageCompletion(0l);
          }
        }

        // converting the total completed competencies from int to double for not to round down the
        // result to zero of integer division
        if (totalCompletionsByDomain > 0) {
          double totalCompletions = totalCompletionsByDomain;
          domainCompCompletionModel.setAverage_completions(
              Math.round((totalCompletions / (numberOfCompetencies * memberCount)) * 100));
        } else {
          domainCompCompletionModel.setAverage_completions(0l);
        }
      }
    }
  }

  /*
   * From the domain competency matrix (skyline) of the user, generate the map of the completed
   * competencies by domain
   */
  private Map<String, Map<String, DomainCompetencyMatrixModel>> generateCompletedCompetencyMatrixMap(
      List<DomainCompetencyMatrixModel> userCompetencyModels) {
    LOGGER.debug("fetching completed competencies");
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

  /*
   * Compute the inferred completion for each competency under the domain based on the completed
   * competencies and sequence.
   */
  private Map<String, Map<String, DomainCompetencyMatrixModel>> computeInferredCompletedCompetencyMatrixMap(
      List<DomainCompetencyMatrixModel> userCompetencyModels,
      Map<String, Map<String, DomainCompetencyMatrixModel>> completedCompMatrixMap) {
    LOGGER.debug("computing inferred completion");
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

  /*
   * Fetch domain competency matrix 
   */
  public Map<String, Map<String, DomainCompetencyMatrixModel>> fetchAllDomainCompetencyMatrix(
      String subjectCode) {
    LOGGER.debug("fetching all DCM for the subject '{}", subjectCode);
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
    LOGGER.debug("fetching domain details");
    List<DomainCompetencyCompletionModel> allDomains =
        this.dao.fetchDomains(subjectCode, CollectionUtils.convertToSqlArrayOfString(domainCodes));
    Map<String, DomainCompetencyCompletionModel> domainCompCompletionModelMap = new HashMap<>();
    allDomains.forEach(domain -> {
      domainCompCompletionModelMap.put(domain.getDomain().getTx_domain_code(), domain);
    });

    return domainCompCompletionModelMap;

  }
}
