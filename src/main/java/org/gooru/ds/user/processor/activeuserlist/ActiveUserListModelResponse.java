package org.gooru.ds.user.processor.activeuserlist;

import java.util.List;

/**
 * @author ashish on 11/1/18.
 */
class ActiveUserListModelResponse {

  private List<ActiveUserListModel> users;

  public List<ActiveUserListModel> getUsers() {
    return users;
  }

  public void setUsers(List<ActiveUserListModel> users) {
    this.users = users;
  }
}
