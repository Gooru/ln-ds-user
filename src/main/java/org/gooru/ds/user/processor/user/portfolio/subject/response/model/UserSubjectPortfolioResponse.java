
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
    "portfolio"
})
public class UserSubjectPortfolioResponse implements Serializable
{

    @JsonProperty("portfolio")
    private Portfolio portfolio = null;
    private final static long serialVersionUID = -6068024376246568162L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserSubjectPortfolioResponse() {
    }

    /**
     * 
     * @param portfolio
     */
    public UserSubjectPortfolioResponse(Portfolio portfolio) {
        super();
        this.portfolio = portfolio;
    }

    @JsonProperty("portfolio")
    public Portfolio getPortfolio() {
        return portfolio;
    }

    @JsonProperty("portfolio")
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public UserSubjectPortfolioResponse withPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
        return this;
    }

}