package org.gooru.ds.user.processor.subjectcompetencymatrix;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


interface UserSubjectCompetencyMatrixDao {

  @Mapper(SubjectCompetencyMatrixModelMapper.class)
  @SqlQuery("select cm.tx_subject_code, txs.title as tx_subject_name, tsc.code as tx_classification_code, tsc.title as tx_classification_name, "
      + "COALESCE(ucm.status, 0, ucm.status) as status, count(1) as competency_count, txs.sequence_id as tx_subject_seq, tsc.sequence_id as tx_classification_seq "
      + "from domain_competency_matrix cm inner join tx_subjects txs on txs.id = cm.tx_subject_code inner join taxonomy_subject_classification tsc on tsc.id = txs.taxonomy_subject_classification_id  "
      + "left join learner_profile_competency_status ucm on  cm.tx_subject_code = ucm.tx_subject_code and cm.tx_comp_code = ucm.gut_code and ucm.user_id = :user and "
      + "ucm.updated_at < :toDate group by cm.tx_subject_code, tsc.code, txs.title, tsc.title, txs.sequence_id, tsc.sequence_id, COALESCE(ucm.status, 0, ucm.status)")
  List<SubjectCompetencyMatrixModel> fetchUserSubjectCompetencyMatrix(
      @BindBean UserSubjectCompetencyMatrixCommand.UserSubjectCompetencyMatrixCommandBean userCompetencySubjectMatrix);


}
