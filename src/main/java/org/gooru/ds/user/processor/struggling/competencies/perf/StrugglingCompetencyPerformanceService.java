
package org.gooru.ds.user.processor.struggling.competencies.perf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.processor.grade.competency.compute.utils.CollectionUtils;
import org.gooru.ds.user.processor.struggling.competencies.CoreService;
import org.gooru.ds.user.processor.struggling.competencies.UserModel;
import org.skife.jdbi.v2.DBI;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 12-Nov-2019
 */
public class StrugglingCompetencyPerformanceService {

  private final StrugglingCompetencyPerformanceDao dao;
  private final static CoreService CORE_SERVICE = new CoreService(DBICreator.getDbiForCoreDS());

  public StrugglingCompetencyPerformanceService(DBI dbi) {
    this.dao = dbi.onDemand(StrugglingCompetencyPerformanceDao.class);
  }

  public JsonObject fetchCompetencyPerformance(
      StrugglingCompetencyPerformanceCommand.StrugglingCompetencyPerformanceCommandBean bean) {

    // Fetch class members and filter the students for which the competency is struggling
    List<String> classStudents = CORE_SERVICE.fetchClassMembers(bean.getClassId());
    List<String> strugglingUsers = this.dao.fetchUsersOfStrugglingCompetency(
        CollectionUtils.convertToSqlArrayOfString(classStudents), bean.getCompetency(),
        bean.getToDate());


    // Fetch performance of the struggling users for the given competency
    List<StrugglingCompetencyPerformanceModel> perfModels = this.dao.fetchCompetencyPerformance(
        CollectionUtils.convertToSqlArrayOfString(strugglingUsers), bean.getCompetency());
    Map<String, StrugglingCompetencyPerformanceModel> performanceByUsers = new HashMap<>();
    perfModels.forEach(model -> {
      performanceByUsers.put(model.getUserId(), model);
    });

    // Fetch demographic details of the users and prepare response with the student performance
    JsonArray studentPerfArray = new JsonArray();
    List<UserModel> users = CORE_SERVICE.fetchUserDetails(strugglingUsers);
    users.forEach(user -> {
      StrugglingCompetencyPerformanceModel perfModel = performanceByUsers.get(user.getId());
      if (perfModel != null) {
        JsonObject userJson = new JsonObject();
        userJson.put("id", user.getId());
        userJson.put("first_name", user.getFirstName());
        userJson.put("last_name", user.getLastName());
        userJson.put("display_name", user.getDisplayName());
        userJson.put("thumbnail", user.getThumbnail());
        userJson.put("username", user.getUsername());

        userJson.put("performance", perfModel.getScore());
        studentPerfArray.add(userJson);
      } else {
        // We do not want to add the user in the response if there is no score exists in evidence
        // table or the score is null
      }
    });

    JsonObject response = new JsonObject();
    response.put("students", studentPerfArray);
    return response;
  }
}
