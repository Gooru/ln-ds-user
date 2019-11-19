package org.gooru.ds.user.processor.user.portfolio.content.items;

/**
 * @author renuka
 */
public class UserPortfolioMasteryStatusModel {
  
  private String id;
  private String gutCode;
  private String gutDisplayCode;
  private Integer status;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getGutCode() {
    return gutCode;
  }
  public void setGutCode(String gutCode) {
    this.gutCode = gutCode;
  }
  public String getGutDisplayCode() {
    return gutDisplayCode;
  }
  public void setGutDisplayCode(String gutDisplayCode) {
    this.gutDisplayCode = gutDisplayCode;
  }
  public Integer getStatus() {
    return status;
  }
  public void setStatus(Integer status) {
    this.status = status;
  }
}
