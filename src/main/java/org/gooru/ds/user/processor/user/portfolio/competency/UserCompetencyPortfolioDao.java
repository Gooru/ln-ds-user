package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 */
interface UserCompetencyPortfolioDao {
  
  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, updated_at "
      + " from learner_profile_competency_evidence where gut_code = :gutCode and user_id = :user order by updated_at desc")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyCollectionsByGut(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, updated_at "
      + " from learner_profile_competency_evidence_ts where status = :status and gut_code = :gutCode and user_id = :user order by updated_at desc")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyCollectionsByGutAndStatus(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

}
