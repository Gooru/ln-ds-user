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
interface ListSubjectCompetencyDao {
	
   @SqlQuery("select tx_comp_code from grade_competency_matrix where tx_subject_code = :subjectCode "
   		+ "and grade_competency_matrix.grade_seq <= (select grade_seq from grade_competency_matrix where grade = :grade LIMIT 1)")
   List<String> fetchGradeCompetencyList (
       @Bind("subjectCode") String subjectCode, @Bind("grade") String grade);
   
   
   @SqlQuery("select tx_comp_code from domain_competency_matrix where tx_subject_code = :subjectCode "
	   		+ "and tx_comp_code = ANY(:competencyCodes)")
	   List<String> fetchCourseCompetencyList (@Bind("subjectCode") String subjectCode, 
			   @Bind("competencyCodes") PGArray<String> competencyCodes);
}
