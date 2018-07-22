package org.gooru.ds.user.processor.atc.pvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.constants.ExecutionStatus;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hazelcast.util.StringUtil;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class CompetencyPerfVsCompletionService {
	
    private final ClassMembersDao classMembersDao;
    private final ListSubjectCompetencyDao listSubjectCompetencyDao;
    private final CompetencyCompletionDao competencyCompletionDao;
    private final CompetencyPerformanceDao competencyPerformanceDao;
  	
    public static final String PERCENT_COMPLETION = "percentCompletion";  	
  	public static final String PERCENT_SCORE = "percentScore";
  	public static final String USERID = "userId";
  	
    private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyPerfVsCompletionService.class);
   
    CompetencyPerfVsCompletionService(DBI dbi, DBI coreDbi) {
        this.classMembersDao = coreDbi.onDemand(ClassMembersDao.class);
        this.competencyCompletionDao = dbi.onDemand(CompetencyCompletionDao.class);
        this.competencyPerformanceDao = dbi.onDemand(CompetencyPerformanceDao.class);
        this.listSubjectCompetencyDao = dbi.onDemand(ListSubjectCompetencyDao.class);
    }
    
    public JsonArray fetchUserPerformanceCompletion (CompetencyPerfVsCompletionCommand command) {	   
    	JsonArray pvcArray = new JsonArray();
    	List<String> compCodes = new ArrayList<>();
    	//Integer compCount = 0;
    	JsonObject counts;
    	Double totalCompetencies = 0.0;
    	Double userAvgScore = 0.0;
    	CompetencyCompletionService competencyCompletionService = new CompetencyCompletionService(DBICreator.getDbiForDefaultDS());

    	//Fetch Class Members
    	List<String> classMembers = classMembersDao.fetchClassMembers(UUID.fromString(command.getClassId()));         

    	//get the list of competencies
    	if (!StringUtil.isNullOrEmpty(command.getGrade())) {
    		compCodes = listSubjectCompetencyDao.fetchGradeCompetencyList(command.getSubjectCode(), command.getGrade());
    		totalCompetencies = Double.valueOf((compCodes.size() + 1));
    		LOGGER.debug("Total competencies for the grade are " + totalCompetencies);

    		//For each user - fetch %Score & %Completion
    		if (classMembers != null && !classMembers.isEmpty()) {
    			for (String cm : classMembers) {
    				JsonObject userPvC = new JsonObject();
    				userPvC.put(USERID, cm);
    				counts = competencyCompletionService.fetchUserCompetencyStatus(cm, command.getSubjectCode(), compCodes);
    				LOGGER.debug("Total Grade Competencies completed " + counts.getDouble("completionCount"));
    				Double compCount = counts.getDouble("completionCount");
    				userPvC.put(PERCENT_COMPLETION, totalCompetencies != 0 ? (Double.valueOf(compCount/totalCompetencies) *100) : 0);
    				userAvgScore = competencyPerformanceDao.fetchGradeCompetencyPerformance(cm, 
    						PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));
    				userPvC.put(PERCENT_SCORE, userAvgScore);
    				pvcArray.add(userPvC);            
    			}        	
    		}
    	} else if (StringUtil.isNullOrEmpty(command.getGrade())) {
    		if (classMembers != null && !classMembers.isEmpty()) {
    			for (String cm : classMembers) {
    				JsonObject userPvC = new JsonObject();
    				userPvC.put(USERID, cm);
    				counts = competencyCompletionService.fetchUserCompetencyStatus(cm, command.getSubjectCode(), compCodes);    				
    				Double compCount = counts.getDouble("completionCount");
    				LOGGER.debug("Total Completed " + counts.getDouble("completionCount"));
    				totalCompetencies = counts.getDouble("totalCount");
    				LOGGER.debug("Total competencies in a Subject are " + totalCompetencies);
    				userPvC.put(PERCENT_COMPLETION, totalCompetencies != 0 ? (Double.valueOf(compCount/totalCompetencies) *100) : 0);
    				userAvgScore = competencyPerformanceDao.fetchGradeCompetencyPerformance(cm, 
    						PGArrayUtils.convertFromListStringToSqlArrayOfString(compCodes));
    				userPvC.put(PERCENT_SCORE, userAvgScore);
    				pvcArray.add(userPvC);            
    			}        	
    		}
    	}

    	return pvcArray;
    }

}
