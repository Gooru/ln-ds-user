package org.gooru.ds.user.processor.user.portfolio.subject;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 */
interface UserSubjectPortfolioDao {

  @Mapper(SubjectModelMapper.class)
  @SqlQuery("select tx_comp_code, tx_domain_code from domain_competency_matrix where tx_subject_code = :subjectCode")
  List<SubjectModel> fetchCompetencyCodes(@Bind("subjectCode") String subjectCode);

  @Mapper(UserSubjectPortfolioModelMapper.class)
  @SqlQuery("select gut_code, collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type IN ('assessment', 'assessment-external') AND gut_code = ANY(:gutCode) and user_id = :userId AND updated_at <= :dateUntil "
      + " order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserSubjectPortfolioModel> fetchCompetencyAssessments(
      @Bind("gutCode") PGArray<String> gutCode,
      @BindBean UserSubjectPortfolioCommand.UserSubjectPortfolioCommandBean userSubjectPortfolioCommandBean);

  @Mapper(UserSubjectPortfolioModelMapper.class)
  @SqlQuery("select gut_code, collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type IN ('assessment', 'assessment-external') AND gut_code = ANY(:gutCode) and user_id = :userId AND updated_at BETWEEN :startDate AND :endDate "
      + " order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserSubjectPortfolioModel> fetchCompetencyAssessmentsInDateRange(
      @Bind("gutCode") PGArray<String> gutCode,
      @BindBean UserSubjectPortfolioCommand.UserSubjectPortfolioCommandBean userSubjectPortfolioCommandBean);

  @Mapper(UserSubjectPortfolioModelMapper.class)
  @SqlQuery("select gut_code, collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type IN ('collection', 'collection-external') AND gut_code = ANY(:gutCode) and user_id = :userId AND updated_at <= :dateUntil "
      + " order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserSubjectPortfolioModel> fetchCompetencyCollections(
      @Bind("gutCode") PGArray<String> gutCode,
      @BindBean UserSubjectPortfolioCommand.UserSubjectPortfolioCommandBean userSubjectPortfolioCommandBean);

  @Mapper(UserSubjectPortfolioModelMapper.class)
  @SqlQuery("select gut_code, collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type IN ('collection', 'collection-external') AND gut_code = ANY(:gutCode) and user_id = :userId AND updated_at BETWEEN :startDate AND :endDate "
      + " order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserSubjectPortfolioModel> fetchCompetencyCollectionsInDateRange(
      @Bind("gutCode") PGArray<String> gutCode,
      @BindBean UserSubjectPortfolioCommand.UserSubjectPortfolioCommandBean userSubjectPortfolioCommandBean);

  @Mapper(UserSubjectPortfolioModelMapper.class)
  @SqlQuery("select gut_code, collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type = 'offline-activity' AND gut_code = ANY(:gutCode) and user_id = :userId AND updated_at <= :dateUntil "
      + " order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserSubjectPortfolioModel> fetchCompetencyOfflineActivities(
      @Bind("gutCode") PGArray<String> gutCode,
      @BindBean UserSubjectPortfolioCommand.UserSubjectPortfolioCommandBean userSubjectPortfolioCommandBean);

  @Mapper(UserSubjectPortfolioModelMapper.class)
  @SqlQuery("select gut_code, collection_id, latest_session_id, collection_score, collection_type, class_id, course_id, unit_id, lesson_id, content_source, created_at, updated_at "
      + " from learner_profile_competency_evidence where collection_type = 'offline-activity' AND gut_code = ANY(:gutCode) and user_id = :userId AND updated_at BETWEEN :startDate AND :endDate "
      + " order by updated_at desc OFFSET :offset LIMIT :limit")
  List<UserSubjectPortfolioModel> fetchCompetencyOfflineActivitiesInDateRange(
      @Bind("gutCode") PGArray<String> gutCode,
      @BindBean UserSubjectPortfolioCommand.UserSubjectPortfolioCommandBean userSubjectPortfolioCommandBean);

}
