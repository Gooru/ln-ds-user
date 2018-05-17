package org.gooru.ds.user.processor.userdomaincompetencymatrix;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 13/2/18.
 */
interface UserDomainCompetencyMatrixDao {

    @Mapper(UserDomainCompetencyMatrixModelMapper.class)
    @SqlQuery("select cm.tx_domain_code, cm.tx_comp_code, cm.tx_comp_name, cm.tx_comp_desc, "
                  + " cm.tx_comp_student_desc, cm.tx_comp_seq, ucm.status from domain_competency_matrix cm left join "
                  + " learner_profile_competency_status ucm on  cm.tx_subject_code = ucm.tx_subject_code and "
                  + " cm.tx_comp_code = ucm.gut_code and ucm.user_id = :user where cm.tx_subject_code = :subject "
                  + " order by cm.tx_domain_code, cm.tx_comp_seq asc")
    List<UserDomainCompetencyMatrixModel> fetchUserDomainCompetencyMatrix(@BindBean
        UserDomainCompetencyMatrixCommand.UserCourseCompetencyMatrixCommandBean userCompetencyMatrixCommandBean);

}
