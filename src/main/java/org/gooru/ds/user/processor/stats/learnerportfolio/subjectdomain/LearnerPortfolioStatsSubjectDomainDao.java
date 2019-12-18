package org.gooru.ds.user.processor.stats.learnerportfolio.subjectdomain;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


interface LearnerPortfolioStatsSubjectDomainDao {

  @Mapper(LearnerPortfolioStatsSubjectDomainModelMapper.class)
  @SqlQuery("select count(1) as collection_count, dcm.tx_comp_code, dcm.tx_comp_name, dcm.tx_comp_seq, collection_type from domain_competency_matrix dcm "
      + "left join learner_profile_competency_evidence lpce  on dcm.tx_comp_code = gut_code and  user_id = :user  and lpce.updated_at < :toDate where  "
      + "dcm.tx_subject_code = :subject and  dcm.tx_domain_code = :domain group by  tx_comp_code, tx_comp_name, tx_comp_seq, collection_type")
  List<LearnerPortfolioStatsSubjectDomainModel> fetchLearnerPortfolioStatsSubjectDomain(
      @BindBean LearnerPortfolioStatsSubjectDomainCommand.LearnerPortfolioStatsSubjectDomainCommandBean learnerPortfolioStatsSubjectDomainCommandBean);
}
