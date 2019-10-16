package org.gooru.ds.user.processor.user.portfolio.competency;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 */
interface UserCompetencyPortfolioDao {

  // Assessment
  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type IN ('assessment', 'assessment-external') AND gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) <= :dateUntil order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyAssessmentsByGut(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence_ts where collection_type IN ('assessment', 'assessment-external') AND status = :status and gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) <= :dateUntil order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyAssessmentsByGutAndStatus(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  // Assessment in date range
  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type IN ('assessment', 'assessment-external') AND gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) BETWEEN :startDate AND :endDate order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyAssessmentsByGutInDateRange(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence_ts where collection_type IN ('assessment', 'assessment-external') AND status = :status and gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) BETWEEN :startDate AND :endDate order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyAssessmentsByGutAndStatusInDateRange(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  // Collection
  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type IN ('collection', 'collection-external') AND gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) <= :dateUntil order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyCollectionsByGut(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence_ts where collection_type IN ('collection', 'collection-external') AND status = :status and gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) <= :dateUntil order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyCollectionsByGutAndStatus(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  // Collection In Date Range
  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type IN ('collection', 'collection-external') AND gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) BETWEEN :startDate AND :endDate order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyCollectionsByGutInDateRange(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence_ts where collection_type IN ('collection', 'collection-external') AND status = :status and gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) BETWEEN :startDate AND :endDate order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyCollectionsByGutAndStatusInDateRange(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  // Offline Activity
  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type = 'offline-activity' AND gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) <= :dateUntil order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyOAsByGut(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence_ts where collection_type = 'offline-activity' AND status = :status and gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) <= :dateUntil order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyOAsByGutAndStatus(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  // Offline Activity In Date Range
  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type = 'offline-activity' AND gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) BETWEEN :startDate AND :endDate order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyOAsByGutInDateRange(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

  @Mapper(UserCompetencyPortfolioModelMapper.class)
  @SqlQuery("select collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence_ts where collection_type = 'offline-activity' AND status = :status and gut_code = :gutCode and user_id = :user "
      + " AND CAST(updated_at AS DATE) BETWEEN :startDate AND :endDate order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserCompetencyPortfolioModel> fetchUserPerfCompetencyOAsByGutAndStatusInDateRange(
      @BindBean UserCompetencyPortfolioCommand.UserCompetencyPortfolioCommandBean userPerfCompetencyCollectionsCommandBean);

}
