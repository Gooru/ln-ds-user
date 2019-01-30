
package org.gooru.ds.user.processor.domain.report;

/**
 * @author szgooru Created On 30-Jan-2019
 */
public class DomainModel {
  private String tx_domain_code;
  private String tx_domain_name;
  private Integer tx_domain_seq;

  public String getTx_domain_code() {
    return tx_domain_code;
  }

  public void setTx_domain_code(String tx_domain_code) {
    this.tx_domain_code = tx_domain_code;
  }

  public String getTx_domain_name() {
    return tx_domain_name;
  }

  public void setTx_domain_name(String tx_domain_name) {
    this.tx_domain_name = tx_domain_name;
  }

  public Integer getTx_domain_seq() {
    return tx_domain_seq;
  }

  public void setTx_domain_seq(Integer tx_domain_seq) {
    this.tx_domain_seq = tx_domain_seq;
  }
}
