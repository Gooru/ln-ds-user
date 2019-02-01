
package org.gooru.ds.user.processor.domain.report.utils;

import org.gooru.ds.user.constants.HttpConstants;
import org.gooru.ds.user.exceptions.HttpResponseWrapperException;
import org.gooru.ds.user.processor.domain.report.dbhelpers.CoreClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On 31-Jan-2019
 */
public final class DomainReportUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(DomainReportUtils.class);

  private DomainReportUtils() {
    throw new AssertionError();
  }

  public static String fetchSubjectFromClass(CoreClass cls) {
    JsonObject classPreference = cls.getPreference();
    if (classPreference == null || classPreference.isEmpty()) {
      LOGGER.warn("class preference is not set, aborting...");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "class preferce is not setup");
    }

    String subject = classPreference.getString("subject");
    if (subject == null || subject.isEmpty()) {
      LOGGER.warn("subject is not set for class, aborting...");
      throw new HttpResponseWrapperException(HttpConstants.HttpStatus.BAD_REQUEST,
          "no subject associated with class");
    }

    // TODO: validate subject against db?
    return subject;
  }

  public static Integer getAsInt(JsonObject requestBody, String key) {
    String value = requestBody.getString(key);
    if (value == null || value.isEmpty()) {
      return null;
    }

    Integer result = null;
    if (key != null) {
      try {
        result = Integer.valueOf(value);
      } catch (NumberFormatException e) {
        LOGGER.info("Invalid number format for {}", key);
        result = null;
      }
    }
    return result;
  }
}
