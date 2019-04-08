package org.gooru.ds.user.processor.user.portfolio.content.perf.item;

import java.util.List;
import java.util.Map;

/**
 * @author renuka
 */
public class UserPortfolioItemPerfModelResponse {

  private List<Map<String, Object>> items;

  public List<Map<String, Object>> getItems() {
    return items;
  }

  public void getItems(List<Map<String, Object>> items) {
    this.items = items;
  }

}
