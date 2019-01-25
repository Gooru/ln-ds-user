package org.gooru.ds.user.processor.grade.competency;

import java.util.List;
import org.gooru.ds.user.app.jdbi.DBICreator;


/**
 * @author mukul@gooru
 */
public class GradeCompetencyService {

  public GradeCompetencyModel fetchGradeCompetency(
      GradeCompetencyCommand command) {
    GradeCompetencyFetcher gradeCompetencyFetcher =
        new GradeCompetencyFetcher();
    List<String> gradeCompetencyList =
        gradeCompetencyFetcher.fetchGradeCompetencies(command);   
    
    GradeCompetencyModel gradeCompetency = new GradeCompetencyModel();
    if (gradeCompetencyList != null && !gradeCompetencyList.isEmpty()) {
      gradeCompetency.setGradeId(command.getGradeId());      
      gradeCompetency.setTotalCompetencies(gradeCompetencyList.size());
    } else {
      //If totalCompetencies are zero, then possibly the grade is out-of-bounds 
      //from what is currently set (1-13 is current setting)
      gradeCompetency.setGradeId(command.getGradeId());
    }
    
    return gradeCompetency;
  }

}
