package org.gooru.ds.user.processor.competency.subjects;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 * 
 */
interface CompetencySubjectListDao {

    @Mapper(CompetencySubjectListModelMapper.class)
    @SqlQuery("select id, title, description, code, standard_framework_id, sequence_id from tx_subjects "
    		+ "where id IN (select DISTINCT(default_taxonomy_subject_id) from tx_subjects where "
    		+ "taxonomy_subject_classification_id = :classificationType and is_visible = true) order by "
    		+ "sequence_id offset :offset limit :limit")
    List<CompetencySubjectListModel> fetchCompetencySubjectList(
        @BindBean CompetencySubjectListCommand.CompetencySubjectListCommandBean bean);
   
}
