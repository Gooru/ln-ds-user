package org.gooru.ds.user.processor.userperf.course;

/**
 * @author mukul@gooru
 */
class UserPerfUnitModel {

    private String unitId;
    private String unitTitle;
    private Long unitAsmtTimeSpent;
    private Float unitAsmtScore;
    private Long unitCollTimeSpent;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public Long getUnitAsmtTimeSpent() {
        return unitAsmtTimeSpent;
    }

    public void setUnitAsmtTimeSpent(Long unitAsmtTimeSpent) {
        this.unitAsmtTimeSpent = unitAsmtTimeSpent;
    }

    public Float getUnitAsmtScore() {
        return unitAsmtScore;
    }

    public void setUnitAsmtScore(Float unitAsmtScore) {
        this.unitAsmtScore = unitAsmtScore;
    }

    public Long getUnitCollTimeSpent() {
        return unitCollTimeSpent;
    }

    public void setUnitCollTimeSpent(Long unitCollTimeSpent) {
        this.unitCollTimeSpent = unitCollTimeSpent;
    }

}
