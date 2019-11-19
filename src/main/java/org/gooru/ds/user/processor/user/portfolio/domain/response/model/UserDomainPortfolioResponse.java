
package org.gooru.ds.user.processor.user.portfolio.domain.response.model;


/**
 * @author mukul@gooru
 */
import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "portfolio"
})
public class UserDomainPortfolioResponse implements Serializable
{

    @JsonProperty("portfolio")
    private Portfolio portfolio = null;
    private final static long serialVersionUID = 3637780095721677754L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserDomainPortfolioResponse() {
    }

    /**
     * 
     * @param portfolio
     */
    public UserDomainPortfolioResponse(Portfolio portfolio) {
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

    public UserDomainPortfolioResponse withPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
        return this;
    }

}