package org.gooru.ds.user.processor.competencycompletion.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 *  
 */
public class AllClassesCompetencyCompletionStatsService {  

  private final ClassMembersDao classMembersDao;
  private final CompetencyCompletionStatsDao competencyCompletionStatsDao;
  private static final Logger LOGGER =
      LoggerFactory.getLogger(AllClassesCompetencyCompletionStatsService.class);

  AllClassesCompetencyCompletionStatsService(DBI dbi, DBI coreDbi) {
    this.classMembersDao = coreDbi.onDemand(ClassMembersDao.class);
    this.competencyCompletionStatsDao = dbi.onDemand(CompetencyCompletionStatsDao.class);
  }

  public CompetencyCompletionStatsResponse fetchAllClassesCompetencyCompletion(
      AllClassesCompetencyCompletionStatsCommand command) {
    List<CompetencyCompletionStatsModel> classCompetencyStatsModelList = new ArrayList<>();       
    List<String> classIds = command.getClassIds();
    
    for(String classId : classIds) {
      CompetencyCompletionStatsModel classCompetencyStatsModel = null; 
      // Fetch Class Members
      List<String> classMembers = new ArrayList<>();
      if (command.getUser() != null) {
        classMembers.add(command.getUser());
      } else {
        classMembers =
            classMembersDao.fetchClassMembers(UUID.fromString(classId));
      }
      
      if (classMembers != null && !classMembers.isEmpty()) { 
        classCompetencyStatsModel = competencyCompletionStatsDao.fetchClassCompetencyCompletionStats(
              PGArrayUtils.convertFromListStringToSqlArrayOfString(classMembers),
              classId, command.getStatsDate());      
      }
      
      if(classCompetencyStatsModel != null) {
        classCompetencyStatsModelList.add(classCompetencyStatsModel);        
      }      
    }    

    if (classCompetencyStatsModelList != null && !classCompetencyStatsModelList.isEmpty()) {
      CompetencyCompletionStatsResponse stats = new CompetencyCompletionStatsResponse();
      stats.setCompetencyStats(classCompetencyStatsModelList);
      return stats;
    } else {
      return null;
    }
  }

}
