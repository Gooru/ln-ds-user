package org.gooru.ds.user.processor.competencycompletion.classes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author mukul@gooru
 *  
 */
public class AllClassesCompetencyCompletionStatsCommand {
  private List<String> classIds;
  private Date statsDate;
  private String user;

  private static final Logger LOGGER = LoggerFactory.getLogger(AllClassesCompetencyCompletionStatsCommand.class);

  public List<String> getClassIds() {
    return classIds;
  }
  
  public String getUser() {
    return user;
  }
  
  public Date getStatsDate() {
    return statsDate;
  }

  static AllClassesCompetencyCompletionStatsCommand builder(JsonObject requestBody) {
    AllClassesCompetencyCompletionStatsCommand result = AllClassesCompetencyCompletionStatsCommand.buildFromJsonObject(requestBody);
    result.validate();
    return result;
  }

  public CompetencyCompletionStatsCommandBean asBean() {
    CompetencyCompletionStatsCommandBean bean = new CompetencyCompletionStatsCommandBean();
    bean.classIds = classIds;
    bean.statsDate = statsDate;
    bean.user = user;
    return bean;
  }

  private static AllClassesCompetencyCompletionStatsCommand buildFromJsonObject(JsonObject requestBody) {
    AllClassesCompetencyCompletionStatsCommand result = new AllClassesCompetencyCompletionStatsCommand();
    
    JsonArray classIds = requestBody.getJsonArray(CommandAttributes.CLASS_IDS);
    if (classIds == null || classIds.isEmpty()) {
      LOGGER.info("ClassIds not provided in the request");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "ClassIds not provided in the request");
      }      
    
    List<String> clsIds = new ArrayList<>(classIds.size());
    for (Object s : classIds) {
      clsIds.add(s.toString());
    }
    result.classIds = clsIds;
    
    LocalDate today = LocalDate.now();    
    LocalDate localDate = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
    Date statsDate = Date.valueOf(localDate);    
    result.statsDate = statsDate;    
    result.user = requestBody.getString(CommandAttributes.USER);
    
    return result;
  }

  private void validate() {
    
  }

  public static class CompetencyCompletionStatsCommandBean {
    private List<String> classIds;
    private Date statsDate;
    private String user;

    public String getUser() {
      return user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public List<String> getClassIds() {
      return classIds;
    }

    public void setClassIds(List<String> classIds) {
      this.classIds = classIds;
    }

    public Date getStatsDate() {
      return statsDate;
    }

    public void setStatsDate(Date statsDate) {
      this.statsDate = statsDate;
    }

  }

  static class CommandAttributes {
    private static final String CLASS_IDS = "classIds";
    private static final String STATS_DATE = "statsDate";
    private static final String USER = "user";

    private CommandAttributes() {
      throw new AssertionError();
    }
  }

}
