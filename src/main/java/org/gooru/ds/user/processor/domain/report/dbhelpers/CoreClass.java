
package org.gooru.ds.user.processor.domain.report.dbhelpers;

import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 21-Jan-2019
 */
public class CoreClass {

  private Integer gradeCurrent;
  private Integer gradeLowerBound;
  private Integer gradeUpperBound;
  private JsonObject preference;

  public Integer getGradeCurrent() {
    return gradeCurrent;
  }

  public void setGradeCurrent(Integer gradeCurrent) {
    this.gradeCurrent = gradeCurrent;
  }

  public Integer getGradeLowerBound() {
    return gradeLowerBound;
  }

  public void setGradeLowerBound(Integer gradeLowerBound) {
    this.gradeLowerBound = gradeLowerBound;
  }

  public Integer getGradeUpperBound() {
    return gradeUpperBound;
  }

  public void setGradeUpperBound(Integer gradeUpperBound) {
    this.gradeUpperBound = gradeUpperBound;
  }

  public JsonObject getPreference() {
    return preference;
  }

  public void setPreference(JsonObject preference) {
    this.preference = preference;
  }

}
