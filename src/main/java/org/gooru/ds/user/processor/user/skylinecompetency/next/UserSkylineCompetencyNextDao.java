package org.gooru.ds.user.processor.user.skylinecompetency.next;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author mukul@gooru
 */
interface UserSkylineCompetencyNextDao {

	@Mapper(CompetencyModelMapper.class)
	@SqlQuery("select cm.tx_domain_code, cm.tx_comp_code, cm.tx_comp_name, cm.tx_comp_desc, cm.tx_comp_student_desc, cm.tx_comp_seq, ucm.status from"
			+ " domain_competency_matrix cm left join learner_profile_competency_status ucm on  cm.tx_subject_code = ucm.tx_subject_code and "
			+ " cm.tx_comp_code = ucm.gut_code and ucm.user_id = :user where cm.tx_subject_code = :subject order by cm.tx_domain_code,"
			+ " cm.tx_comp_seq asc")
	List<CompetencyModel> fetchUserCompetencyStatus(
			@BindBean UserSkylineCompetencyNextCommand.UserSkylineCompetencyNextCommandBean userSkylineCompetencyNextCommandBean);

}
