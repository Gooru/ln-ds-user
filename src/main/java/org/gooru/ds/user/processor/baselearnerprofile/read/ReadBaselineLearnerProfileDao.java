package org.gooru.ds.user.processor.baselearnerprofile.read;

import java.sql.Timestamp;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru on 20-Jul-2018
 */
public interface ReadBaselineLearnerProfileDao {

	@Mapper(ReadBaselineLearnerProfileModelMapper.class)
	@SqlQuery("SELECT cm.tx_domain_code, cm.tx_comp_code, cm.tx_comp_name, cm.tx_comp_desc, cm.tx_comp_student_desc, cm.tx_comp_seq, blp.status FROM"
			+ " domain_competency_matrix cm LEFT JOIN baseline_learner_profile blp ON cm.tx_subject_code = blp.tx_subject_code AND "
			+ " cm.tx_comp_code = blp.gut_code AND blp.class_id = :classId AND blp.course_id = :courseId AND blp.user_id = :user WHERE"
			+ " cm.tx_subject_code = :subject ORDER BY cm.tx_domain_code, cm.tx_comp_seq ASC")
	List<ReadBaselineLearnerProfileModel> readBaselineLearnerProfile(
			@BindBean ReadBaselineLearnerProfileCommand.ReadBaselineLearnerProfileCommandBean bean);

	@Mapper(ReadBaselineLearnerProfileModelMapper.class)
	@SqlQuery("SELECT cm.tx_domain_code, cm.tx_comp_code, cm.tx_comp_name, cm.tx_comp_desc, cm.tx_comp_student_desc, cm.tx_comp_seq, blp.status FROM"
			+ " domain_competency_matrix cm LEFT JOIN baseline_learner_profile blp ON cm.tx_subject_code = blp.tx_subject_code AND "
			+ " cm.tx_comp_code = blp.gut_code AND blp.class_id IS NULL AND blp.course_id = :courseId AND blp.user_id = :user WHERE"
			+ " cm.tx_subject_code = :subject ORDER BY cm.tx_domain_code, cm.tx_comp_seq ASC")
	List<ReadBaselineLearnerProfileModel> readBaselineLearnerProfileIL(
			@BindBean ReadBaselineLearnerProfileCommand.ReadBaselineLearnerProfileCommandBean bean);
	
	@SqlQuery("SELECT MAX(created_at) FROM baseline_learner_profile "
        + "WHERE class_id = :classId AND course_id = :courseId AND user_id = :user AND tx_subject_code = :subject")
	Timestamp fetchLastCreatedTimeInClass(
        @BindBean ReadBaselineLearnerProfileCommand.ReadBaselineLearnerProfileCommandBean bean);
	
	@SqlQuery("SELECT MAX(created_at) FROM baseline_learner_profile "
	    + "WHERE course_id = :courseId AND user_id = :user AND class_id IS NULL AND tx_subject_code = :subject")
	Timestamp fetchLastCreatedTimeIL(
            @BindBean ReadBaselineLearnerProfileCommand.ReadBaselineLearnerProfileCommandBean bean);
	
}
