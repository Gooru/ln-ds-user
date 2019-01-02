package org.gooru.ds.user.processor.user.course.competency.report;

import java.util.List;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportCommand.UserCourseCompetencyReportCommandBean;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author szgooru on 17-Jul-2018
 */
public interface UserCourseCompetencyReportDao {

  @Mapper(UserCourseCompetencyReportModelMapper.class)
  @SqlQuery("select distinct(cm.tx_comp_code), cm.tx_domain_code, cm.tx_comp_name, cm.tx_comp_desc, cm.tx_comp_student_desc, cm.tx_comp_seq,"
      + " (SELECT DISTINCT ON (lpcs.gut_code) FIRST_VALUE(lpcs.status) OVER (PARTITION BY lpcs.gut_code ORDER BY lpcs.updated_at desc) as"
      + " status FROM learner_profile_competency_status_ts lpcs where lpcs.user_id = :studentId and lpcs.gut_code = ucm.gut_code and extract(month"
      + " from lpcs.updated_at) <= :toMonth and extract(year from lpcs.updated_at) <= :toYear) as status from domain_competency_matrix cm left"
      + " join learner_profile_competency_status_ts ucm on cm.tx_subject_code = ucm.tx_subject_code and cm.tx_comp_code = ucm.gut_code and"
      + " ucm.user_id = :studentId and extract(month from ucm.updated_at) <= :toMonth and extract(year from ucm.updated_at) <= :toYear where"
      + " cm.tx_subject_code = :subject order by cm.tx_domain_code, cm.tx_comp_seq asc")
  List<UserCourseCompetencyReportModel> fetchUserDomainCompetencyMatrixCumulative(
      @BindBean UserCourseCompetencyReportCommandBean bean);

  @Mapper(UserCourseCompetencyReportModelMapper.class)
  @SqlQuery("select distinct(cm.tx_comp_code), cm.tx_domain_code, cm.tx_comp_name, cm.tx_comp_desc, cm.tx_comp_student_desc, cm.tx_comp_seq,"
      + " (SELECT DISTINCT ON (lpcs.gut_code) FIRST_VALUE(lpcs.status) OVER (PARTITION BY lpcs.gut_code ORDER BY lpcs.updated_at desc) as"
      + " status FROM learner_profile_competency_status_ts lpcs where lpcs.user_id = :studentId and lpcs.gut_code = ucm.gut_code and extract(month"
      + " from lpcs.updated_at) >= :fromMonth and extract(year from lpcs.updated_at) >= :fromYear and extract(month from lpcs.updated_at) <="
      + " :toMonth and extract(year from lpcs.updated_at) <= :toYear) as status from domain_competency_matrix cm left join"
      + " learner_profile_competency_status_ts ucm on cm.tx_subject_code = ucm.tx_subject_code and cm.tx_comp_code = ucm.gut_code and"
      + " ucm.user_id = :studentId and extract(month from ucm.updated_at) >= :fromMonth and extract(year from ucm.updated_at) >= :fromYear and"
      + " extract(month from ucm.updated_at) <= :toMonth and extract(year from ucm.updated_at) <= :toYear where cm.tx_subject_code = :subject"
      + " order by cm.tx_domain_code, cm.tx_comp_seq asc")
  List<UserCourseCompetencyReportModel> fetchUserDomainCompetencyMatrixWindow(
      @BindBean UserCourseCompetencyReportCommandBean bean);

  @Mapper(DomainCompetenciesModelMapper.class)
  @SqlQuery("SELECT dcm.tx_comp_code, dcm.tx_domain_code, dcm.tx_comp_name, dcm.tx_comp_desc, dcm.tx_comp_student_desc, dcm.tx_comp_seq,"
      + " d.tx_domain_name, d.tx_domain_seq FROM domain_competency_matrix dcm, tx_domains d WHERE dcm.tx_subject_code = :subjectCode AND"
      + " d.tx_subject_code = dcm.tx_subject_code AND d.tx_domain_code = dcm.tx_domain_code ORDER BY d.tx_domain_seq, dcm.tx_comp_seq")
  List<DomainCompetenciesModel> fetchAllDomainCompetencies(@Bind("subjectCode") String subjectCode);



}
