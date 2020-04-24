package org.gooru.ds.user.processor.competency.subjects;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 * 
 */
interface CompetencySubjectListDao {

  @Mapper(CompetencySubjectListModelMapper.class)
  @SqlQuery("SELECT * from tx_subjects WHERE taxonomy_subject_classification_id = :classificationType AND "
      + "standard_framework_id is null AND default_taxonomy_subject_id is null and  is_visible = true AND (tenant_visibility = true OR id = "
      + "ANY(:ids::varchar[])) ORDER BY sequence_id")
  List<CompetencySubjectListModel> fetchCompetencySubjectList(
      @BindBean CompetencySubjectListCommand.CompetencySubjectListCommandBean bean,
      @Bind("ids") PGArray<String> ids);

  @Mapper(CompetencySubjectListModelMapper.class)
  @SqlQuery("SELECT * from tx_subjects WHERE taxonomy_subject_classification_id = :classificationType AND "
      + "standard_framework_id is null AND default_taxonomy_subject_id is null and  is_visible = true AND id = ANY(:ids::varchar[]) "
      + "ORDER BY sequence_id")
  List<CompetencySubjectListModel> fetchCompetencySubjectListByIds(
      @BindBean CompetencySubjectListCommand.CompetencySubjectListCommandBean bean,
      @Bind("ids") PGArray<String> ids);



}
