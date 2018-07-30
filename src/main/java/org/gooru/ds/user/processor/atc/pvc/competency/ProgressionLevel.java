package org.gooru.ds.user.processor.atc.pvc.competency;

/**
 * @author ashish.
 */
public class ProgressionLevel {
    private final int progressionLevel;

    public ProgressionLevel(int progressionLevel) {
        this.progressionLevel = progressionLevel;
    }

    public int getProgressionLevel() {
        return progressionLevel;
    }

    @Override
    public String toString() {
        return "ProgressionLevel{" + "progressionLevel=" + progressionLevel + '}';
    }
}
