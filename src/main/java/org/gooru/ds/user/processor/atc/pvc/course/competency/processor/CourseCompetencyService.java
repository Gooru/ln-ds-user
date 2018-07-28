package org.gooru.ds.user.processor.atc.pvc.course.competency.processor;

import java.util.ArrayList;
import java.util.List;

import org.gooru.ds.user.processor.atc.pvc.competency.CompetencyModel;
import org.gooru.ds.user.processor.atc.pvc.course.competency.CompetencyExtractor;
import org.gooru.ds.user.processor.atc.pvc.course.competency.CompetencyRouteCalculator;
import org.gooru.ds.user.processor.atc.pvc.course.competency.CompetencyRouteModel;

import org.skife.jdbi.v2.DBI;

import io.vertx.core.json.JsonObject;

public class CourseCompetencyService {

    private final DBI dbiForDefaultDS;
    private final DBI dbiForCoreDS;    

    CourseCompetencyService (DBI dbiForCoreDS, DBI dbiForDefaultDS) {

        this.dbiForDefaultDS = dbiForDefaultDS;
        this.dbiForCoreDS = dbiForCoreDS;
    }

    JsonObject calculateCompetencyRoute(CourseCompetencyCommand command) {
        CompetencyRouteCalculator competencyRouteCalculator = CompetencyRouteCalculator.build();
        CompetencyRouteModel competencyRouteModel =
            competencyRouteCalculator.calculateCompetencyRoute(command.asRouteCalculatorModel());
        return competencyRouteModel.toJson();
    }
    
    
    List<String> calculateCourseCompetencies(CourseCompetencyCommand command) {
        CompetencyRouteCalculator competencyRouteCalculator = CompetencyRouteCalculator.build();
        CompetencyRouteModel competencyRouteModel =
            competencyRouteCalculator.calculateCompetencyRoute(command.asRouteCalculatorModel());
        List<String> competencyList = createCompetencyList(competencyRouteModel);        
        return competencyList;
    }

    private List<String> createCompetencyList(CompetencyRouteModel competencyRouteModel) {    	
    	List<String> competencyList = new CompetencyExtractor().getComptencyList(competencyRouteModel);    			
        return competencyList;
    }
}
