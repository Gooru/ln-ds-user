package org.gooru.ds.user.processor.user.distribution;

import java.util.List;
import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 10/1/18.
 */
class UserDistributionService {
  private final UserDistributionDao userDistributionDao;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserDistributionService.class);
  private UserDistributionCommand command;

  UserDistributionService(DBI dbi) {
    this.userDistributionDao = dbi.onDemand(UserDistributionDao.class);
  }

  public UserDistributionModelResponse fetchUserDistribution(UserDistributionCommand command) {
    this.command = command;
    if (command.getGeoLocation() != null) {
      LOGGER.debug("User distribution fetch based on geolocation");
      return fetchUserDistributionByGeoLocationAcrossSubjects();
    } else if (command.getSubject() != null) {
      LOGGER.debug("User distribution fetch based on subject");
      return fetchUserDistributionBySubjectAcrossGeoLocations();
    } else if (command.getGeoLocation() == null && command.getSubject() == null) {
      LOGGER.debug("User distribution fetch across geolocation and subject");
      return fetchUserDistributionAcrossGeolocationAndSubject();
    } else {
      LOGGER.info("User distribution fetch failed because of invalid criteria");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "Invalid fetch criteria for user distribution");
    }
  }

  private UserDistributionModelResponse fetchUserDistributionAcrossGeolocationAndSubject() {
    UserDistributionModelResponse result = new UserDistributionModelResponse();
    List<UserDistributionModel> geoLocationResult =
        userDistributionDao.fetchUserDistributionAcrossGeoLocations(command.asBean());
    result.setGeoLocations(geoLocationResult);
    List<UserDistributionModel> subjectResult =
        userDistributionDao.fetchUserDistributionAcrossSubjects(command.asBean());
    result.setSubjects(subjectResult);
    return result;
  }

  private UserDistributionModelResponse fetchUserDistributionBySubjectAcrossGeoLocations() {
    UserDistributionModelResponse result = new UserDistributionModelResponse();
    List<UserDistributionModel> geoLocationResult =
        userDistributionDao.fetchUserDistributionBySubjectAcrossGeoLocations(command.asBean());
    result.setGeoLocations(geoLocationResult);
    return result;
  }

  private UserDistributionModelResponse fetchUserDistributionByGeoLocationAcrossSubjects() {
    UserDistributionModelResponse result = new UserDistributionModelResponse();
    List<UserDistributionModel> subjectResult =
        userDistributionDao.fetchUserDistributionByGeoLocationAcrossSubjects(command.asBean());
    result.setSubjects(subjectResult);
    return result;
  }
}
