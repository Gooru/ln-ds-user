package org.gooru.ds.user.processor.atc.pvc;

import org.gooru.ds.user.app.jdbi.DBICreator;
import org.skife.jdbi.v2.DBI;
import com.hazelcast.util.StringUtil;
import io.vertx.core.json.JsonArray;


/**
 * @author mukul@gooru
 */
public class CompetencyPerfVsCompletionService {

  public JsonArray fetchUserPerformanceCompletion(CompetencyPerfVsCompletionCommand command) {
    JsonArray pvcArray = new JsonArray();
    CourseBasedPerfVsCompletionCalculator courseBasedPerfVsCompletionCalculator =
        new CourseBasedPerfVsCompletionCalculator(DBICreator.getDbiForDefaultDS(),
            DBICreator.getDbiForCoreDS());
    pvcArray =
        courseBasedPerfVsCompletionCalculator.fetchCourseBasedUserPerformanceCompletion(command);
    return pvcArray;
  }

}
