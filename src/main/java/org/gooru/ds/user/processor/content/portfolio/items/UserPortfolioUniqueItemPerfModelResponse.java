package org.gooru.ds.user.processor.content.portfolio.items;

import java.util.Map;

/**
 * @author renuka
 */
public class UserPortfolioUniqueItemPerfModelResponse {

  private Map<String, Object> items;

  public Map<String, Object> getItems() {
    return items;
  }

  public void getItems(Map<String, Object> userItems) {
    this.items = userItems;
  }

}
