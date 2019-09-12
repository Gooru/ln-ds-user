package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.List;
import io.vertx.core.json.JsonObject;

/**
 * @author renuka
 */
public class CoreCollectionsModel {

  private String id;
  private String title;
  private String type;
  private String subType;
  private String learningObjective;
  private String thumbnail;
  private JsonObject taxonomy;
  private List<?> gutCodes;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getLearningObjective() {
    return learningObjective;
  }

  public void setLearningObjective(String learningObjective) {
    this.learningObjective = learningObjective;
  }

  public JsonObject getTaxonomy() {
    return taxonomy;
  }

  public void setTaxonomy(JsonObject taxonomy) {
    this.taxonomy = taxonomy;
  }
  
  public List<?> getGutCodes() {
    return gutCodes;
  }

  public void setGutCodes(List<?> gutCodes) {
    this.gutCodes = gutCodes;
  }

}
