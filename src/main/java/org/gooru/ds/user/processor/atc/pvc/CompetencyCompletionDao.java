package org.gooru.ds.user.processor.atc.pvc;

import java.util.List;
import org.gooru.ds.user.app.jdbi.PGArray;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


/**
 * @author mukul@gooru
 */
interface CompetencyCompletionDao {

  @Mapper(CompetencyCompletionModelMapper.class)
  @SqlQuery("select cm.tx_domain_code, cm.tx_comp_code, cm.tx_comp_seq, ucm.status from domain_competency_matrix cm "
      + "left join learner_profile_competency_status ucm on  cm.tx_subject_code = ucm.tx_subject_code and "
      + "cm.tx_comp_code = ucm.gut_code and ucm.user_id = :user where "
      + "cm.tx_subject_code = :subjectCode and cm.tx_comp_code = ANY(:competencyCodes) order by cm.tx_domain_code, cm.tx_comp_seq asc")
  List<CompetencyCompletionModel> fetchCompetencyCompletion(@Bind("user") String user,
      @Bind("subjectCode") String subjectCode,
      @Bind("competencyCodes") PGArray<String> competencyCodes);

  @Mapper(CompetencyCompletionModelMapper.class)
  @SqlQuery("select distinct(cm.tx_comp_code), cm.tx_domain_code, cm.tx_comp_seq, "
      + " (SELECT DISTINCT ON (lpcs.gut_code) FIRST_VALUE(lpcs.status) OVER (PARTITION BY lpcs.gut_code ORDER BY lpcs.updated_at desc) as status"
      + " FROM learner_profile_competency_status_ts lpcs where lpcs.user_id = :user and lpcs.gut_code = ucm.gut_code and extract(month from lpcs.updated_at) <= :month"
      + " and extract(year from lpcs.updated_at) <= :year) as status from domain_competency_matrix cm left join learner_profile_competency_status_ts "
      + " ucm on cm.tx_subject_code = ucm.tx_subject_code and cm.tx_comp_code = ucm.gut_code and ucm.user_id = :user and extract(month from"
      + " ucm.updated_at) <= :month and extract(year from ucm.updated_at) <= :year where cm.tx_subject_code = :subjectCode and cm.tx_comp_code = ANY(:competencyCodes)"
      + " order by cm.tx_domain_code," + " cm.tx_comp_seq asc")
  List<CompetencyCompletionModel> fetchCompetencyCompletionMonthBased(@Bind("user") String user,
      @Bind("subjectCode") String subjectCode,
      @Bind("competencyCodes") PGArray<String> competencyCodes, @Bind("month") Integer month,
      @Bind("year") Integer year);



}
