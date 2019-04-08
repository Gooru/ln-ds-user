
package org.gooru.ds.user.processor.user.portfolio.content.response.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "activityType",
    "activities"
})
public class Content implements Serializable
{

    @JsonProperty("activityType")
    private String activityType;
    @JsonProperty("activities")
    private List<Activity> activities = null;
    private final static long serialVersionUID = 5190491815844142181L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Content() {
    }

    /**
     * 
     * @param activities
     * @param activityType
     */
    public Content(String activityType, List<Activity> activities) {
        super();
        this.activityType = activityType;
        this.activities = activities;
    }

    @JsonProperty("activityType")
    public String getActivityType() {
        return activityType;
    }

    @JsonProperty("activityType")
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Content withActivityType(String activityType) {
        this.activityType = activityType;
        return this;
    }

    @JsonProperty("activities")
    public List<Activity> getActivities() {
        return activities;
    }

    @JsonProperty("activities")
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Content withActivities(List<Activity> activities) {
        this.activities = activities;
        return this;
    }
    
    public void addActivity(Activity activity) {
      if (this.activities == null) {
        activities = new ArrayList<>();        
      }      
      this.activities.add(activity);      
  }

}