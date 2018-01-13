package org.gooru.ds.user.processor.userprefs.curators;

/**
 * @author ashish on 13/1/18.
 */
class UserPrefsCuratorModel {
    private Long curatorId;
    private String curatorName;
    private Float pref;

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

    public Float getPref() {
        return pref;
    }

    public void setPref(Float pref) {
        this.pref = pref;
    }
}
