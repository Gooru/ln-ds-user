package org.gooru.ds.user.processor.competency.subjects;

import java.util.List;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 * 
 */
public class CompetencySubjectListService {


  private final CompetencySubjectListDao competencySubjectListDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(CompetencySubjectListService.class);

  CompetencySubjectListService(DBI dbi) {
    this.competencySubjectListDao = dbi.onDemand(CompetencySubjectListDao.class);
  }

  public CompetencySubjectListModelResponse fetchCompetencySubjects(
      CompetencySubjectListCommand command) {
    List<CompetencySubjectListModel> models =
        competencySubjectListDao.fetchCompetencySubjectList(command.asBean());
    CompetencySubjectListModelResponse result = new CompetencySubjectListModelResponse();
    result.setSubjects(models);
    return result;
  }


}
