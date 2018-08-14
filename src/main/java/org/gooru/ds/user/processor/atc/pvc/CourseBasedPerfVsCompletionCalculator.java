package org.gooru.ds.user.processor.atc.pvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.processor.atc.pvc.course.competency.processor.CourseCompetencyProcessor;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class CourseBasedPerfVsCompletionCalculator {
	
	private final ClassMembersDao classMembersDao;
	private final CompetencyPerformanceDao competencyPerformanceDao;
	
	public static final String PERCENT_COMPLETION = "percentCompletion";
	public static final String COMPLETED_COMPETENCIES = "completedCompetencies";
	public static final String TOTAL_COMPETENCIES = "totalCompetencies";
	public static final String PERCENT_SCORE = "percentScore";
	public static final String USERID = "userId";

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseBasedPerfVsCompletionCalculator.class);

	CourseBasedPerfVsCompletionCalculator(DBI dbi, DBI coreDbi) {
		this.classMembersDao = coreDbi.onDemand(ClassMembersDao.class);
		this.competencyPerformanceDao = dbi.onDemand(CompetencyPerformanceDao.class);
	}


	public JsonArray fetchCourseBasedUserPerformanceCompletion (CompetencyPerfVsCompletionCommand command) {		   
    	JsonArray pvcArray = new JsonArray();
    	List<String> compCodes = new ArrayList<>();
    	List<String> courseCompList = new ArrayList<>();
    	JsonObject counts;
    	JsonObject courseCompetency;
    	Double totalCompetencies = 0.0;
    	Double userAvgScore = 0.0;
	
		//Fetch Class Members
		List<String> classMembers = classMembersDao.fetchClassMembers(UUID.fromString(command.getClassId()));         
		CompetencyCompletionService competencyCompletionService = new CompetencyCompletionService(DBICreator.getDbiForDefaultDS());
		
		if (classMembers != null && !classMembers.isEmpty()) {    			
			CourseCompetencyProcessor courseCompetencyProcessor = new CourseCompetencyProcessor();
			courseCompList = courseCompetencyProcessor.getCourseCompetency(command.getCourseId());   
			
			if (courseCompList != null && !courseCompList.isEmpty() ) {				
				for (String cc : courseCompList) {
					LOGGER.debug("The list of competencies is" + cc);    				
				}
	    		totalCompetencies = Double.valueOf(courseCompList.size());
				
				for (String cm : classMembers) {
					JsonObject userPvC = new JsonObject();
					LOGGER.info("The UserId is" + cm);
					userPvC.put(USERID, cm);
					userPvC.put(TOTAL_COMPETENCIES, totalCompetencies);
					counts = competencyCompletionService.fetchUserCompetencyStatus(cm, command.getSubjectCode(), courseCompList, 
							command.getMonth(), command.getYear()); 
					if (!counts.isEmpty() && counts != null) {
						Double compCount = counts.getDouble("completionCount");  
						userPvC.put(COMPLETED_COMPETENCIES, compCount);
						userPvC.put(PERCENT_COMPLETION, totalCompetencies != 0 ? (Double.valueOf(compCount/totalCompetencies) *100) : 0);
						
						if (courseCompList != null && !courseCompList.isEmpty() && (command.getMonth() == null || command.getYear() == null)) {
		    				userAvgScore = competencyPerformanceDao.fetchGradeCompetencyPerformance(cm, 
		    						PGArrayUtils.convertFromListStringToSqlArrayOfString(courseCompList));
						} else if (courseCompList != null && !courseCompList.isEmpty() && (command.getMonth() != null && command.getYear() != null)) {
							userAvgScore = competencyPerformanceDao.fetchGradeCompetencyPerformanceTimeBased(cm, 
									PGArrayUtils.convertFromListStringToSqlArrayOfString(courseCompList), 
									command.getMonth(), command.getYear());    					
						} 
						userPvC.put(PERCENT_SCORE, userAvgScore);
						pvcArray.add(userPvC);  						
					}
          
				}
				
			}
        	
		} else {
			LOGGER.info("No Students enrolled for this class");			
		}
		
		return pvcArray;
	}

}
