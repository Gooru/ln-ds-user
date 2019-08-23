package org.gooru.ds.user.processor.user.portfolio.competency;

import java.io.Serializable;
import java.util.Map;


/**
 * @author mukul@gooru
 */
public class UserCompetencyPortfolioModelResponse implements Serializable {

  private final static long serialVersionUID = 3637780095721677754L;

  private Map<String, Object> items;

  public Map<String, Object> getItems() {
    return items;
  }

  public void setItems(Map<String, Object> userItems) {
    this.items = userItems;
  }

}
