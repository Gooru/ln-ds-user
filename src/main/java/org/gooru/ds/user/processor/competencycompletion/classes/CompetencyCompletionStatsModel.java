package org.gooru.ds.user.processor.competencycompletion.classes;

/**
 * @author mukul@gooru
 *  
 */
public class CompetencyCompletionStatsModel {
  
  private Integer completedCompetencies;
  private Integer totalCompetencies;
  private String classId;
  
  public Integer getCompletedCompetencies() {
    return completedCompetencies;
  }

  public void setCompletedCompetencies(Integer completedCompetencies) {
    this.completedCompetencies = completedCompetencies;
  }

  public Integer getTotalCompetencies() {
    return totalCompetencies;
  }

  public void setTotalCompetencies(Integer totalCompetencies) {
    this.totalCompetencies = totalCompetencies;
  }

  public String getClassId() {
    return classId;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }
  
}
