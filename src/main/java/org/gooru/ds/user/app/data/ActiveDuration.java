package org.gooru.ds.user.app.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ashish on 11/1/18.
 */
public final class ActiveDuration {

  private ActiveDuration() {
    throw new AssertionError();
  }

  private static final List<String> VALID_DURATION = Arrays.asList("1w", "1m", "3m", "6m", "1y");

  private static final List<String> VALID_ACTIVE_DURATION =
      Collections.unmodifiableList(VALID_DURATION);

  public static List<String> getValidActiveDuration() {
    return VALID_ACTIVE_DURATION;
  }

  public static boolean isValidDuration(String duration) {
    return VALID_DURATION.contains(duration);
  }
}
