package org.gooru.ds.user.processor.atc.pvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class CompetencyCompletionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyCompletionService.class);
  private static final int INFERRED = 2;
  private static final int ASSERTED = 3;
  private static final int COMPLETED = 4;
  private static final int INPROGRESS = 1;
  private final CompetencyCompletionDao competencyCompletionDao;
  private final UserSkylineDao userSkylineDao;
  private Integer completionCount;

  CompetencyCompletionService(DBI dbi) {
    this.competencyCompletionDao = dbi.onDemand(CompetencyCompletionDao.class);
    this.userSkylineDao = dbi.onDemand(UserSkylineDao.class);
  }

  JsonObject fetchUserCompetencyStatus(String user, String subjectCode,
      List<String> competencyCodes, Integer month, Integer year) {

    JsonObject counts = new JsonObject();
    completionCount = 0;
    List<CompetencyCompletionModel> userCompetencyCompletionModels = new ArrayList<>();
    List<UserSkylineModel> userSkylineModels = new ArrayList<>();

    if (competencyCodes != null && !competencyCodes.isEmpty() && (month == null || year == null)) {
      userCompetencyCompletionModels = competencyCompletionDao.fetchCompetencyCompletion(user,
          subjectCode, PGArrayUtils.convertFromListStringToSqlArrayOfString(competencyCodes));
      userSkylineModels = userSkylineDao.fetchUserSkyline(user, subjectCode);
    } else if (competencyCodes != null && !competencyCodes.isEmpty()
        && (month != null && year != null)) {
      userCompetencyCompletionModels =
          competencyCompletionDao.fetchCompetencyCompletionMonthBased(user, subjectCode,
              PGArrayUtils.convertFromListStringToSqlArrayOfString(competencyCodes), month, year);
      userSkylineModels = userSkylineDao.fetchUserSkylineMonthBased(user, subjectCode, month, year);
    }

    if (userCompetencyCompletionModels.isEmpty()) {
      LOGGER.info("The User competency Status model is empty");
      return new JsonObject();
    } else {
      List<CompetencyCompletionModel> completed = userCompetencyCompletionModels.stream()
          .filter(model -> model.getStatus() >= COMPLETED).collect(Collectors.toList());
      completionCount = completed.size();
      LOGGER.debug("Completed/Mastered Competencies " + completionCount);

      Map<String, Map<String, CompetencyCompletionModel>> completedCompMap = new HashMap<>();
      Map<String, Map<String, UserSkylineModel>> skylineCompletedCompMap = new HashMap<>();

      completed.forEach(model -> {
        String domain = model.getDomainCode();
        String compCode = model.getCompetencyCode();
        LOGGER.debug("Completed/Mastered Competencies Code" + compCode);

        if (completedCompMap.containsKey(domain)) {
          Map<String, CompetencyCompletionModel> competencies = completedCompMap.get(domain);
          competencies.put(compCode, model);
          completedCompMap.put(domain, competencies);
        } else {
          Map<String, CompetencyCompletionModel> competencies = new HashMap<>();
          competencies.put(compCode, model);
          completedCompMap.put(domain, competencies);
        }
      });

      if (!userSkylineModels.isEmpty()) {
        // We need to filter out completed/mastered specifically since in the L_p_C_s_ts table
        // in-progress is also stored.
        List<UserSkylineModel> skylineCompleted = userSkylineModels.stream()
            .filter(skymodel -> skymodel.getStatus() >= COMPLETED).collect(Collectors.toList());

        skylineCompleted.forEach(model -> {
          String domain = model.getDomainCode();
          String compCode = model.getCompetencyCode();
          LOGGER.debug("Skyline Completed/Mastered Competencies Code" + compCode);

          if (skylineCompletedCompMap.containsKey(domain)) {
            Map<String, UserSkylineModel> skyCompetencies = skylineCompletedCompMap.get(domain);
            skyCompetencies.put(compCode, model);
            skylineCompletedCompMap.put(domain, skyCompetencies);
          } else {
            Map<String, UserSkylineModel> skyCompetencies = new HashMap<>();
            skyCompetencies.put(compCode, model);
            skylineCompletedCompMap.put(domain, skyCompetencies);
          }
        });
      }

      userCompetencyCompletionModels.forEach(model -> {
        String domainCode = model.getDomainCode();
        int sequence = model.getCompetencySeq();
        int status = model.getStatus();

        if (completedCompMap.containsKey(domainCode)) {
          Map<String, CompetencyCompletionModel> competencies = completedCompMap.get(domainCode);
          for (Map.Entry<String, CompetencyCompletionModel> entry : competencies.entrySet()) {
            CompetencyCompletionModel compModel = entry.getValue();
            int compSeq = compModel.getCompetencySeq();

            if (sequence < compSeq && status < ASSERTED && model.getStatus() != INFERRED) {
              model.setStatus(INFERRED);
              completionCount++;
            }
          }
        }
        // Run through the skyline to check for completion
        if (skylineCompletedCompMap != null && !skylineCompletedCompMap.isEmpty()
            && skylineCompletedCompMap.containsKey(domainCode)) {
          Map<String, UserSkylineModel> skyCompetencies = skylineCompletedCompMap.get(domainCode);
          for (Map.Entry<String, UserSkylineModel> entry : skyCompetencies.entrySet()) {
            UserSkylineModel skyCompModel = entry.getValue();
            int skyCompSeq = skyCompModel.getCompetencySeq();

            if (sequence < skyCompSeq && status < ASSERTED && model.getStatus() != INFERRED) {
              model.setStatus(INFERRED);
              completionCount++;
            }
          }
        }
      });

      counts.put("completionCount", completionCount);
      LOGGER.debug("Completed/Mastered/Inferred " + completionCount);

      List<CompetencyCompletionModel> inProgress = userCompetencyCompletionModels.stream()
          .filter(model -> model.getStatus() == INPROGRESS).collect(Collectors.toList());
      counts.put("inprogressCount", inProgress.size());
      LOGGER.debug("InProgress Competencies " + inProgress.size());

      return counts;
    }
  }

}
