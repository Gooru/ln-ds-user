package org.gooru.ds.user.processor.userperf.competency.collections;

import java.util.List;

import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsCommand;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsModel;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsModelMapper;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

interface UserPerfCompetencyCollectionsDao {

	@Mapper(UserPerfCompetencyCollectionsModelMapper.class)
	@SqlQuery("select collection_id, latest_session_id, collection_score, collection_type "
			+ " from content_competency_evidence where competency_code = :competencyCode and user_id = :user")
	List<UserPerfCompetencyCollectionsModel> fetchUserPerfCompetencyCollections(
			@BindBean UserPerfCompetencyCollectionsCommand.UserPerfCompetencyCollectionsCommandBean userPerfCompetencyCollectionsCommandBean);

	@Mapper(UserPerfCompetencyCollectionsModelMapper.class)
	@SqlQuery("select collection_id, latest_session_id, collection_score, collection_type "
			+ " from learner_profile_competency_evidence where gut_code = :gutCode and user_id = :user")
	List<UserPerfCompetencyCollectionsModel> fetchUserPerfCompetencyCollectionsByGut(
			@BindBean UserPerfCompetencyCollectionsCommand.UserPerfCompetencyCollectionsCommandBean userPerfCompetencyCollectionsCommandBean);
	
	@Mapper(UserPerfCompetencyCollectionsModelMapper.class)
	@SqlQuery("select collection_id, latest_session_id, collection_score, collection_type "
			+ " from learner_profile_competency_evidence_ts where status = :status and gut_code = :gutCode and user_id = :user")
	List<UserPerfCompetencyCollectionsModel> fetchUserPerfCompetencyCollectionsByGutAndStatus(
			@BindBean UserPerfCompetencyCollectionsCommand.UserPerfCompetencyCollectionsCommandBean userPerfCompetencyCollectionsCommandBean);
}
