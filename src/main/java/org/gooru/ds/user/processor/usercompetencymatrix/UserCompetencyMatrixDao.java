package org.gooru.ds.user.processor.usercompetencymatrix;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 17/1/18.
 */
interface UserCompetencyMatrixDao {

    @Mapper(UserCompetencyMatrixModelMapper.class)
    @SqlQuery("select cm.tx_course_code, cm.tx_domain_code, cm.tx_comp_code, cm.tx_comp_name, cm.tx_comp_desc, cm"
                  + ".tx_comp_student_desc, cm.tx_comp_seq, ucm.status from competency_matrix cm left join "
                  + "learner_profile_competency_status ucm on  cm.tx_subject_code = ucm.tx_subject_code and cm.tx_comp_code = "
                  + "ucm.gut_code and ucm.user_id = :user where cm.tx_subject_code = :subject order by cm"
                  + ".tx_course_code, cm.tx_domain_code, cm.tx_comp_seq asc")
    List<UserCompetencyMatrixModel> fetchUserCompetencyMatrix(
        @BindBean UserCompetencyMatrixCommand.UserCompetencyMatrixCommandBean userCompetencyMatrixCommandBean);

}
