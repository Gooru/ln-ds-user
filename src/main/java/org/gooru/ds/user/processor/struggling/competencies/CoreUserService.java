
package org.gooru.ds.user.processor.struggling.competencies;

import java.util.List;
import java.util.Set;
import org.gooru.ds.user.processor.grade.competency.compute.utils.CollectionUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author szgooru Created On 17-Oct-2019
 */
public class CoreUserService {

  private final CoreUserDao dao;

  public CoreUserService(DBI dbi) {
    this.dao = dbi.onDemand(CoreUserDao.class);
  }

  public List<UserModel> fetchUserDetails(Set<String> students) {
    return this.dao.fetchUserDetails(CollectionUtils.convertToSqlArrayOfString(students));
  }
}
