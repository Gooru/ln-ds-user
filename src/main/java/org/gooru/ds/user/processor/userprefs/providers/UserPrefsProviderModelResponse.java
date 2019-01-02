package org.gooru.ds.user.processor.userprefs.providers;

import java.util.List;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsProviderModelResponse {
  private List<UserPrefsProviderModel> providers;

  public List<UserPrefsProviderModel> getProviders() {
    return providers;
  }

  public void setProviders(List<UserPrefsProviderModel> providers) {
    this.providers = providers;
  }
}
