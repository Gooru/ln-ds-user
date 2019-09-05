package org.gooru.ds.user.processor.user.portfolio.content.items;

/**
 * @author renuka
 */
public class CoreCollectionItemCountsModel {

  private String id;
  private Integer resourceCount;
  private Integer questionCount;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getResourceCount() {
    return resourceCount;
  }

  public void setResourceCount(Integer resourceCount) {
    this.resourceCount = resourceCount;
  }

  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }

}
