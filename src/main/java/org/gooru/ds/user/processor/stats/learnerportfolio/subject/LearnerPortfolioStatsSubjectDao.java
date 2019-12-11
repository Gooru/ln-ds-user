package org.gooru.ds.user.processor.stats.learnerportfolio.subject;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


interface LearnerPortfolioStatsSubjectDao {

  @Mapper(LearnerPortfolioStatsSubjectModelMapper.class)
  @SqlQuery("select count(1) as collection_count, td.tx_domain_code, td.tx_domain_name, td.tx_domain_seq, collection_type from domain_competency_matrix dcm  inner join "
      + "tx_domains td on  td.tx_subject_code = dcm.tx_subject_code and td.tx_domain_code = dcm.tx_domain_code left join learner_profile_competency_evidence lpce   on "
      + "dcm.tx_comp_code = gut_code  and  user_id = :user  and lpce.updated_at < :toDate where dcm.tx_subject_code = :subject "
      + "group by td.tx_domain_code, td.tx_domain_name, td.tx_domain_seq, collection_type")
  List<LearnerPortfolioStatsSubjectModel> fetchLearnerPortfolioStatsSubject(
      @BindBean LearnerPortfolioStatsSubjectCommand.LearnerPortfolioStatsSubjectCommandBean learnerPortfolioStatsSubjectCommandBean);
}
