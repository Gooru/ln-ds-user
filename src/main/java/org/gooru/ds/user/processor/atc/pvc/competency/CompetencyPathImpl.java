package org.gooru.ds.user.processor.atc.pvc.competency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish.
 */
class CompetencyPathImpl implements CompetencyPath {
    private final SubjectCode subjectCode;
    private final DomainCode domainCode;
    private final Competency startPoint;
    private final Competency endPoint;
    private final List<ProgressionLevel> path;
    private boolean isProgressionOrder;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompetencyPathImpl.class);
    		
    CompetencyPathImpl(Competency startPoint, Competency endPoint) {
        Objects.requireNonNull(startPoint);
        Objects.requireNonNull(endPoint);

        if (!startPoint.belongToSamePath(endPoint)) {
            throw new IllegalStateException(
                "Competency path can not be calculated if competencies are not on same path");
        }

        this.subjectCode = startPoint.getSubject();
        this.domainCode = startPoint.getDomain();
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.path = calculatePath();
    }

    private List<ProgressionLevel> calculatePath() {
        int beginningProgression = startPoint.getProgressionLevel().getProgressionLevel();
        int endProgression = endPoint.getProgressionLevel().getProgressionLevel();
        
        List<ProgressionLevel> result = new ArrayList<>();
        if ((beginningProgression == endProgression)) {
            return Collections.emptyList();            
        } else if (beginningProgression < endProgression) {
            isProgressionOrder = true;
            //Mukul - I need it inclusive, i.e the Top Competency should be included in the path
            for (int i = beginningProgression + 1; i <= endProgression; i++) {
                result.add(new ProgressionLevel(i));
            }
        } else {
            isProgressionOrder = false;
            for (int i = endProgression + 1; i < beginningProgression; i++) {
                result.add(new ProgressionLevel(i));
            }
        }
        return result;
    }

    @Override
    public SubjectCode getSubject() {
        return subjectCode;
    }

    @Override
    public DomainCode getDomain() {
        return domainCode;
    }

    @Override
    public Competency getStartPoint() {
        return startPoint;
    }

    @Override
    public Competency getEndPoint() {
        return endPoint;
    }

    @Override
    public List<ProgressionLevel> getPath() {
        return Collections.unmodifiableList(path);
    }

    @Override
    public boolean isPathInProgressionOrder() {
        return isProgressionOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompetencyPathImpl that = (CompetencyPathImpl) o;

        if (!subjectCode.equals(that.subjectCode)) {
            return false;
        }
        if (!domainCode.equals(that.domainCode)) {
            return false;
        }
        if (!startPoint.equals(that.startPoint)) {
            return false;
        }
        if (!endPoint.equals(that.endPoint)) {
            return false;
        }
        if (isProgressionOrder != that.isProgressionOrder) {
            return false;
        }
        return path.equals(that.path);
    }

    @Override
    public int hashCode() {
        int result = subjectCode.hashCode();
        result = 31 * result + domainCode.hashCode();
        result = 31 * result + startPoint.hashCode();
        result = 31 * result + endPoint.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CompetencyPath{" + "subjectCode=" + subjectCode + ", domainCode=" + domainCode + ", startPoint="
            + startPoint + ", endPoint=" + endPoint + ", path=" + path + ", isProgressionOrder=" + isProgressionOrder
            + '}';
    }
}
