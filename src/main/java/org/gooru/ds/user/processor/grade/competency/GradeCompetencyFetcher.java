package org.gooru.ds.user.processor.grade.competency;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.gooru.ds.user.processor.grade.competency.compute.processor.GradeCompetencyComputeProcessor;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 */
public class GradeCompetencyFetcher {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(GradeCompetencyFetcher.class);

  GradeCompetencyFetcher() {
  }

  public List<String> fetchGradeCompetencies(
      GradeCompetencyCommand command) {
    List<String> gradeCompetencyList = new ArrayList<>();
    
    GradeCompetencyComputeProcessor gradeCompetencyProcessor = new GradeCompetencyComputeProcessor();
    gradeCompetencyList = gradeCompetencyProcessor.getGradeCompetency(command.getGradeId(), command.getSubjectCode());
 
    return gradeCompetencyList;
  }
}
