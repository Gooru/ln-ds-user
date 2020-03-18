
package org.gooru.ds.user.processor.struggling.competencies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.processor.grade.competency.compute.utils.CollectionUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 17-Oct-2019
 */
public class StrugglingCompetenciesService {

  private final static Logger LOGGER = LoggerFactory.getLogger(StrugglingCompetenciesService.class);

  private final StrugglingCompetenciesDao dao;
  private final static CoreService CORE_SERVICE = new CoreService(DBICreator.getDbiForCoreDS());

  public StrugglingCompetenciesService(DBI dbi) {
    this.dao = dbi.onDemand(StrugglingCompetenciesDao.class);
  }

  public JsonObject fetchStrugglingCompetencies(
      StrugglingCompetenciesCommand.StrugglingCompetenciesCommandBean bean) {

    // Validate the grades passed in the request and fetch the details
    List<GradeModel> gradeModels =
        this.dao.fetchGrades(CollectionUtils.toPostgresArrayLong(bean.getGrades()));
    if (bean.getGrades().size() != gradeModels.size()) {
      LOGGER.debug("not all grades present in the datastore for which the report is requested");
      return new JsonObject();
    }

    // Prepare the map of the grade to be referred further in the processing
    Map<Long, GradeModel> gradeModelMap = new HashMap<>();
    gradeModels.forEach(model -> {
      gradeModelMap.put(model.getId(), model);
    });

    JsonObject response = new JsonObject();
    JsonArray strugglingArray = new JsonArray();

    // Fetch all the active class members of the class
    List<String> classStudents = CORE_SERVICE.fetchClassMembers(bean.getClassId());
    LOGGER.debug("number of students in the class :{}", classStudents.size());

    bean.getGrades().forEach(grade -> {
      // Fetch all competencies fall under the grade to find out that which competencies are
      // struggling
      List<GradeCompetencyMapModel> gradeCompetencyMap = this.dao.fetchCompetencyMapByGrade(grade);
      LOGGER.debug("grade competency map returned: {}", gradeCompetencyMap.size());

      Map<String, List<GradeCompetencyMapModel>> competenciesByDomain = new HashMap<>();
      Map<String, DomainModel> domainModelMap = new HashMap<>();
      Map<String, String> compDomainMapping = new HashMap<>();
      Map<String, GradeCompetencyMapModel> competencyMap = new HashMap<>();

      // Iterate over the grade competency map and prepare different data models to be used in
      // further processing
      Set<String> compCodes = new HashSet<>();
      gradeCompetencyMap.forEach(model -> {
        compCodes.add(model.getCompCode());
        compDomainMapping.put(model.getCompCode(), model.getDomainCode());

        String domainCode = model.getDomainCode();
        if (competenciesByDomain.containsKey(domainCode)) {
          competenciesByDomain.get(domainCode).add(model);
        } else {
          List<GradeCompetencyMapModel> compMap = new ArrayList<>();
          compMap.add(model);
          competenciesByDomain.put(domainCode, compMap);
        }

        if (!domainModelMap.containsKey(domainCode)) {
          domainModelMap.put(domainCode, populateDomainModel(model));
        }

        if (!competencyMap.containsKey(model.getCompCode())) {
          competencyMap.put(model.getCompCode(), model);
        }
      });

      // Fetch struggling competencies from the competencies fall under grade and students
      List<StrugglingCompetencyModel> strugglingCompetencies =
          this.dao.fetchStrugglingCompetencies(CollectionUtils.convertToSqlArrayOfString(compCodes),
              CollectionUtils.convertToSqlArrayOfString(classStudents), bean.getToDate());
      LOGGER.debug("number of struggling competencies returned: {}", strugglingCompetencies.size());

      // Iterate on the struggling competencies and prepare map of competencies and number of
      // strudents struggling in it
      Map<String, Set<String>> strugglingCompetenciesMap = new HashMap<>();
      strugglingCompetencies.forEach(model -> {
        if (strugglingCompetenciesMap.containsKey(model.getCompCode())) {
          strugglingCompetenciesMap.get(model.getCompCode()).add(model.getUserId());
        } else {
          Set<String> users = new HashSet<>();
          users.add(model.getUserId());
          strugglingCompetenciesMap.put(model.getCompCode(), users);
        }
      });

      JsonObject gradeJson = new JsonObject();
      GradeModel gradeModel = gradeModelMap.get(grade);
      gradeJson.put("grade_id", gradeModel.getId());
      gradeJson.put("grade", gradeModel.getGrade());
      gradeJson.put("grade_Seq", gradeModel.getGradeSeq());
      gradeJson.put("description", gradeModel.getDescription());
      gradeJson.put("fw_code", gradeModel.getFwCode());

      // For all struggling competencies and user list, check if its been completed. if so, remove
      // it from the struggling competencies
      List<CompletedCompetencyModel> completedCompetencies =
          this.dao.fetchCompetencyCompletionStatus(gradeModel.getSubjectCode(),
              CollectionUtils.convertToSqlArrayOfString(strugglingCompetenciesMap.keySet()),
              CollectionUtils.convertToSqlArrayOfString(classStudents), bean.getToDate());
      LOGGER.debug("completed competencies found: {}", completedCompetencies.size());

      // Iterate on all completed competecies found for the given struggling competency and
      // students, remove them from the struggling competencies
      completedCompetencies.forEach(completedModel -> {
        String gutCode = completedModel.getGutCode();
        if (strugglingCompetenciesMap.containsKey(gutCode)) {
          strugglingCompetenciesMap.get(gutCode).remove(completedModel.getUserId());
          // After removal from the struggling competencies based on the completion, if there are no
          // student struggling then we don't need to report the competency as struggling
          if (strugglingCompetenciesMap.get(gutCode).isEmpty()) {
            strugglingCompetenciesMap.remove(gutCode);
          }
        }
      });

      // Sort the struggling competencies based on the number of students struggling in them
      Map<String, Set<String>> sortedStrugglingCompetenciesMap = sortMap(strugglingCompetenciesMap);

      // Now arrange the results by grade, domain as most students struggling in competency appear
      // first
      Map<String, Map<String, Integer>> strugglingCompByDomain = new HashMap<>();
      for (Map.Entry<String, Set<String>> entry : sortedStrugglingCompetenciesMap.entrySet()) {
        String compCode = entry.getKey();
        String domainCode = compDomainMapping.get(compCode);

        if (strugglingCompByDomain.containsKey(domainCode)) {
          strugglingCompByDomain.get(domainCode).put(compCode, entry.getValue().size());
        } else {
          Map<String, Integer> compCountMap = new HashMap<>();
          compCountMap.put(compCode, entry.getValue().size());
          strugglingCompByDomain.put(domainCode, compCountMap);
        }
      }

      // Fetch display codes from the DCM table
      List<CompetencyModel> compDisplayCodes =
          this.dao.fetchCompetencyDisplayCode(gradeModel.getSubjectCode(),
              CollectionUtils.convertToSqlArrayOfString(strugglingCompetenciesMap.keySet()));
      Map<String, String> displayCodeMap = new HashMap<>();
      compDisplayCodes.forEach(model -> {
        displayCodeMap.put(model.getCompCode(), model.getDisplayCode());
      });

      JsonArray domainsArray = new JsonArray();
      for (Map.Entry<String, Map<String, Integer>> entry : strugglingCompByDomain.entrySet()) {
        String domainCode = entry.getKey();
        JsonObject domainJson = prepareDomainJson(domainModelMap.get(domainCode));

        Map<String, Integer> compCount = entry.getValue();
        JsonArray compArray = new JsonArray();
        compCount.keySet().forEach(key -> {
          JsonObject compJson = new JsonObject();
          compJson.put("comp_code", key);
          compJson.put("student_count", compCount.get(key));

          GradeCompetencyMapModel compModel = competencyMap.get(key);
          compJson.put("comp_name", compModel.getCompName());
          compJson.put("comp_student_desc", compModel.getCompStudentDesc());
          compJson.put("comp_seq", compModel.getCompSeq());
          compJson.put("comp_display_code", displayCodeMap.get(key));

          compArray.add(compJson);
        });
        domainJson.put("competencies", compArray);
        domainsArray.add(domainJson);
      }
      gradeJson.put("domains", domainsArray);
      strugglingArray.add(gradeJson);
    });

    response.put("struggling_competencies", strugglingArray);
    return response;
  }

