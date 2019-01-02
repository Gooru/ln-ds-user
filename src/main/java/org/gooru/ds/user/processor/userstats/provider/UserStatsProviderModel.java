package org.gooru.ds.user.processor.userstats.provider;

/**
 * @author ashish on 13/1/18.
 */
class UserStatsProviderModel {
  private Long providerId;
  private String providerName;
  private Long count;

  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }
}
