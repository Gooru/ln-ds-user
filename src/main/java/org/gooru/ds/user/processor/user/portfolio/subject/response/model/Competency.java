
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
    "competencyCode",
    "collections"
})
public class Competency implements Serializable
{

    @JsonProperty("competencyCode")
    private String competencyCode;
    @JsonProperty("collections")
    private List<Collection> collections = null;
    private final static long serialVersionUID = -2403063262881369516L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Competency() {
    }

    /**
     * 
     * @param competencyCode
     * @param collections
     */
    public Competency(String competencyCode, List<Collection> collections) {
        super();
        this.competencyCode = competencyCode;
        this.collections = collections;
    }

    @JsonProperty("competencyCode")
    public String getCompetencyCode() {
        return competencyCode;
    }

    @JsonProperty("competencyCode")
    public void setCompetencyCode(String competencyCode) {
        this.competencyCode = competencyCode;
    }

    public Competency withCompetencyCode(String competencyCode) {
        this.competencyCode = competencyCode;
        return this;
    }

    @JsonProperty("collections")
    public List<Collection> getCollections() {
        return collections;
    }

    @JsonProperty("collections")
    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public Competency withCollections(List<Collection> collections) {
        this.collections = collections;
        return this;
    }
    
    public void addCollection(Collection collection) {
      if( this.collections == null ){
          this.collections = new ArrayList<>();
      }
      this.collections.add(collection);
  }


}