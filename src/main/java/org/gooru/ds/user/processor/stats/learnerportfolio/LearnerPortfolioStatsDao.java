package org.gooru.ds.user.processor.stats.learnerportfolio;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


interface LearnerPortfolioStatsDao {

  @Mapper(LearnerPortfolioStatsModelMapper.class)
  @SqlQuery("select count(1) as collection_count, tx_subject_code, ts.title as tx_subject_name,  tsc.code as tx_classification_code, "
      + "tsc.title as tx_classification_name, collection_type, tsc.sequence_id as classification_seq, ts.sequence_id as subject_seq from domain_competency_matrix dcm inner join tx_subjects ts on  ts.id = dcm.tx_subject_code inner join "
      + "taxonomy_subject_classification tsc on tsc.id = ts.taxonomy_subject_classification_id left join learner_profile_competency_evidence lpce   on dcm.tx_comp_code = gut_code and ts.is_visible = true and  user_id = :user  and lpce.updated_at < :toDate "
      + "group by tx_subject_code, ts.title,tsc.code, tsc.title, collection_type, tsc.sequence_id, ts.sequence_id order by tsc.sequence_id, ts.sequence_id")
  List<LearnerPortfolioStatsModel> fetchLearnerPortfolioStats(
      @BindBean LearnerPortfolioStatsCommand.LearnerPortfolioStatsCommandBean learnerPortfolioStatsCommandBean);
}
