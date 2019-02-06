
package org.gooru.ds.user.processor.domain.report.utils;

/**
 * @author szgooru Created On 31-Jan-2019
 */
public final class DomainReportResponseConstants {

  private DomainReportResponseConstants() {
    throw new AssertionError();
  }

  public static final class DesktopResponseConstants {

    public static final String MEMBER_COUNT = "member_count";
    public static final String DOMAINS = "domains";
    public static final String DOMAIN = "domain";
    public static final String TX_DOMAIN_CODE = "tx_domain_code";
    public static final String TX_DOMAIN_NAME = "tx_domain_name";
    public static final String TX_DOMAIN_SEQ = "tx_domain_seq";
    public static final String DOMAIN_AVERAGE_COMPLETIONS = "average_completions";

    public static final String COMPETENCIES = "competencies";
    public static final String TX_COMP_CODE = "tx_comp_code";
    public static final String TX_COMP_NAME = "tx_comp_name";
    public static final String TX_COMP_DESC = "tx_comp_desc";
    public static final String COMPLETIONS = "completions";

    public static final String USERS = "users";
    public static final String USER = "user";
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String THUMBNAIL = "thumbnail";
    public static final String STATUS = "status";
    public static final String SCORE = "score";

    private DesktopResponseConstants() {
      throw new AssertionError();
    }
  }

  public static final class MobileResponseConstants {

    public static final String MEMBER_COUNT = "cmc";
    public static final String DOMAINS = "dmns";
    public static final String DOMAIN = "d";
    public static final String TX_DOMAIN_CODE = "dc";
    public static final String TX_DOMAIN_NAME = "dn";
    public static final String TX_DOMAIN_SEQ = "seq";
    public static final String DOMAIN_AVERAGE_COMPLETIONS = "avg";

    public static final String COMPETENCIES = "tx";
    public static final String TX_COMP_CODE = "gc";
    public static final String TX_COMP_NAME = "nm";
    public static final String TX_COMP_DESC = "ds";
    public static final String COMPLETIONS = "pc";

    public static final String USERS = "us";
    public static final String USER = "u";
    public static final String ID = "id";
    public static final String FIRST_NAME = "fn";
    public static final String LAST_NAME = "ln";
    public static final String THUMBNAIL = "th";
    public static final String STATUS = "st";
    public static final String SCORE = "sc";

    private MobileResponseConstants() {
      throw new AssertionError();
    }
  }

}
