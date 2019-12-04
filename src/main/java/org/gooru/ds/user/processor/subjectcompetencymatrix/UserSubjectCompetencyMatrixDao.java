package org.gooru.ds.user.processor.subjectcompetencymatrix;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


interface UserSubjectCompetencyMatrixDao {

  @Mapper(SubjectCompetencyMatrixModelMapper.class)
  @SqlQuery("select cm.tx_subject_code, txs.title as tx_subject_name, txs.taxonomy_subject_classification_id as tx_classification_id,  "
      + "COALESCE(ucm.status, 0, ucm.status) as status, count(1) as competency_count from domain_competency_matrix cm inner join "
      + "tx_subjects txs on txs.id = cm.tx_subject_code left join learner_profile_competency_status ucm on  "
      + "cm.tx_subject_code = ucm.tx_subject_code and cm.tx_comp_code = ucm.gut_code and ucm.user_id = :user and "
      + "ucm.updated_at < :toDate group by cm.tx_subject_code, txs.taxonomy_subject_classification_id, "
      + "txs.title, COALESCE(ucm.status, 0, ucm.status)")
  List<SubjectCompetencyMatrixModel> fetchUserSubjectCompetencyMatrix(
      @BindBean UserSubjectCompetencyMatrixCommand.UserSubjectCompetencyMatrixCommandBean userCompetencySubjectMatrix);


}
