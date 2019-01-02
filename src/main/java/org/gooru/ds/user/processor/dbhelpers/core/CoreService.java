package org.gooru.ds.user.processor.dbhelpers.core;

import java.util.List;
import org.skife.jdbi.v2.DBI;

/**
 * @author szgooru on 18-Jul-2018
 */
public class CoreService {

  private final CoreCourseDao courseDao;
  private final CoreClassMemberDao classMemberDao;

  public CoreService(DBI dbi) {
    this.courseDao = dbi.onDemand(CoreCourseDao.class);
    this.classMemberDao = dbi.onDemand(CoreClassMemberDao.class);
  }

  public String fetchCourseSubject(String courseId) {
    return this.courseDao.fetchCourseSubject(courseId);
  }

  public List<String> fetchClassMembers(String classId) {
    return this.classMemberDao.fetchClassMembers(classId);
  }

  public List<String> fetchCourseCompetencies(String courseId) {
    return this.courseDao.fetchCourseCompetencies(courseId);
  }
}
