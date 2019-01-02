package org.gooru.ds.user.processor.baselearnerprofile;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 * 
 */
interface LearnerProfileReadDao {

  @Mapper(LearnerProfileReadModelMapper.class)
  @SqlQuery("select tx_subject_code, user_id, gut_code, status from learner_profile_competency_status where "
      + "tx_subject_code = :subjectCode and user_id = :user")
  List<LearnerProfileReadModel> fetchCurrentLearnerProfile(
      @BindBean LearnerProfileBaselineUpdateCommand.LearnerProfileReadCommandBean bean);

}
