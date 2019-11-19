package org.gooru.ds.user.processor.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author szgooru on 20-Aug-2018
 */
public final class ValidatorUtils {

  private final static Logger LOGGER = LoggerFactory.getLogger(ValidatorUtils.class);
  private static final List<String> DEFAULT_ALLOWED_FIELDS =
      new ArrayList<>(Arrays.asList("version"));

  private ValidatorUtils() {
    throw new AssertionError();
  }

  public static boolean isNullOrEmpty(String value) {
    return (value == null || value.isEmpty() || value.trim().isEmpty());
  }

  public static boolean isValidUUID(String id) {
    try {
      if (!isNullOrEmpty(id) && id.length() == 36) {
        UUID.fromString(id);
        return true;
      }

      return false;
    } catch (IllegalArgumentException iae) {
      LOGGER.warn("invalid UUID string '{}'", id);
    }

    return false;
  }


  public static JsonObject validateMandatoryFields(List<String> mandatoryfields, JsonObject input) {
    JsonObject result = new JsonObject();
    mandatoryfields.forEach(entry -> {
      if (input.getValue(entry) == null) {
        result.put(entry, "Missing field");
      }
    });
    return result.isEmpty() ? null : result;
  }

  public static JsonObject validateAllowedFields(List<String> allowedfields, JsonObject input) {
    JsonObject result = new JsonObject();
    input.forEach(entry -> {
      if (!(allowedfields.contains(entry.getKey())
          || DEFAULT_ALLOWED_FIELDS.contains(entry.getKey()))) {
        result.put(entry.getKey(), "Field not allowed");
      }
    });
    return result.isEmpty() ? null : result;
  }
}
