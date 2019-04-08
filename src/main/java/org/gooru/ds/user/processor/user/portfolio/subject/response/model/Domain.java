
package org.gooru.ds.user.processor.user.portfolio.subject.response.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author mukul@gooru
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "domainCode",
    "competencies"
})
public class Domain implements Serializable
{

    @JsonProperty("domainCode")
    private String domainCode;
    @JsonProperty("competencies")
    private List<Competency> competencies = null;
    private final static long serialVersionUID = 872781540541845251L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Domain() {
    }

    /**
     * 
     * @param competencies
     * @param domainCode
     */
    public Domain(String domainCode, List<Competency> competencies) {
        super();
        this.domainCode = domainCode;
        this.competencies = competencies;
    }

    @JsonProperty("domainCode")
    public String getDomainCode() {
        return domainCode;
    }

    @JsonProperty("domainCode")
    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }

    public Domain withDomainCode(String domainCode) {
        this.domainCode = domainCode;
        return this;
    }

    @JsonProperty("competencies")
    public List<Competency> getCompetencies() {
        return competencies;
    }

    @JsonProperty("competencies")
    public void setCompetencies(List<Competency> competencies) {
        this.competencies = competencies;
    }

    public Domain withCompetencies(List<Competency> competencies) {
        this.competencies = competencies;
        return this;
    }
    
    public void addCompetencies(Competency competency) {
      if( this.competencies == null ){
          this.competencies = new ArrayList<>();
      }
      this.competencies.add(competency);
  }


}