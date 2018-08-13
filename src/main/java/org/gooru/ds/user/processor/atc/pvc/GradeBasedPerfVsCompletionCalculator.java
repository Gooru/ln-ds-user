package org.gooru.ds.user.processor.atc.pvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class GradeBasedPerfVsCompletionCalculator {

	private final ClassMembersDao classMembersDao;
	private final ListSubjectCompetencyDao listSubjectCompetencyDao;
	private final CompetencyPerformanceDao competencyPerformanceDao;
	

	public static final String PERCENT_COMPLETION = "percentCompletion";
	public static final String COMPLETED_COMPETENCIES = "completedCompetencies";
	public static final String TOTAL_COMPETENCIES = "totalCompetencies";
	public static final String PERCENT_SCORE = "percentScore";
	public static final String USERID = "userId";

	private static final Logger LOGGER = LoggerFactory.getLogger(GradeBasedPerfVsCompletionCalculator.class);

	GradeBasedPerfVsCompletionCalculator(DBI dbi, DBI coreDbi) {
		this.classMembersDao = coreDbi.onDemand(ClassMembersDao.class);
		this.competencyPerformanceDao = dbi.onDemand(CompetencyPerformanceDao.class);
		this.listSubjectCompetencyDao = dbi.onDemand(ListSubjectCompetencyDao.class);
		
	}


	public JsonArray fetchGradeBasedUserPerformanceCompletion (CompetencyPerfVsCompletionCommand command) {
		JsonArray pvcArray = new JsonArray();
		List<String> compCodes = new ArrayList<>();		
		JsonObject counts;
		Double totalCompetencies = 0.0;
		Double userAvgScore = 0.0;

		//Fetch Class Members
		List<String> classMembers = classMembersDao.fetchClassMembers(UUID.fromString(command.getClassId()));         
		CompetencyCompletionService competencyCompletionService = new CompetencyCompletionService(DBICreator.getDbiForDefaultDS());

		if (classMembers != null && !classMembers.isEmpty()) {
			
			compCodes = listSubjectCompetencyDao.fetchGradeCompetencyList(command.getSubjectCode(), command.getGrade());
			if (compCodes != null && compCodes.isEmpty()) {
				for(String cc : compCodes) {
					LOGGER.debug("The grade competencies are " + cc);
				}
				totalCompetencies = Double.valueOf(compCodes.size());
				LOGGER.debug("Total competencies for the grade are " + totalCompetencies);
				
				for (String cm : classMembers) {
					JsonObject userPvC = new JsonObject();
					userPvC.put(USERID, cm);
					userPvC.put(TOTAL_COMPETENCIES, totalCompetencies);    				
					counts = competencyCompletionService.fetchUserCompetencyStatus(cm, command.getSubjectCode(), compCodes, 
							command.getMonth(), command.getYear()); 
					LOGGER.debug("Total Grade Competencies completed " + counts.getDouble("completionCount"));
					Double compCount = counts.getDouble("completionCount");
					userPvC.put(COMPLETED_COMPETENCIES, compCount);
					userPvC.put(PERCENT_COMPLETION, totalCompetencies != 0 ? (Double.valueOf(compCount/totalCompetencies) *100) : 0);
					if (compCodes != null && !compCodes.isEmpty() && (command.getMonth() == null || command.getYear() == null)) {
						userAvgScore = competencyPerformanceDao.fetchGradeCompetencyPerformance(cm, 
								PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));    					
					} else if (compCodes != null && !compCodes.isEmpty() && (command.getMonth() != null && command.getYear() != null)) {
						userAvgScore = competencyPerformanceDao.fetchGradeCompetencyPerformance(cm, 
								PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));    					
					}    				
					userPvC.put(PERCENT_SCORE, userAvgScore);
					pvcArray.add(userPvC);            
				}  		
				
			}

		} //classMembers
		
		return pvcArray;
	}
}
