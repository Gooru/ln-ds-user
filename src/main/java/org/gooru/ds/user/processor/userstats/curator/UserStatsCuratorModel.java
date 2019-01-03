package org.gooru.ds.user.processor.userstats.curator;

/**
 * @author ashish on 13/1/18.
 */
class UserStatsCuratorModel {
  private Long curatorId;
  private String curatorName;
  private Long count;

  public Long getCuratorId() {
    return curatorId;
  }

  public void setCuratorId(Long curatorId) {
    this.curatorId = curatorId;
  }

  public String getCuratorName() {
    return curatorName;
  }

  public void setCuratorName(String curatorName) {
    this.curatorName = curatorName;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }
}
