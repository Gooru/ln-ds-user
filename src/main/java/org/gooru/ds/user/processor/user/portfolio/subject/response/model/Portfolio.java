
package org.gooru.ds.user.processor.user.portfolio.subject.response.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * @author mukul@gooru
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userId",
    "domains"
})
public class Portfolio implements Serializable
{

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("domains")
    private List<Domain> domains = null;
    private final static long serialVersionUID = 5148954858549081664L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Portfolio() {
    }

    /**
     * 
     * @param domains
     * @param userId
     */
    public Portfolio(String userId, List<Domain> domains) {
        super();
        this.userId = userId;
        this.domains = domains;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Portfolio withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @JsonProperty("domains")
    public List<Domain> getDomains() {
        return domains;
    }

    @JsonProperty("domains")
    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public Portfolio withDomains(List<Domain> domains) {
        this.domains = domains;
        return this;
    }

}