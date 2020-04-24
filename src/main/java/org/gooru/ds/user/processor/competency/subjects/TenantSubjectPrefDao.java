package org.gooru.ds.user.processor.competency.subjects;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

interface TenantSubjectPrefDao {

  @Mapper(TenantSubjectPrefModelMapper.class)
  @SqlQuery("SELECT value FROM tenant_setting WHERE id = :tenantId::uuid AND key = 'tx_sub_prefs'")
  TenantSubjectPrefModel fetchSubjectPref(@Bind("tenantId") String tenantId);

}
