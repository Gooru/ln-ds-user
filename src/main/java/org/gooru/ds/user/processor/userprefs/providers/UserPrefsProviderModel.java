package org.gooru.ds.user.processor.userprefs.providers;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsProviderModel {
    private Long providerId;
    private String providerName;
    private Float pref;

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

    public Float getPref() {
        return pref;
    }

    public void setPref(Float pref) {
        this.pref = pref;
    }
}
