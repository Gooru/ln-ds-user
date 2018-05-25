package org.gooru.ds.user.processor.userperf.competency.assessments;

import java.util.List;

import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsCommand;
import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsModel;
import org.gooru.ds.user.processor.userperf.competency.assessments.UserPerfCompetencyAssessmentsModelMapper;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 */
interface UserPerfCompetencyAssessmentsDao {

/*    @Mapper(UserPerfCompetencyAssessmentsModelMapper.class)
    @SqlQuery(
        "select collection_id, collection_title, latest_session_id, collection_time_spent, collection_score, "
            + "collection_average_reaction"
            + " from competency_collection_performance where "
            + " competency_id = :competencyId "            
            + " and collection_type = 'assessment' and user_id = :user")
    List<UserPerfCompetencyAssessmentsModel> fetchUserPerfCompetencyAssessments(
        @BindBean UserPerfCompetencyAssessmentsCommand.UserPerfCompetencyAssessmentsCommandBean userPerfCompetencyAssessmentsCommandBean);*/
	
    @Mapper(UserPerfCompetencyAssessmentsModelMapper.class)
    @SqlQuery(
        "select collection_id, latest_session_id, collection_score from learner_profile_competency_evidence where "
            + " competency_id = :competencyId and collection_type = 'assessment' and user_id = :user")
    List<UserPerfCompetencyAssessmentsModel> fetchUserPerfCompetencyAssessments(
        @BindBean UserPerfCompetencyAssessmentsCommand.UserPerfCompetencyAssessmentsCommandBean userPerfCompetencyAssessmentsCommandBean);

}
