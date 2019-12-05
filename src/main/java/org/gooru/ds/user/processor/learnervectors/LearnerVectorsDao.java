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
  LearnerVectorsModel fetchUserVectors(
      @BindBean LearnerVectorsCommand.LearnerVectorsCommandBean userVectorsCommandBean);
  
  @Mapper(LearnerVectorsModelMapper.class)
  @SqlQuery("select avg(authority/1000::real) as  authority, avg(reputation/1000::real) as  reputation, avg(citizenship/1000::real) as "
      + "citizenship, avg(grit/1000::real) as grit, avg(perseverance/1000::real) as perseverance, avg(motivation/1000::real) as "
      + "motivation, avg(self_confidence/1000::real) as self_confidence  from learner_vectors where  user_id = :user "
      + "and tx_subject_code = :subject and updated_at < :toDate")
  LearnerVectorsModel fetchUserVectorsBySubject(
      @BindBean LearnerVectorsCommand.LearnerVectorsCommandBean userVectorsCommandBean);

}
