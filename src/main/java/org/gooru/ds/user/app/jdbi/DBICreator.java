package org.gooru.ds.user.app.jdbi;

import javax.sql.DataSource;
import org.gooru.ds.user.app.components.DataSourceRegistry;
import org.skife.jdbi.v2.DBI;

/**
 * @author ashish on 1/10/18.
 */
public final class DBICreator {

  private DBICreator() {
    throw new AssertionError();
  }

  public static DBI getDbiForDefaultDS() {
    return createDBI(DataSourceRegistry.getInstance().getDefaultDataSource());
  }

  public static DBI getDbiForCoreDS() {
    return createDBI(DataSourceRegistry.getInstance().getCoreDataSource());
  }

  public static DBI getDbiForAnalyticsDS() {
    return createDBI(DataSourceRegistry.getInstance().getAnalyticsDataSource());
  }

  private static DBI createDBI(DataSource dataSource) {
    DBI dbi = new DBI(dataSource);
    dbi.registerArgumentFactory(new PostgresIntegerArrayArgumentFactory());
    dbi.registerArgumentFactory(new PostgresStringArrayArgumentFactory());
    dbi.registerArgumentFactory(new PostgresUUIDArrayArgumentFactory());
    return dbi;
  }

}
