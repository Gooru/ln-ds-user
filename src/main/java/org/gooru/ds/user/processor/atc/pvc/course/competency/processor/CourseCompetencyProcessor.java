package org.gooru.ds.user.processor.atc.pvc.course.competency.processor;

import java.util.List;
import java.util.UUID;

import org.gooru.ds.user.app.jdbi.DBICreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

public class CourseCompetencyProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseCompetencyProcessor.class);
	//We need to get the Competency Map right from the earthline, and hence the below userId
	private static final String userUUIDStr = "00000000-0000-0000-0000-000000000000";
    private CourseCompetencyService courseCompetencyService =
        new CourseCompetencyService(DBICreator.getDbiForDefaultDS(), DBICreator.getDbiForCoreDS());

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<String> getCourseCompetency(String courseId) {
    	
    	CourseCompetencyCommand command = CourseCompetencyCommand.builder(userUUIDStr, courseId);
    	JsonObject competencyRoute = courseCompetencyService.calculateCompetencyRoute(command);    	
    	LOGGER.debug("The Route Map is " + competencyRoute.encodePrettily());
    	
    	if (!competencyRoute.isEmpty()) {
    		List competencyList = courseCompetencyService.calculateCourseCompetencies(command);
        	return (competencyList != null) ? competencyList : null;    		
    	} else {
    		return null;
    	}

    }
}
