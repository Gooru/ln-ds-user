package org.gooru.ds.user.processor.usercoursecompetencymatrix;

import java.sql.Timestamp;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 13/2/18.
 */
interface UserCourseCompetencyMatrixDao {

  @Mapper(UserCourseCompetencyMatrixModelMapper.class)
  @SqlQuery("select cm.tx_course_code, cm.tx_comp_code, cm.tx_comp_name, cm.tx_comp_desc, "
      + " cm.tx_comp_student_desc, cm.tx_comp_seq, ucm.status from course_competency_matrix cm left join "
      + " learner_profile_competency_status ucm on  cm.tx_subject_code = ucm.tx_subject_code and "
      + " cm.tx_comp_code = ucm.gut_code and ucm.user_id = :user where cm.tx_subject_code = :subject "
      + " order by cm.tx_course_code, cm.tx_comp_seq asc")
  List<UserCourseCompetencyMatrixModel> fetchUserCourseCompetencyMatrix(
      @BindBean UserCourseCompetencyMatrixCommand.UserCourseCompetencyMatrixCommandBean userCompetencyMatrixCommandBean);

  @SqlQuery("SELECT MAX(updated_at) FROM learner_profile_competency_status_ts WHERE user_id = :user AND tx_subject_code = :subject AND"
      + " updated_at < :toDate")
  Timestamp fetchLastUpdatedTime(
      @BindBean UserCourseCompetencyMatrixCommand.UserCourseCompetencyMatrixCommandBean userCompetencyMatrixCommandBean);

  @Mapper(UserCourseCompetencyMatrixModelMapper.class)
  @SqlQuery("select distinct(cm.tx_comp_code), cm.tx_course_code, cm.tx_comp_name, cm.tx_comp_desc, cm.tx_comp_student_desc, cm.tx_comp_seq,"
      + " (SELECT DISTINCT ON (lpcs.gut_code) FIRST_VALUE(lpcs.status) OVER (PARTITION BY lpcs.gut_code ORDER BY lpcs.updated_at desc) as"
      + " status FROM learner_profile_competency_status_ts lpcs where lpcs.user_id = :user and lpcs.gut_code = ucm.gut_code and"
      + " lpcs.updated_at < :toDate) as status from course_competency_matrix cm left"
      + " join learner_profile_competency_status_ts ucm on  cm.tx_subject_code = ucm.tx_subject_code and cm.tx_comp_code = ucm.gut_code and"
      + " ucm.user_id = :user AND ucm.updated_at < :toDate where"
      + " cm.tx_subject_code = :subject order by cm.tx_course_code, cm.tx_comp_seq asc")
  List<UserCourseCompetencyMatrixModel> fetchUserCourseCompetencyMatrixTillMonth(
      @BindBean UserCourseCompetencyMatrixCommand.UserCourseCompetencyMatrixCommandBean userCompetencyMatrixCommandBean);

}
