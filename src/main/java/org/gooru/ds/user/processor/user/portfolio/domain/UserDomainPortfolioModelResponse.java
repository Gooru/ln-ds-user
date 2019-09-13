package org.gooru.ds.user.processor.user.portfolio.domain;

import java.util.Map;


/**
 * @author mukul@gooru
 */
public class UserDomainPortfolioModelResponse {

  private Map<String, Object> items;

  public Map<String, Object> getItems() {
    return items;
  }

  public void setItems(Map<String, Object> userItems) {
    this.items = userItems;
  }

}
