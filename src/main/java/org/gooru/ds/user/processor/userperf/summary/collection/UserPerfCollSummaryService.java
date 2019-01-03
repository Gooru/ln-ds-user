package org.gooru.ds.user.processor.userperf.summary.collection;

import java.util.ArrayList;
import java.util.List;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserPerfCollSummaryService {

  private final UserPerfCollSummaryDao userPerfCollSummaryDao;
  private UserPerfCollSummaryCommand command;
  private String classId;
  private String courseId;
  private String unitId;
  private String lessonId;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCollSummaryService.class);

  UserPerfCollSummaryService(DBI dbi) {
    this.userPerfCollSummaryDao = dbi.onDemand(UserPerfCollSummaryDao.class);
  }

  public UserPerfCollSummaryModelResponse fetchUserCollSummary(UserPerfCollSummaryCommand command) {
    // Refactor
    this.command = command;
    classId = this.command.getclassId();
    courseId = this.command.getcourseId();
    lessonId = this.command.getlessonId();
    unitId = this.command.getunitId();
    List<UserPerfCollSummaryModel> model = new ArrayList<>();

    if (courseId == null && lessonId == null && unitId == null) {
      model = fetchCollSummaryforComp(this.command);
    } else {
      model = fetchCollSummary(this.command);
    }

    // List<UserPerfCollSummaryModel> models =
    // userPerfCollSummaryDao.fetchUserPerfCollSummary(command.asBean());
    UserPerfCollSummaryModelResponse result = new UserPerfCollSummaryModelResponse();
    result.setResources(model);
    return result;

  }

  private List<UserPerfCollSummaryModel> fetchCollSummary(UserPerfCollSummaryCommand command) {
    return userPerfCollSummaryDao.fetchUserPerfCollSummary(command.asBean());
  }

  private List<UserPerfCollSummaryModel> fetchCollSummaryforComp(
      UserPerfCollSummaryCommand command) {
    return userPerfCollSummaryDao.fetchUserPerfCollSummaryforComp(command.asBean());
  }

}
