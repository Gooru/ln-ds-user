
package org.gooru.ds.user.processor.struggling.competencies;

import java.util.List;
import org.gooru.ds.user.processor.grade.competency.compute.utils.CollectionUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author szgooru Created On 17-Oct-2019
 */
public class CoreService {

  private final CoreDao dao;

  public CoreService(DBI dbi) {
    this.dao = dbi.onDemand(CoreDao.class);
  }

  public List<UserModel> fetchUserDetails(List<String> students) {
    return this.dao.fetchUserDetails(CollectionUtils.convertToSqlArrayOfString(students));
  }
  
  public List<String> fetchClassMembers(String classId) {
    return this.dao.fetchClassMembers(classId);
  }
}