  private static DomainModel populateDomainModel(GradeCompetencyMapModel model) {
    DomainModel domainModel = new DomainModel();
    domainModel.setDomainId(model.getDomainId());
    domainModel.setDomainCode(model.getDomainCode());
    domainModel.setDomainName(model.getDomainName());
    domainModel.setDomainSeq(model.getDomainSeq());
    return domainModel;
  }

  public static HashMap<String, Set<String>> sortMap(Map<String, Set<String>> hm) {
    // Create a list from elements of HashMap
    List<Map.Entry<String, Set<String>>> list =
        new LinkedList<Map.Entry<String, Set<String>>>(hm.entrySet());

    // Sort the list
    Collections.sort(list, new Comparator<Map.Entry<String, Set<String>>>() {
      public int compare(Map.Entry<String, Set<String>> o1, Map.Entry<String, Set<String>> o2) {
        Integer o1Size = o1.getValue().size();
        Integer o2Size = o2.getValue().size();
        return o2Size.compareTo(o1Size);
      }
    });

    // put data from sorted list to hashmap
    HashMap<String, Set<String>> temp = new LinkedHashMap<String, Set<String>>();
    for (Map.Entry<String, Set<String>> aa : list) {
      temp.put(aa.getKey(), aa.getValue());
    }

    return temp;
  }

  private JsonObject prepareDomainJson(DomainModel model) {
    return new JsonObject().put("domain_id", model.getDomainId())
        .put("domain_name", model.getDomainName()).put("domain_code", model.getDomainCode())
        .put("domain_seq", model.getDomainSeq());
  }
}
