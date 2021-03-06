package org.gooru.ds.user.processor.learnervectors;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


interface LearnerVectorsDao {

  @Mapper(LearnerVectorsModelMapper.class)
  @SqlQuery("select avg(authority/1000::real) as  authority, avg(reputation/1000::real) as  reputation, avg(citizenship/1000::real) as "
      + "citizenship, avg(grit/1000::real) as grit, avg(perseverance/1000::real) as perseverance, avg(motivation/1000::real) as "
      + "motivation, avg(self_confidence/1000::real) as self_confidence  from learner_vectors where  user_id = :user "
      + "and updated_at < :toDate")
  LearnerVectorsModel fetchLearnerVectors(
      @BindBean LearnerVectorsCommand.LearnerVectorsCommandBean learnerVectorsCommandBean);
  
  @Mapper(LearnerVectorsModelMapper.class)
  @SqlQuery("select avg(authority/1000::real) as  authority, avg(reputation/1000::real) as  reputation, avg(citizenship/1000::real) as "
      + "citizenship, avg(grit/1000::real) as grit, avg(perseverance/1000::real) as perseverance, avg(motivation/1000::real) as "
      + "motivation, avg(self_confidence/1000::real) as self_confidence  from learner_vectors where  user_id = :user "
      + "and tx_subject_code = :subject and updated_at < :toDate")
  LearnerVectorsModel fetchLearnerVectorsBySubject(
      @BindBean LearnerVectorsCommand.LearnerVectorsCommandBean learnerVectorsCommandBean);
  
  @Mapper(LearnerVectorsModelMapper.class)
  @SqlQuery("select avg(authority/1000::real) as  authority, avg(reputation/1000::real) as  reputation, avg(citizenship/1000::real) as "
      + "citizenship, avg(grit/1000::real) as grit, avg(perseverance/1000::real) as perseverance, avg(motivation/1000::real) as "
      + "motivation, avg(self_confidence/1000::real) as self_confidence  from learner_vectors where  user_id = :user "
      + "and tx_subject_code = :subject and tx_domain_code = :domain and updated_at < :toDate")
  LearnerVectorsModel fetchLearnerVectorsBySubjectDomain(
      @BindBean LearnerVectorsCommand.LearnerVectorsCommandBean learnerVectorsCommandBean);
  
  @Mapper(LearnerVectorsModelMapper.class)
  @SqlQuery("select avg(authority/1000::real) as  authority, avg(reputation/1000::real) as  reputation, avg(citizenship/1000::real) as "
      + "citizenship, avg(grit/1000::real) as grit, avg(perseverance/1000::real) as perseverance, avg(motivation/1000::real) as "
      + "motivation, avg(self_confidence/1000::real) as self_confidence  from learner_vectors where  user_id = :user "
      + "and tx_subject_code = :subject and tx_domain_code = :domain and tx_comp_code = :compCode and updated_at < :toDate")
  LearnerVectorsModel fetchLearnerVectorsBySubjectDomainCompCode(
      @BindBean LearnerVectorsCommand.LearnerVectorsCommandBean learnerVectorsCommandBean);

}
