package org.gooru.ds.user.processor.competencymatrixcoordinates;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 17/1/18.
 */
class CompetencyMatrixCoordinatesService {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(CompetencyMatrixCoordinatesService.class);

  private final CompetencyMatrixCoordinatesDao competencyMatrixCoordinatesDao;
  private CompetencyMatrixCoordinatesCommand command;

  CompetencyMatrixCoordinatesService(DBI dbi) {
    this.competencyMatrixCoordinatesDao = dbi.onDemand(CompetencyMatrixCoordinatesDao.class);
  }

  public CompetencyMatrixCoordinatesModelResponse fetchCompetencyMatrixCoordinates(
      CompetencyMatrixCoordinatesCommand command) {
    this.command = command;
    CompetencyMatrixCoordinatesModelResponse result =
        new CompetencyMatrixCoordinatesModelResponse();
    populateCoordinates(result);
    return result;
  }

  private void populateCoordinates(CompetencyMatrixCoordinatesModelResponse result) {
    if (command.isFilteredByCourses()) {
      populateCourses(result);
    } else if (command.isFilteredByDomains()) {
      populateDomains(result);
    } else if (command.isUnfiltered()) {
      populateCourses(result);
      populateDomains(result);
    }
  }

  private void populateDomains(CompetencyMatrixCoordinatesModelResponse result) {
    result.setDomains(
        competencyMatrixCoordinatesDao.fetchCompetencyMatrixCoordinatesDomains(command.asBean()));
  }

  private void populateCourses(CompetencyMatrixCoordinatesModelResponse result) {
    result.setCourses(
        competencyMatrixCoordinatesDao.fetchCompetencyMatrixCoordinatesCourses(command.asBean()));
  }
}
