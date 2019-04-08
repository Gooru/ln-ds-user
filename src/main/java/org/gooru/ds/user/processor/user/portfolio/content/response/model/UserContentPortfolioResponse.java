
package org.gooru.ds.user.processor.user.portfolio.content.response.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "portfolio"
})
public class UserContentPortfolioResponse implements Serializable
{

    @JsonProperty("portfolio")
    private Portfolio portfolio = null;
    private final static long serialVersionUID = 7446566718172171814L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserContentPortfolioResponse() {
    }

    /**
     * 
     * @param portfolio
     */
    public UserContentPortfolioResponse(Portfolio portfolio) {
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

    public UserContentPortfolioResponse withPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
        return this;
    }

}