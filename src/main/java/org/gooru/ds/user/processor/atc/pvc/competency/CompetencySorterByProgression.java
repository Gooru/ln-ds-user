package org.gooru.ds.user.processor.atc.pvc.competency;

import java.util.Comparator;

/**
 * @author ashish.
 */
class CompetencySorterByProgression implements Comparator<Competency> {

  @Override
  public int compare(Competency o1, Competency o2) {
    return o1.getProgressionLevel().getProgressionLevel()
        - o2.getProgressionLevel().getProgressionLevel();
  }
}
