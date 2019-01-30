
package org.gooru.ds.user.processor.domain.report;

/**
 * @author szgooru Created On 30-Jan-2019
 */
public class DomainModel {
  private String domainCode;
  private String domainName;
  private Integer domainSeq;

  public String getDomainCode() {
    return domainCode;
  }

  public void setDomainCode(String domainCode) {
    this.domainCode = domainCode;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  public Integer getDomainSeq() {
    return domainSeq;
  }

  public void setDomainSeq(Integer domainSeq) {
    this.domainSeq = domainSeq;
  }
}
