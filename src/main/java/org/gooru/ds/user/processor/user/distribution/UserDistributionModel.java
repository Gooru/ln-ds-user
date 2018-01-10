package org.gooru.ds.user.processor.user.distribution;

/**
 * @author ashish on 10/1/18.
 */
class UserDistributionModel {
    private String code;
    private String name;
    private Long total;
    private Long active;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }
}
