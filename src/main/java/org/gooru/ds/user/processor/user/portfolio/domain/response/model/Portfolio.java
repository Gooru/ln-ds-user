
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
    "competencies"
})
public class Portfolio implements Serializable
{

    @JsonProperty("competencies")
    private List<Competency> competencies = null;
    private final static long serialVersionUID = -4499029740266815372L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Portfolio() {
    }

    /**
     * 
     * @param competencies
     */
    public Portfolio(List<Competency> competencies) {
        super();
        this.competencies = competencies;
    }

    @JsonProperty("competencies")
    public List<Competency> getCompetencies() {
        return competencies;
    }

    @JsonProperty("competencies")
    public void setCompetencies(List<Competency> competencies) {
        this.competencies = competencies;
    }

    public Portfolio withCompetencies(List<Competency> competencies) {
        this.competencies = competencies;
        return this;
    }

}