package org.gooru.ds.user.processor.user.portfolio.domain;

import java.util.List;


/**
 * @author mukul@gooru
 */
public class UserDomainPortfolioModelResponse {

  private List<UserDomainPortfolioModel> collections;

  public List<UserDomainPortfolioModel> getCollections() {
    return collections;
  }

  public void setCollections(List<UserDomainPortfolioModel> collections) {
    this.collections = collections;
  }

}
