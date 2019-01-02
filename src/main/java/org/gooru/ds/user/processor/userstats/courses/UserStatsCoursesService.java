package org.gooru.ds.user.processor.userstats.courses;

import java.util.List;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 13/1/18.
 */
class UserStatsCoursesService {

  private final UserStatsCoursesDao userStatsCompetencyDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsCoursesService.class);

  UserStatsCoursesService(DBI dbi) {
    this.userStatsCompetencyDao = dbi.onDemand(UserStatsCoursesDao.class);
  }

  public UserStatsCoursesModelResponse fetchUserStatsCourses(UserStatsCoursesCommand command) {
    List<UserStatsCoursesModel> models =
        userStatsCompetencyDao.fetchUserStatsCourses(command.asBean());
    UserStatsCoursesModelResponse result = new UserStatsCoursesModelResponse();
    result.setCourses(models);
    return result;
  }

}
