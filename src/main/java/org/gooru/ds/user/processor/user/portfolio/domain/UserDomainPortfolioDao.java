package org.gooru.ds.user.processor.user.portfolio.domain;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 */
interface UserDomainPortfolioDao {
  
  @SqlQuery("select tx_comp_code from domain_competency_matrix where tx_subject_code = :subjectCode and tx_domain_code = :domainCode")
  List<String> fetchCompetencyCodes( @Bind("subjectCode") String subjectCode, @Bind("domainCode") String domainCode);
  
  @Mapper(UserDomainPortfolioModelMapper.class)
  @SqlQuery("select gut_code, collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, updated_at "
      + " from learner_profile_competency_evidence where gut_code = ANY(:gutCode) and user_id = :user order by updated_at desc")
  List<UserDomainPortfolioModel> fetchCompetencyActivities(@Bind("gutCode") PGArray<String> gutCode, @Bind("user") String user);

//  @Mapper(UserDomainPortfolioModelMapper.class)
//  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source "
//      + " from learner_profile_competency_evidence_ts where status = :status and gut_code = :gutCode and user_id = :user")
//  List<UserDomainPortfolioModel> fetchUserPerfCompetencyCollectionsByGutAndStatus(
//      @BindBean UserDomainPortfolioCommand.UserDomainPortfolioCommandBean userDomainPortfolioCommandBean);

}
