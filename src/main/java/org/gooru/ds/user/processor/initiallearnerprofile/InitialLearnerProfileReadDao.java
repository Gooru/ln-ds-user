package org.gooru.ds.user.processor.initiallearnerprofile;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 * 
 */
interface InitialLearnerProfileReadDao {
	
	 //1472 | K12.MA          | fe3c31a1-8419-4c32-ba66-a623428ef354 | K12.MA-MA3-MD-B.02     |      4 
	
    @Mapper(InitialLearnerProfileReadModelMapper.class)
    @SqlQuery("select tx_subject_code, tx_grade, gut_code, status, profile_source from grade_competency_profile where "
    		+ "tx_subject_code = :subjectCode and grade_competency_profile.grade_seq < "
    		+ "(select grade_seq from grade_competency_profile where tx_grade = :grade LIMIT 1)")
    List<InitialLearnerProfileReadModel> fetchBaseUserProfile (
        @BindBean InitialLearnerProfileReadCommand.BaseUserProfileCommandBean bean, @Bind("grade") String grade);

}
