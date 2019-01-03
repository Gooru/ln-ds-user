package org.gooru.ds.user.processor.userperf.course;

import java.util.List;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserPerfCourseService {

  private final UserPerfCourseDao userPerfCourseDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCourseService.class);

  UserPerfCourseService(DBI dbi) {
    this.userPerfCourseDao = dbi.onDemand(UserPerfCourseDao.class);
  }

  public List<UserPerfCourseBaseModel> fetchUserCoursePerf(UserPerfCourseCommand command) {
    List<UserPerfCourseBaseModel> result;
    result = userPerfCourseDao.fetchUserPerfCourse(command.asBean());
    return result;
  }

}
