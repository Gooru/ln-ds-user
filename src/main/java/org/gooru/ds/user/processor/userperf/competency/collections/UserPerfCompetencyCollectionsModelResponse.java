package org.gooru.ds.user.processor.userperf.competency.collections;

import java.util.List;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsModel;

public class UserPerfCompetencyCollectionsModelResponse {

  private List<UserPerfCompetencyCollectionsModel> collections;

  public List<UserPerfCompetencyCollectionsModel> getCollections() {
    return collections;
  }

  public void setCollections(List<UserPerfCompetencyCollectionsModel> collections) {
    this.collections = collections;
  }

}
