package org.gooru.ds.user.processor.competency.subjects;

import io.vertx.core.json.JsonObject;

public class TenantSubjectPrefModel {

  private JsonObject subjectPref;

  public JsonObject getSubjectPref() {
    return subjectPref;
  }

  public void setSubjectPref(JsonObject subjectPref) {
    this.subjectPref = subjectPref;
  }

}
