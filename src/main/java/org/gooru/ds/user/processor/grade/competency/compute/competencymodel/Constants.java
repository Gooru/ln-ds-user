package org.gooru.ds.user.processor.grade.competency.compute.competencymodel;

import java.util.UUID;

public class Constants {


  public static final String COMPETENCY_PLACEHOLDER = new UUID(0, 0).toString();
  public static final String USER_PLACEHOLDER = new UUID(0, 0).toString();

  private Constants() {
    throw new AssertionError();
  }
}


