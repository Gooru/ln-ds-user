
package org.gooru.ds.user.processor.domain.competency.perf.report;

import java.util.List;
import java.util.Map;
import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.processor.domain.report.dbhelpers.UserModel;
import org.gooru.ds.user.processor.domain.report.utils.DomainReportResponseConstants;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public final class DomainCompetencyCompletionReportResponseBuilder {

  private DomainCompetencyCompletionReportResponseBuilder() {
    throw new AssertionError();
  }

  public static JsonObject build(List<UserModel> users,
      Map<String, UserCompetencyCompletionModel> userPerfs, String agent) {
    if (agent.equalsIgnoreCase(Constants.Params.AGENT_MOBILE)) {
      return buildResponseForMobile(users, userPerfs, agent);
    } else {
      return buildResponseForDesktop(users, userPerfs, agent);
    }
  }

  private static JsonObject buildResponseForDesktop(List<UserModel> users,
      Map<String, UserCompetencyCompletionModel> userPerfs, String agent) {
    JsonObject response = new JsonObject();
    JsonArray usersArray = new JsonArray();
    users.forEach(user -> {
      UserCompetencyCompletionModel perfModel = userPerfs.get(user.getId());
        JsonObject userObj = new JsonObject();
        JsonObject userJson = new JsonObject();
        userJson.put(DomainReportResponseConstants.DesktopResponseConstants.ID, user.getId());
        userJson.put(DomainReportResponseConstants.DesktopResponseConstants.FIRST_NAME,
            user.getFirstName());
        userJson.put(DomainReportResponseConstants.DesktopResponseConstants.LAST_NAME,
            user.getLastName());
        userJson.put(DomainReportResponseConstants.DesktopResponseConstants.THUMBNAIL,
            user.getThumbnail());

        userObj.put(DomainReportResponseConstants.DesktopResponseConstants.USER, userJson);

        userObj.put(DomainReportResponseConstants.DesktopResponseConstants.STATUS,
            perfModel.getStatus());

        usersArray.add(userObj);
    });

    return response.put(DomainReportResponseConstants.DesktopResponseConstants.USERS, usersArray);
  }

  private static JsonObject buildResponseForMobile(List<UserModel> users,
      Map<String, UserCompetencyCompletionModel> userPerfs, String agent) {
    JsonObject response = new JsonObject();
    JsonArray usersArray = new JsonArray();
    users.forEach(user -> {
      UserCompetencyCompletionModel perfModel = userPerfs.get(user.getId());
      JsonObject userObj = new JsonObject();

      JsonObject userJson = new JsonObject();
      userJson.put(DomainReportResponseConstants.MobileResponseConstants.ID, user.getId());
      userJson.put(DomainReportResponseConstants.MobileResponseConstants.FIRST_NAME,
          user.getFirstName());
      userJson.put(DomainReportResponseConstants.MobileResponseConstants.LAST_NAME,
          user.getLastName());
      userJson.put(DomainReportResponseConstants.MobileResponseConstants.THUMBNAIL,
          user.getThumbnail());

      userObj.put(DomainReportResponseConstants.MobileResponseConstants.USER, userJson);
      userObj.put(DomainReportResponseConstants.MobileResponseConstants.STATUS,
          perfModel.getStatus());
      usersArray.add(userObj);
    });

    return response.put(DomainReportResponseConstants.MobileResponseConstants.USERS, usersArray);
  }
}
