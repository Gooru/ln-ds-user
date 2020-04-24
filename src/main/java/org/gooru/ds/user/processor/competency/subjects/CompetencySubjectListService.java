package org.gooru.ds.user.processor.competency.subjects;

import java.util.ArrayList;
import java.util.List;
import org.gooru.ds.user.processor.grade.competency.compute.utils.CollectionUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 * 
 */
public class CompetencySubjectListService {


  private final CompetencySubjectListDao competencySubjectListDao;
  private final TenantSubjectPrefDao tenantSubjectPrefDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(CompetencySubjectListService.class);
  private final static String IS_GLOBAL_VISIBLE = "is_global_visible";
  private final static String GUT_SUBJECT_CODES = "gut_subject_codes";
  private final static String DEFAULT_GUT_SUBJECT_CODE = "default_gut_subject_code";

  CompetencySubjectListService(DBI dbi, DBI coreDbi) {
    this.competencySubjectListDao = dbi.onDemand(CompetencySubjectListDao.class);
    this.tenantSubjectPrefDao = coreDbi.onDemand(TenantSubjectPrefDao.class);
  }

  public CompetencySubjectListModelResponse fetchCompetencySubjects(
      CompetencySubjectListCommand command) {
    CompetencySubjectListModelResponse result = new CompetencySubjectListModelResponse();
    result.setSubjects(getSubjects(command));
    return result;
  }

  @SuppressWarnings("unchecked")
  private List<CompetencySubjectListModel> getSubjects(CompetencySubjectListCommand command) {
    boolean isGlobalVisible = true;
    String defaultSubjectId = null;
    List<String> subjectIds = new ArrayList<>(0);

    TenantSubjectPrefModel tenantSubjectPrefModel =
        tenantSubjectPrefDao.fetchSubjectPref(command.getTenantId());
    if (tenantSubjectPrefModel != null && tenantSubjectPrefModel.getSubjectPref() != null) {
      JsonObject subjectPrefs =
          tenantSubjectPrefModel.getSubjectPref().getJsonObject(command.getClassificationType());
      if (subjectPrefs != null) {
        isGlobalVisible = subjectPrefs.getBoolean(IS_GLOBAL_VISIBLE);
        JsonArray ids = subjectPrefs.getJsonArray(GUT_SUBJECT_CODES);
        defaultSubjectId = subjectPrefs.getString(DEFAULT_GUT_SUBJECT_CODE);
        if (ids != null) {
          subjectIds = ids.getList();
        }

      }
    }
    List<CompetencySubjectListModel> results = null;
    if (isGlobalVisible) {
      LOGGER.debug("Fetch Subjects By Global Visible And Prefered Subject Ids");
      results = competencySubjectListDao.fetchCompetencySubjectList(command.asBean(),
          CollectionUtils.convertToSqlArrayOfString(subjectIds));
    } else {
      LOGGER.debug("Fetch Subjects By  Prefered Subject Ids");
      results = competencySubjectListDao.fetchCompetencySubjectListByIds(command.asBean(),
          CollectionUtils.convertToSqlArrayOfString(subjectIds));
    }

    return buildSubject(defaultSubjectId, results);
  }

  private List<CompetencySubjectListModel> buildSubject(String defaultSubjectId,
      List<CompetencySubjectListModel> results) {
    results.forEach(result -> {
      if (defaultSubjectId != null && defaultSubjectId.equalsIgnoreCase(result.getId())) {
        result.setDefault(true);
      }
    });
    return results;
  }

}
